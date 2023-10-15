fun sumRecursive(n: Int): Int {
    // (base case / terminal condition here
    if (n == 0) return 0
    // recursive call here
    return n + sumRecursive(n - 1)
}

fun main() {
    val n = readLine()!!.toInt()
    print(sumRecursive(n))
}