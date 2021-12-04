fun main() {
    fun part1(input: List<String>): Int = input.mapNotNull { it.toIntOrNull() }.filterIndexed { index, i ->
        input.getOrNull(index - 1)?.toIntOrNull()?.let { i > it } ?: false
    }.count()


    fun part2(input: List<String>): Int {
        val intInput = input.mapNotNull { it.toIntOrNull() }
        return intInput.filterIndexed { index, _ ->
            if (index >= 3) {
                intInput.slice(index - 3 until index).sum() < intInput.slice(index - 2 until index + 1).sum()
            } else {
                false
            }
        }.count()
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
