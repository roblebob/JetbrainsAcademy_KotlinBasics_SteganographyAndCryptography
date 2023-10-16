fun main() {
    val list = mutableListOf<Int>()
    repeat(readln().toInt()) {
        list.add(readln().toInt())
    }
    println(if (list.contains(readln().toInt())) "YES" else "NO")
}