fun main() {
    fun checkBingo(board: List<List<String>>): Boolean {
        if (board.any { i -> i.all { it == "X" } }) {
            return true
        }

        if (board.none { i -> i.any { it == "X" } }) {
            return false
        }

        val spotCount = 0

        for (i in 0 until 5) {
            if (board.map { it[i] }.all { it == "X" }) {
                return true
            }
        }

        return false


    }

    fun part1(input: List<String>): Int {
        val drawn = input.take(1).flatMap { it.split(",") }

        var boards =
            input.subList(2, input.size).filter { it.isNotEmpty() }
                .map { n -> n.split(" ").filter { it.isNotEmpty() } }
                .chunked(5)

        var winningBoard: List<List<String>>? = null
        var winningNumber: Int? = null

        drawning@ for (n in drawn) {
            boards = boards.map { i -> i.map { o -> o.map { if (it == n) "X" else it } } }

            for (board in boards) {
                if (checkBingo(board)) {
                    winningBoard = board
                    winningNumber = n.toIntOrNull()
                    break@drawning
                }

            }
        }

        val sum = winningBoard?.sumOf { it -> it.mapNotNull { it.toIntOrNull() }.sum() } ?: 0

        return sum * (winningNumber ?: 0)
    }


    fun part2(input: List<String>): Int {
        val drawn = input.take(1).flatMap { it.split(",") }

        var boards =
            input.subList(2, input.size).filter { it.isNotEmpty() }
                .map { n -> n.split(" ").filter { it.isNotEmpty() } }
                .chunked(5).toMutableList()

        var winningBoard: List<List<String>>? = null
        var winningNumber: Int? = null

        for (n in drawn) {
            boards = boards.map { i -> i.map { o -> o.map { if (it == n) "X" else it } } }.toMutableList()

            val boardsIter = boards.iterator()
            while (boardsIter.hasNext()) {
                val board = boardsIter.next()
                if (checkBingo(board)) {
                    boardsIter.remove()
                    winningBoard = board
                    winningNumber = n.toIntOrNull()
                }
            }
        }

        val sum = winningBoard?.sumOf { b -> b.mapNotNull { it.toIntOrNull() }.sum() } ?: 0

        return sum * (winningNumber ?: 0)
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
