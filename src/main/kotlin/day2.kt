import java.io.File
import java.nio.charset.Charset

fun main() {
    val points = mapOf("X" to 1, "Y" to 2, "Z" to 3)
    val win = 6
    val draw = 3
    val lose = 0

    val games = File("src/main/resources/day2.txt")
        .readText(Charset.defaultCharset())
        .split("\r\n")

    val part1 = games.sumOf { line ->
        val moves = (line.split(" "))

        points[moves.last()]!! + when (moves) {
            listOf("A", "Y") -> win
            listOf("B", "Z") -> win
            listOf("C", "X") -> win
            listOf("A", "X") -> draw
            listOf("B", "Y") -> draw
            listOf("C", "Z") -> draw
            else -> lose
        }
    }

    val part2 = games.sumOf { line ->
        val moves = (line.split(" "))

        when (moves.last()) {
            "X" -> lose + when (moves.first()) {
                "A" -> points["Z"]
                "B" -> points["X"]
                "C" -> points["Y"]
                else -> 0
            }!!

            "Y" -> draw + when (moves.first()) {
                "A" -> points["X"]
                "B" -> points["Y"]
                "C" -> points["Z"]
                else -> 0
            }!!

            "Z" -> win + when (moves.first()) {
                "A" -> points["Y"]
                "B" -> points["Z"]
                "C" -> points["X"]
                else -> 0
            }!!

            else -> 0
        }
    }

    println("Part 1: $part1")
    println("Part 2: $part2")
}