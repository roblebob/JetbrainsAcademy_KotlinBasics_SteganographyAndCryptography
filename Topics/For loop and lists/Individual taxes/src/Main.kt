fun main() {
    val n = readln().toInt()
    val incomes = MutableList<Int>(n) { readln().toInt() }
    val taxesPercent = MutableList<Double>(n) { readln().toDouble() }

    val taxesAbsolute = MutableList<Double>(n) { 0.0 }

    for (i in 0 until n) {
        taxesAbsolute[i] = incomes[i] * taxesPercent[i] * 0.01
    }

    val max = taxesAbsolute.maxOrNull()

    for (i in taxesAbsolute.indices) {
        if (taxesAbsolute[i] == max) {
            println(i + 1)
            break
        }
    }
}