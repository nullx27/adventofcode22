import java.io.File
import java.nio.charset.Charset

fun main() {

    val totalCaloriesPerElf = File("src/main/resources/day1.txt")
        .readText(Charset.defaultCharset())
        .split(Regex("(?m)^\\s*\$"))
        .filter { it.isNotBlank() && it.isNotEmpty() }
        .map { chunks ->
            chunks.split("\r\n")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
                .reduce { item, next -> item + next }
        }

    println("Part 1: ${totalCaloriesPerElf.maxOf { it }}")
    println("Part 2: ${totalCaloriesPerElf.sorted().takeLast(3).reduce { acc, i -> acc + i }}")
}