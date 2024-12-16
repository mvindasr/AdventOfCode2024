import java.io.File

fun main() {
    val rules = getPageRules("day5Input.txt")
    val updates = getUpdates("day5Input.txt")
    val middlePagesSum = getCorrectlyOrderedMiddlePagesSum(rules, updates)
    print(middlePagesSum)
}

fun getPageRules(fileName: String): List<Pair<Int, Int>> {
    val rules = mutableListOf<Pair<Int, Int>>()
    val file = File(fileName)
    file.forEachLine { line ->
        if (line.isBlank()) return@forEachLine
        val parts = line.split("|")
        if (parts.size == 2) {
            val beforePage = parts[0].trim().toInt()
            val afterPage = parts[1].trim().toInt()
            rules.add(Pair(beforePage, afterPage))
        }
    }
    return rules
}

fun getUpdates(fileName: String): List<List<Int>> {
    val updates = mutableListOf<List<Int>>()
    val file = File(fileName)
    var startReadingUpdates = false
    file.forEachLine { line ->
        if (line.isBlank()) {
            startReadingUpdates = true
            return@forEachLine
        }
        if (startReadingUpdates) {
            val numbers = line.split(",").map { it.trim().toInt() }
            updates.add(numbers)
        }
    }
    return updates
}

fun getCorrectlyOrderedMiddlePagesSum(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
    var sum = 0
    for (update in updates) {
        var isCorrectlyOrdered = true
        for (i in 0 until update.size - 1) {
            val beforePage = update[i]
            val afterPage = update[i + 1]
            if (rules.any { it.first == afterPage && it.second == beforePage }) {
                isCorrectlyOrdered = false
                break
            }
        }
        if (isCorrectlyOrdered) {
            val middlePageIndex = update.size / 2
            sum += update[middlePageIndex]
        }
    }
    return sum
}

fun print(value: Int) {
    println("---------------------------------------")
    println("     Day 5: Print Queue (Part 1)")
    println("---------------------------------------")
    println("Safety protocols clearly indicate that new pages for the safety manuals must be printed in a very specific order. The notation X|Y means that if both page number X and page number Y are to be produced as part of an update, page number X must be printed at some point before page number Y.")
    println()
    println("The Elf has for you both the page ordering rules and the pages to produce in each update (your puzzle input), but can't figure out whether each update has the pages in the right order.")
    println()
    println("For some reason, the Elves also need to know the middle page number of each update being printed. Because you are currently only printing the correctly-ordered updates, you will need to find the middle page number of each correctly-ordered update.")
    println()
    println("Determine which updates are already in the correct order. What do you get if you add up the middle page number from those correctly-ordered updates?")
    println()
    println("***************************************")
    println("Correctly Ordered Middle-Page Sum: $value")
    println("***************************************")
}