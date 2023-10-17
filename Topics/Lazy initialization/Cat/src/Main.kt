class Cat {
    val name: String by lazy {
        println("I prefer to ignore it")
        callName()
    }

    fun callName(): String {
        println("Input the cat name")
        return readln()
    }
}