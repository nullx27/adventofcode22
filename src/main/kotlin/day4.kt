import java.io.File
import java.nio.charset.Charset

fun main() {

    val input = File("src/main/resources/day4.txt")
        .readText(Charset.defaultCharset())
        .split("\r\n")
        .map { sections ->
            sections
                .split(",")
                .map { it.split("-").map { i -> i.toInt() } }
        }
        .map { sections ->
            sections.map { (it.first().toInt()..it.last().toInt()).toSet() }
        }

    val part1 = input.count {
        it.first().containsAll(it.last()) || it.last().containsAll(it.first())
    }

    val part2 = input.count {
        it.first().intersect(it.last()).isNotEmpty()
    }

    println("Part 1: $part1")
    println("Part 2: $part2")

}