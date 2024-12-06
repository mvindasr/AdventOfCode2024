import java.io.File
import kotlin.math.abs

fun main() {
    val reportList = readReports("day2Input.txt")
    val safeReports = countSafeReports(reportList)

    printReport(safeReports)
}

fun readReports(fileName: String): MutableList<Array<Int>> {
    val reportList = mutableListOf<Array<Int>>()
    val file = File(fileName)
    file.forEachLine { line ->
        val parts = line.split(" ")
        val report = parts.map { it.toInt() }.toTypedArray()
        reportList.add(report)
    }
    return reportList
}

fun defineOrder(first: Int, second: Int): String {
    return when {
        first > second -> "desc"
        first < second -> "asc"
        else -> ""
    }
}

fun isSafeReport(report: Array<Int>): Boolean {
    val order = defineOrder(report[0], report[1])
    if (order.isEmpty()) return false

    for (i in 0 until report.size - 1) {
        if (abs(report[i] - report[i + 1]) > 3) {
            return false
        }
        if (order == "asc" && report[i] >= report[i + 1]) {
            return false
        } else if (order == "desc" && report[i] <= report[i + 1]) {
            return false
        }
    }
    return true
}

fun isSafeWithTolerance(report: Array<Int>): Boolean {
    if (isSafeReport(report)) return true

    for (i in report.indices) {
        val newReport = report.filterIndexed { index, _ -> index != i }.toTypedArray()
        if (isSafeReport(newReport)) {
            return true
        }
    }
    return false
}

fun countSafeReports(reportList: MutableList<Array<Int>>): Int {
    var safeReports = 0

    reportList.forEach { report ->
        val isSafe = isSafeWithTolerance(report)
        println("${report.joinToString(" ")} isSafe: $isSafe")
        if (isSafe) {
            safeReports += 1
        }
    }

    return safeReports
}

fun printReport(safeReports: Int) {
    println("---------------------------------------")
    println("   Day 2: Red-Nosed Reports (Part 2)")
    println("---------------------------------------")
    println("The engineers are surprised by the low number of safe reports until they realize they forgot to tell you about the Problem Dampener.")
    println()
    println("The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a single bad level in what would otherwise be a safe report. It's like the bad level never happened!")
    println()
    println("Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe, the report instead counts as safe.")
    println("Update your analysis by handling situations where the Problem Dampener can remove a single level from unsafe reports.")
    println()
    println("***************************************")
    println("Number of safe reports: $safeReports")
    println("***************************************")
}