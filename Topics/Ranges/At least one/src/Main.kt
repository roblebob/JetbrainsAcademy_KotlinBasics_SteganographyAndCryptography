fun main() {
    val numbers = mutableListOf<Int>()
    repeat(5) {
        numbers.add(readln().toInt())
    }
    println(numbers.last() in numbers.first()..numbers[1] || numbers.last() in numbers[2]..numbers[3])
}