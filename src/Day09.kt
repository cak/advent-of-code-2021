fun main() {
    fun part1(input: List<String>): Int {
        val lowPoints = mutableListOf<Int>()
        val points = input.map { p -> p.split("").mapNotNull { it.toIntOrNull() } }

        for ((gi, g) in points.withIndex()) {
            for ((pi, p) in g.withIndex()) {
                if (pi < g.size - 1) {
                    if (p >= g[pi + 1]) {
                        continue
                    }
                }

                if (pi > 0) {
                    if (p >= g[pi - 1]) {
                        continue
                    }
                }

                if (gi > 0) {
                    if (p >= points[gi - 1][pi]) {
                        continue
                    }
                }

                if (gi < points.size - 1) {
                    if (p >= points[gi + 1][pi]) {
                        continue
                    }
                }

                lowPoints.add(p)
            }
        }
        
        return lowPoints.map { it + 1 }.sum()
    }


    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    //check(part2(testInput) == 5)
    val input = readInput("Day09")
    println(part1(input))
    //println(part2(input))
}
