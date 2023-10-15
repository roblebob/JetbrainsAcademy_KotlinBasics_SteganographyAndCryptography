fun solution(elements: Set<String>, element: String): MutableSet<String> {
    return elements.toMutableSet().apply { remove(element) }
}