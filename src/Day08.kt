fun main() {
    fun part1(input: List<String>): Int = input.mapNotNull { it.split(" | ").lastOrNull() }
        .flatMap { it.split(" ") }.count { it.length == 4 || it.length == 7 || it.length == 3 || it.length == 2 }


    fun part2(input: List<String>): Int {
        val result = input.map {s -> s.split(" | ")
            .map { t-> t.split(" ").map {  u -> u.split("").sorted().joinToString("")}} }.map { l ->
            val acc = mutableMapOf<Int, String>()
            var count = 0
            while (acc.size < 10) {
                l.first().sorted().forEach { curr ->
                    val currList = curr.split("")
                    when {
                        curr.length == 7 -> acc[8] = curr
                        curr.length == 2 -> acc[1] = curr
                        curr.length == 3 -> acc[7] = curr
                        curr.length == 4 -> acc[4] = curr
                        curr.length == 5 && acc[7]?.let { currList.containsAll(it.split("")) } == true -> acc[3] = curr
                        curr.length == 5 && acc[9]?.split("")?.containsAll(currList) == true -> acc[5] = curr
                        curr.length == 5 -> acc[2] = curr
                        curr.length == 6 && acc[3]?.let { currList.containsAll(it.split("")) } == true -> acc[9] = curr
                        curr.length == 6 && acc[7]?.let { currList.containsAll(it.split("")) } == true -> acc[0] = curr
                        curr.length == 6 -> acc[6] = curr
                    }
                }
            }

                val keys = mutableMapOf<String, Int>()
                acc.forEach { keys[it.value] = it.key }

                l.last().mapNotNull { keys[it] }.joinToString("").toInt()

        }

        return result.sum()

    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
