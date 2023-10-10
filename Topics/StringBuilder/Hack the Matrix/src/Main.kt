val vowels = mutableSetOf('a', 'e', 'i', 'o', 'u'). apply { this.addAll( this.map { it.uppercaseChar() }) }

fun decodeMessage(encodedMessage: String): String {
    return buildString {
        for (c in encodedMessage) {
            when (c) {
                in vowels  -> append(c)
                else -> c.isLowerCase().let { if (it) append(c.uppercaseChar())}
            }
        }
    }
}