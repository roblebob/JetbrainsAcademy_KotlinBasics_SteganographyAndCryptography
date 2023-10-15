import java.util.Collections

fun main() {
    // write your code here
    val n = readln().toInt()
    val list = MutableList<Int>(n) { readln().toInt() }
    val nRightRotate = readln().toInt()

    Collections.rotate(list, nRightRotate)

    println(list.joinToString().replace(",", ""))
}