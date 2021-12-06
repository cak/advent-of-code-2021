fun main() {
    fun calculateFish(fish: List<Long>, size: Int): List<Long> = (0 until size)
        .fold(fish to fish) { (_, curr), _ ->
            val newFish = mutableListOf<Long>()
            val currentFish = curr.map {
                if (it == 0.toLong()) {
                    newFish.add(8)
                    6
                } else {
                    it - 1
                }
            }.toList()
            curr to listOf<List<Long>>(newFish, currentFish).flatten()
        }.second


    fun part1(input: List<String>): Int {
        val lanternfish = input.firstOrNull()?.split(",")?.mapNotNull { it.toLongOrNull() } ?: listOf<Long>()
        return calculateFish(lanternfish, 80).count()
    }

    fun part2(input: List<String>): Long {
        val lanternfish = input.firstOrNull()?.split(",")?.mapNotNull { it.toLongOrNull() } ?: listOf<Long>()
        return calculateFish(lanternfish, 256).count().toLong()
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    //check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
   //println(part2(input))
}
