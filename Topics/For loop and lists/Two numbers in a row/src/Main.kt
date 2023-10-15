fun main() {
    // write your code here
    val n = readLine()!!.toInt()
    val list = MutableList<Int>(n) { readLine()!!.toInt() }
    val (p, m) = readln().split(" ").map { it.toInt() }

    var result = "YES"
    for (i in 0 until list.lastIndex) {
        if (list[i] == p && list[i + 1] == m || list[i] == m && list[i + 1] == p) {
            result = "NO"
        }
    }
    println(result)
}