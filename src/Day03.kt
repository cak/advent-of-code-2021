fun main() {
    fun part1(input: List<String>): Int {
        val twoDim = input.map { l ->
            l.split("").filter { it.isNotBlank() }
        }

        val size = twoDim.firstOrNull()?.size ?: 0

        val zipped = (0 until size).map { i ->
            twoDim.map { it[i] }
        }.map { b -> b.groupingBy { it }.eachCount() }

        val gammaRate =
            zipped.map { m -> m.maxByOrNull { it.value } }.mapNotNull { it?.key }.joinToString("").toInt(2)

        val epsilonRate =
            zipped.map { m -> m.minByOrNull { it.value } }.mapNotNull { it?.key }.joinToString("").toInt(2)

        return gammaRate * epsilonRate
    }


    fun part2(input: List<String>): Int {
        val twoDim = input.map { l ->
            l.split("").filter { it.isNotBlank() }
        }

        val size = twoDim.firstOrNull()?.size ?: 0

        var maxList = twoDim.reversed()

        (0 until size).forEach {
            val zipped = (0 until size).map { i ->
                maxList.map { it[i] }
            }.map { b -> b.groupingBy { it }.eachCount() }

            val max =
                zipped.map { m -> m.toSortedMap(Comparator.reverseOrder()).maxByOrNull { it.value } }
                    .mapNotNull { it?.key }

            maxList = maxList.filter { t ->
                t[it] == max[it]
            }
        }


        var minList = twoDim

        (0 until size).forEach {
            val zipped = (0 until size).map { i ->
                minList.map { it[i] }
            }.map { b -> b.groupingBy { it }.eachCount() }


            val min =
                zipped.map { m -> m.toSortedMap().minByOrNull { it.value } }
                    .mapNotNull { it?.key }
            minList = minList.filter { t ->
                t[it] == min[it]
            }
        }

        val oxygen = maxList.first().joinToString("").toInt(2)
        val co2 = minList.first().joinToString("").toInt(2)

        return oxygen * co2
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
