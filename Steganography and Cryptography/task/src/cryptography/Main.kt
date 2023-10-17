package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.experimental.xor
import kotlin.math.pow

val ENDING = listOf<Byte>(0, 0, 3).flatMap { it.toBits() }


fun main() {

    while (true) {
        println("Task (hide, show, exit):")
        when (val command = readln()) {
            "exit" -> {
                println("Bye!")
                break
            }
            "hide" -> {
                println("Input image file:")
                val image = try {
                    ImageIO.read( File(readln()))
                } catch (e: Exception) {
                    println("Can't read input file!")
                    continue
                }

                println("Output image file:")
                val outputFileName = readln()
                println("Message to hide:")
                val message = readln()
                println("Password:")
                val password = readln()


                // processing...
                if (!hide(image, message, password)) { continue }

                ImageIO.write(image, "png", File(outputFileName))
                println("Message saved in $outputFileName image.")
            }
            "show" -> {
                println("Input image file:")
                val inputFile = File( readln())
                if (!inputFile.exists()) {
                    println("Can't read input file!")
                    continue
                }
                println("Password:")
                val password = readln()

                val image = ImageIO.read( inputFile)
                println("Message:\n${show(image, password)}")
            }
            else -> {
                println("Wrong task: [$command]")
            }
        }
    }
}




fun hide(image: BufferedImage, msg: String, pwd: String): Boolean {

    var msgAsByteArray = msg.encodeToByteArray()
    var pwdAsByteArray = pwd.encodeToByteArray()

    while (pwdAsByteArray.size < msgAsByteArray.size) { pwdAsByteArray += pwdAsByteArray }
    pwdAsByteArray = pwdAsByteArray.dropLast(pwdAsByteArray.size - msgAsByteArray.size).toByteArray()

    msgAsByteArray = msgAsByteArray.zip(pwdAsByteArray) { a, b -> a xor b }.toByteArray()


    val bits = msgAsByteArray
        .map { it.toBits() }
        .flatten()
        .toMutableList()
        .apply { addAll(ENDING) }

    if (bits.size > image.width * image.height) {
        println("The input image is not large enough to hold this message.")
        return false
    }

    var bitIndex = 0
    out@for (y in 0 until image.height) {
        for (x in 0 until image.width) {
            if (bitIndex >= bits.size) { break@out }

            with (bits[bitIndex]) {
                val color = Color(image.getRGB(x, y))
                image.setRGB(x, y, Color(color.red, color.green,
                    ((color.blue and 254) or this) % 256
                ).rgb)
            }

            bitIndex++
        }
    }

    return true
}


/**
 * read the least significant bits from the blue channel out of image given,
 * until marker ENDING is found
 * @return the hidden message as UTF-8-string
 */
fun show(image: BufferedImage, password: String): String {
    val bits = mutableListOf<Int>()
    var bitIndex = 0
    out@for (y in 0 until image.height) {
        for (x in 0 until image.width) {

            bits.add( Color( image.getRGB(x, y)).blue % 2)
            if (bits.takeLast(ENDING.size) == ENDING) { break@out }
            bitIndex++
        }
    }
    repeat(ENDING.size) { bits.removeLast() }

    // convert bits to bytes to UTF-8-string, which is returned
    val byteList = mutableListOf<Byte>()
    for (bitList in bits.chunked(8)) { byteList.add( bitList.toByte()) }
    val encrypted = byteList.toByteArray()

    var pwdAsByteArray = password.encodeToByteArray()
    while (pwdAsByteArray.size < encrypted.size) { pwdAsByteArray += pwdAsByteArray }
    pwdAsByteArray = pwdAsByteArray.dropLast(pwdAsByteArray.size - encrypted.size).toByteArray()

    val output = encrypted.zip(pwdAsByteArray) { a, b -> a xor b }.toByteArray()

    return output.toString(Charsets.UTF_8)
}



fun Byte.toBits(): List<Int> {
    val bitList = mutableListOf<Int>()
    var value = this.toUByte().toInt()
    for (i in 7 downTo 0) {
        val x: Int = 2.0.pow(i).toInt() // 2^i
        bitList.add(value / x)
        value %= x
    }
    return bitList
}


fun List<Int>.toByte(): Byte {
    assert(this.size == 8)
    var out = 0
    for (i in 0 .. 7) {
        out += this[i] * 2.0.pow(7 - i).toInt()
    }
    return out.toByte()
}
