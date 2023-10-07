import java.awt.Color
import java.awt.image.BufferedImage  

data class Point(val x: Int, val y: Int)


fun drawLines(): BufferedImage {
    val image = BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB)
    val graphics = image.graphics

    val map: Map<Color, Pair<Point, Point>> = mapOf(
        Color.RED to Pair(Point(0, 0), Point(image.width, image.height)),
        Color.GREEN to Pair(Point(0, image.height), Point(image.width, 0))
    )

    for (color in map.keys) {
        graphics.color = color
        graphics.drawLine(map[color]!!.first.x, map[color]!!.first.y, map[color]!!.second.x, map[color]!!.second.y)
    }

    return image
}