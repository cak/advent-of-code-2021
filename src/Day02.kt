fun main() {
    fun part1(input: List<String>): Int {
        val mappedInput = input.map { it.split(" ") }.map {
            Pair(it[0], it[1].toIntOrNull() ?: 0)
        }.fold(mutableMapOf<String, Int>()) { sum, element ->
            sum[element.first] = sum.getOrDefault(element.first, 0).plus(element.second)
            sum
        }

        val horizontal = mappedInput.getOrDefault("forward", 0)
        val depth = mappedInput["down"]?.minus(mappedInput["up"] ?: 0) ?: 0

        return horizontal * depth
    }


    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontal = 0
        var aim = 0

        input.map { it.split(" ") }.map {
            Pair(it[0], it[1].toIntOrNull() ?: 0)
        }.forEach {
            when (it.first) {
                "down" -> aim += it.second
                "up" -> aim -= it.second
                "forward" -> {
                    horizontal += it.second
                    depth += (it.second * aim)
                }
            }
        }

        return horizontal * depth
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
