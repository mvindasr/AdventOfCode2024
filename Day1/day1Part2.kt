import java.io.File

fun main() {

    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    val file = File("day1Input.txt")
    file.forEachLine { line ->
        val parts = line.split("   ")
        if (parts.size == 2) {
            leftList.add(parts[0].toInt())
            rightList.add(parts[1].toInt())
        }
    }

    val similarityScores = mutableListOf<Int>()
    while (leftList.isNotEmpty()) {
        val number = leftList.removeAt(0)
        val count = rightList.count { it == number }
        val score = number * count
        similarityScores.add(score)
    }

    val totalSimilarityScore = similarityScores.sum()

     println("---------------------------------------")
    println("Day 1: Historian Hysteria (Part 2)")
    println("---------------------------------------")
    println("The Historians can't agree on which group made the mistakes or how to read most of the Chief's handwriting,")
    println("but in the commotion you notice an interesting detail: a lot of location IDs appear in both lists!")
    println("Maybe the other numbers aren't location IDs at all but rather misinterpreted handwriting.")
    println()
    println("This time, you'll need to figure out exactly how often each number from the left list appears in the right list.")
    println("Calculate a total similarity score by adding up each number in the left list after multiplying it by the number of times that number appears in the right list.")
    println()
    println("***************************************")
    println("Total Similarity Score: $totalSimilarityScore")
    println("***************************************")

}
