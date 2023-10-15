fun main() {
    val list = readln().split(" ")
    // write your code
    val set = list.toMutableSet()
    val map = mutableMapOf<String, Int>()
    for (word in set) {
        map[word] = list.count { it == word }
    }
    map.forEach { println("${it.key} ${it.value}") }
}