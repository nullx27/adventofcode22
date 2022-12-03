import java.io.File
import java.nio.charset.Charset

fun main() {

    val priority = mutableMapOf<Char, Int>()
    ('a'..'z').toSet().forEachIndexed { index, item -> priority[item] = index + 1 }
    ('A'..'Z').toSet().forEachIndexed { index, item -> priority[item] = (index + 1) + 26 }

    val rucksacks = File("src/main/resources/day3.txt")
        .readText(Charset.defaultCharset())
        .split("\r\n")

    val part1 = rucksacks.sumOf { item -> item.splitInHalf().findCommonChars().sumOf { priority[it]!! } }

    println("Part 1: $part1")

    val part2 = rucksacks.chunked(6).sumOf { chunk ->
        val chunks = chunk.chunked(3)

        chunks.first().toSet().findCommonChars().sumOf { priority[it]!! } + chunks.last().toSet().findCommonChars()
            .sumOf { priority[it]!! }
    }

    println("Part 2: $part2")
}


fun String.splitInHalf(): Set<String> {
    val mid = this.length / 2
    return setOf(this.substring(0, mid), this.substring(mid, this.length))
}

fun Set<String>.findCommonChars(): Set<Char> {
    val charactersList = arrayListOf<List<Char>>()
    this.forEach { charactersList.add(it.toCharArray().toList()) }

    val common = mutableSetOf<Char>();
    common.addAll(charactersList.first())
    charactersList.map { common.retainAll(it::contains) }

    return common
}