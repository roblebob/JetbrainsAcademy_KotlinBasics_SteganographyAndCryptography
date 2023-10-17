fun countStrings(list: List<Any>): Int {
    // make your code here
    return list.count { it is String }
}