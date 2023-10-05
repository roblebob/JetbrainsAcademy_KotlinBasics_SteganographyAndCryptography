package cryptography

fun main() {
    println("Task (hide, show, exit):")
    while (true) {
        val command = readln()
        when (command) {
            "exit" -> {
                println("Bye!")
                break
            }
            "hide" -> {
                println("Hiding message in image.")
                continue
            }
            "show" -> {
                println("Obtaining message from image.")
                continue
            }
            else -> {
                println("Wrong task: [$command]")
                continue
            }
        }
    }
}

