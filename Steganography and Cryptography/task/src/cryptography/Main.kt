package cryptography

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

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


                val inputFile = File(inputFileName)
                if (!inputFile.exists()) {
                    println("Can't read input file!")
                    continue
                }

                println("Input Image: $inputFileName")
                println("Output Image: $outputFileName")


                val image = ImageIO.read( inputFile)


                for (x in 0 until image.width) {
                    for (y in 0 until image.height) {

                        val pixel = image.getRGB(x, y)
                        val color = Color(pixel)

                        val newColor = Color(
                            color.red or 1,
                            color.green or 1,
                            color.blue or 1
                        )


                        //val red =   (pixel and 0b00000000111111110000000000000000) shr 16
//                        val green = (pixel and 0b00000000000000001111111100000000) shr 8
//                        val blue =  (pixel and 0b00000000000000000000000011111111)
//
//                        val newPixel =  (red    or 0b00000000000000000000000000000001) shl 16 or
//                                        (green  or 0b00000000000000000000000000000001) shl 8 or
//                                        (blue   or 0b00000000000000000000000000000001)

                        image.setRGB(x, y, newColor.rgb)

                    }
                }



                ImageIO.write(image, "png", File(outputFileName))
                println("Image $outputFileName is saved.")
                continue
            }
            "show" -> {
                println("Obtaining message from image.")
                continue
            }
            else -> {
                println("Wrong task: [$command]")
                continue
            }
        }
    }
}

