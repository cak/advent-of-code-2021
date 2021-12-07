fun main() {
    fun part1(input: List<String>): Int {
        val crabs = input.first().split(",").map { it.toInt() }
        val fuelCosts = mutableMapOf<Int, Int>()
        (crabs.distinct()).forEach { c ->
            val fuel = crabs.sumOf {
                if (it <= c) {
                    c -it
                 } else {
                     it - c
                }
            }
            fuelCosts[c] = fuel
        }

       return fuelCosts.toList().minByOrNull { (_, value) -> value }?.second ?: 0
    }


    fun part2(input: List<String>): Int {
        val crabs = input.first().split(",").map { it.toInt() }.sorted()
        val fuelCosts = mutableMapOf<Int, Int>()
        (crabs.first()..crabs.last()).forEach { c ->
            val fuel = crabs.sumOf { f ->
                var cost = 0
                val steps = if (f <= c) {
                   c -f
                } else {
                    f - c
                }
                (0 .. steps).map {
                    cost += it
                }
                cost
            }
            fuelCosts[c] = fuel
        }

        return fuelCosts.toList().minByOrNull { (_, value) -> value }?.second ?: 0
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
