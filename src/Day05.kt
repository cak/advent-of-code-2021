fun main() {
    fun part1(input: List<String>): Int {
        val cords = input
            .map {
                it.split(" -> ")
                    .flatMap { c -> c.split(",").zipWithNext() }
            }
            .flatMap { cord ->
                val x = cord[0].first.toIntOrNull() ?: 0
                val y = cord[0].second.toIntOrNull() ?: 0
                var range = listOf<Pair<Int, Int>>()

                if (cord[0].first == cord[1].first || cord[0].second == cord[1].second) {

                    if (cord[0].first != cord[1].first) {
                        val sorted = listOf(cord[0].first.toIntOrNull() ?: 0, cord[1].first.toIntOrNull() ?: 0)
                            .sorted()

                        range = (sorted[0]..sorted[1]).map { Pair(it, y) }
                    }

                    if (cord[0].second != cord[1].second) {
                        val sorted = listOf(cord[0].second.toIntOrNull() ?: 0, cord[1].second.toIntOrNull() ?: 0)
                            .sorted()

                        range = (sorted[0]..sorted[1]).map { Pair(x, it) }
                    }

                }
                range


            }

        return cords.groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    fun part2(input: List<String>): Int {
        val cords = input
            .map { c ->
                c.split(" -> ")
                    .flatMap { c -> c.split(",").mapNotNull { it.toIntOrNull() }.zipWithNext() }
            }
            .flatMap { cord ->


                val x = cord[0].first
                val y = cord[0].second

                var range = listOf<Pair<Int, Int>>()

                if (cord[0].first == cord[1].first) {
                    val sorted = listOf(cord[0].second, cord[1].second)
                        .sorted()

                    range = ((sorted[0]..sorted[1]).map { Pair(x, it) })

                } else if (cord[0].second == cord[1].second) {

                    val sorted = listOf(cord[0].first, cord[1].first).sorted()
                    range = ((sorted[0]..sorted[1]).map { Pair(it, y) })

                } else {

                    val list: MutableList<Pair<Int, Int>> = mutableListOf()

                    val xD = if (cord[0].first > cord[1].first) -1 else 1
                    val yD = if (cord[0].second > cord[1].second) -1 else 1


                    var curr = cord[0]
                    list.add(curr)
                    while (curr != cord[1]) {
                        val r = Pair(curr.first + xD, curr.second + yD)
                        list.add(r)
                        curr = r
                    }

                    range = list.toList()


                }

                range

            }


        return cords.groupingBy { it }.eachCount().filter { it.value > 1 }.count()

    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
