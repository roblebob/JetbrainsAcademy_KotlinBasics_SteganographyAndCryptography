fun main() {
    // write your code here
    val n = readln().toInt()
    val list = MutableList<Int>(n) { readln().toInt() }
    val (p, m) = readln().split(" ").map { it.toInt() }

    var result = ""
    for (el in list) {
        when (el) {
            p -> result += "p"
            m -> result += "m"
        }
    }
    println(if (result.contains('m') && result.contains('p')) "YES" else "NO")
}