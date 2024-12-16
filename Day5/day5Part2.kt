import java.io.File

fun main() {
    val rules = getPageRules("day5Input.txt")
    val updates = getUpdates("day5Input.txt")
    val incorrectlyOrderedUpdates = getIncorrectlyOrderedUpdates(rules, updates)
    val orderedUpdatesMiddlePagesSum = getOrderedUpdatesMiddlePagesSum(rules, incorrectlyOrderedUpdates)
    print(orderedUpdatesMiddlePagesSum)
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

fun getIncorrectlyOrderedUpdates(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): List<List<Int>> {
    val incorrectlyOrderedUpdates = mutableListOf<List<Int>>()
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
        if (!isCorrectlyOrdered) {
            incorrectlyOrderedUpdates.add(update)
        }
    }
    return incorrectlyOrderedUpdates
}

fun getOrderedUpdatesMiddlePagesSum(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
    var sum = 0
    for (update in updates) {
        val orderedUpdate = reorderUpdate(rules, update)
        val middlePageIndex = orderedUpdate.size / 2
        sum += orderedUpdate[middlePageIndex]
    }
    return sum
}

fun reorderUpdate(rules: List<Pair<Int, Int>>, update: List<Int>): List<Int> {
    val orderedUpdate = update.toMutableList()
    var changed: Boolean
    do {
        changed = false
        for (i in 0 until orderedUpdate.size - 1) {
            val beforePage = orderedUpdate[i]
            val afterPage = orderedUpdate[i + 1]
            if (rules.any { it.first == afterPage && it.second == beforePage }) {
                orderedUpdate[i] = afterPage
                orderedUpdate[i + 1] = beforePage
                changed = true
            }
        }
    } while (changed)
    return orderedUpdate
}

fun print(value: Int) {
    println("---------------------------------------")
    println("     Day 5: Print Queue (Part 2)")
    println("---------------------------------------")
    println("Safety protocols clearly indicate that new pages for the safety manuals must be printed in a very specific order. The notation X|Y means that if both page number X and page number Y are to be produced as part of an update, page number X must be printed at some point before page number Y.")
    println()
    println("The Elf has for you both the page ordering rules and the pages to produce in each update (your puzzle input), but can't figure out whether each update has the pages in the right order.")
    println()
    println("For some reason, the Elves also need to know the middle page number of each update being printed. Because you are currently only printing the correctly-ordered updates, you will need to find the middle page number of each correctly-ordered update.")
    println()
    println("Determine which updates are already in the correct order. What do you get if you add up the middle page number from those correctly-ordered updates?")
    println()
    println("While the Elves get to work printing the correctly-ordered updates, you have a little time to fix the rest of them.")
    println()
    println("For each of the incorrectly-ordered updates, use the page ordering rules to put the page numbers in the right order.")
    println()
    println("Find the updates which are not in the correct order. What do you get if you add up the middle page numbers after correctly ordering just those updates?")
    println()
    println("***************************************")
    println("Ordered Updates Middle-Page Sum: $value")
    println("***************************************")
}