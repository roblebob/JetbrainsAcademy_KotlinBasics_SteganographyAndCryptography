import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

const val N = 200
const val R = 100


fun drawCircles(): BufferedImage {
    val map = mapOf<Color, Pair<Int, Int>>(
        Color.RED to Pair(50, 50),
        Color.YELLOW to Pair(50, 75),
        Color.GREEN to Pair(75, 50),
        Color.BLUE to Pair(75, 75)
    )


    val image = BufferedImage(N, N, BufferedImage.TYPE_INT_RGB)
    val graphics = image.createGraphics()


    for (color in map.keys) {
        graphics.color = color
        graphics.drawOval(map[color]!!.first, map[color]!!.second, R, R)
    }


    return image
}



//fun main() {
//    val imageFile = File("image.png")
//    val image = drawCircles()
//    ImageIO.write(image, "png", imageFile)
//}