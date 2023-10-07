class SomeCollection<T>(val list: List<T>) {
    // write your code here
    fun invert(): SomeCollection<T> {
        val outList = list.reversed().toList()
        println(outList)
        return SomeCollection(outList)
    }
}