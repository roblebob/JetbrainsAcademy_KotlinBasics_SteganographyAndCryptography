fun invokeMethods(t1: Thread, t2: Thread, t3: Thread) {
    try {
        // start passed instances here
        val threads = listOf(t1, t2, t3).reversed()
        threads.forEach {
            it.start()
            it.join()
        }


    } catch (e: InterruptedException) {
        println("Exception in thread \"main\" java.lang.RuntimeException: All threads must be terminated before ending the implemented method")
    }
}