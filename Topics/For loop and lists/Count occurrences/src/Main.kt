fun main() {
    // write your code here
    val n = readln().toInt()
    val list = mutableListOf<Int>()
    repeat(n) {
        list.add(readln().toInt())
    }
    val m = readln().toInt()
    println(list.count { it == m })
}