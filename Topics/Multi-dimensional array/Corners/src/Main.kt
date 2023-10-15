fun main() {
    //Do not touch code below
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toTypedArray()
        inputArray += strings
    }
    //write your code here
    val corners = arrayOf(
        arrayOf( inputArray[0][0],
            inputArray[0][inputArray[0].size - 1])
        ,
        arrayOf( inputArray[inputArray.size - 1][0],
            inputArray[inputArray.size - 1][inputArray[inputArray.size - 1].size - 1])
    )

    for (e in corners) {
        println( e.joinToString(separator = " "))
    }

}