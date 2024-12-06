import java.io.File
import kotlin.math.abs

fun main() {
    val reportList = mutableListOf<Array<Int>>()
    var safeReports = 0

    val file = File("day2Input.txt")
    file.forEachLine { line ->
        val parts = line.split(" ")
        val report = parts.map { it.toInt() }.toTypedArray()
        reportList.add(report)
    }


    reportList.forEach { report ->
        var isSafe = true
        var order = "" 

        order = when {
            report[0] > report[1] -> "desc"
            report[0] < report[1] -> "asc"
            else -> {
                isSafe = false
                ""
            }
        }

        if (isSafe) {
            for (i in 0 until report.size - 1) {
                if (abs(report[i] - report[i + 1]) > 3) {
                    isSafe = false
                    break
                }
                if (order == "asc" && (report[i] >= report[i + 1])) {
                    isSafe = false
                    break
                } else if (order == "desc" && (report[i] <= report[i + 1])) {
                    isSafe = false
                    break
                }
            }
        }

        if (isSafe) {
            safeReports += 1
        }
    }

    println("---------------------------------------")
    println("   Day 2: Red-Nosed Reports (Part 1)")
    println("---------------------------------------")
    println("The engineers are trying to figure out which reports are safe.")
    println("The Red-Nosed reactor safety systems can only tolerate levels that are either gradually increasing or gradually decreasing.")
    println("So, a report only counts as safe if both of the following are true:")
    println()
    println("1. The levels are either all increasing or all decreasing.")
    println("2. Any two adjacent levels differ by at least one and at most three.")
    println()
    println("Analyze the unusual data from the engineers. How many reports are safe?")
    println()
    println("***************************************")
    println("Number of safe reports: $safeReports")
    println("***************************************")
}