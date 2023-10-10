package cryptography

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import javax.imageio.ImageIO
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
                val inputFileName = readln()
                println("Output image file:")
                val outputFileName = readln()
                println("Message to hide:")
                val message = readln()

                val inputFile = File(inputFileName)
                if (!inputFile.exists()) {
                    println("Can't read input file!")
                    continue
                }
                val image = ImageIO.read( inputFile)

                val hiddenImage = hide(message, image)
                if (hiddenImage == null) {
                    println("The input image is not large enough to hold this message.")
                    continue
                }

                ImageIO.write(hiddenImage, "png", File(outputFileName))
                println("Message saved in $outputFileName image.")
                continue
            }
            "show" -> {
                println("Input image file:")
                val inputFileName = readln()

                val inputFile = File(inputFileName)
                if (!inputFile.exists()) {
                    println("Can't read input file!")
                    continue
                }
                val image = ImageIO.read( inputFile)
                println("Message:\n${show(image)}")

                continue
            }
            else -> {
                println("Wrong task: [$command]")
                continue
            }
        }
    }
}


fun hide(string: String, image: BufferedImage): BufferedImage? {

    val stringAsByteArray = string.encodeToByteArray()
    val bitLists = stringAsByteArray.map { it.toBits() }.toMutableList()
    val bits = bitLists.flatten().toMutableList()
    bits.addAll(ENDING)
    if (bits.size > image.width * image.height) {
        return null
    }

    val hiddenImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)

    var bitIndex = 0
    for (x in 0 until image.width) {
        for (y in 0 until image.height) {

            val pixel = image.getRGB(x, y)
            val color = Color(pixel)

            val oldBlue = color.blue

            if (bitIndex >= bits.size) {
                hiddenImage.setRGB(x, y, color.rgb)
                continue
            }

            assert(oldBlue shr 1 shl 1 or bits[bitIndex]  == ((oldBlue and 254) or bits[bitIndex]) % 256)
            //val newBlue = oldBlue shr 1 shl 1 or bits[bitIndex]
            val newBlue = ((oldBlue and 254) or bits[bitIndex]) % 256
            assert(newBlue - oldBlue !in -1..1)


            val newColor = Color(color.red, color.green, newBlue)

            hiddenImage.setRGB(x, y, newColor.rgb)

            bitIndex++
        }
    }

    return hiddenImage
}




fun show(image: BufferedImage): String {

    var bits = mutableListOf<Int>()
    var bitIndex = 0
    out@for (x in 0 until image.width) {
        for (y in 0 until image.height) {

            val pixel = image.getRGB(x, y)
            val color = Color(pixel)

            bits.add( color.blue % 2)

            if (bits.takeLast(24) == ENDING) {
                break@out
            }

            bitIndex++
        }
    }
    bits = bits.dropLast(24).toMutableList()

    val bitLists = bits.chunked(8)
    val byteList = mutableListOf<Byte>()

    for (bitList in bitLists) {
        val byte = bitList.toByte()
        byteList.add(byte)
    }

    return byteList.toByteArray().toString(Charsets.UTF_8)
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
