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

    val distances = mutableListOf<Int>()
     while (leftList.isNotEmpty() && rightList.isNotEmpty()) {
        val minLeft = leftList.minOrNull() ?: break
        val minRight = rightList.minOrNull() ?: break

        val distance = kotlin.math.abs(minLeft - minRight)
        distances.add(distance)

        leftList.remove(minLeft)
        rightList.remove(minRight)
    }

    val totalDistance = distances.sum()

    println("---------------------------------------")
    println("Day 1: Historian Hysteria - Part 1")
    println("---------------------------------------")

    println("The Chief Historian is always present for the big Christmas sleigh launch, but nobody has seen him in months!")
    println("Last anyone heard, he was visiting locations that are historically significant to the North Pole;")
    println("a group of Senior Historians has asked you to accompany them as they check the places they think he was most likely to visit.")
    println("Pair up the smallest number in the left list with the smallest number in the right list, then the second-smallest left number with the second-smallest right number, and so on.")
    println()
    println("Within each pair, figure out how far apart the two numbers are; you'll need to add up all of those distances.")
    println("For example, if you pair up a 3 from the left list with a 7 from the right list, the distance apart is 4; if you pair up a 9 with a 3, the distance apart is 6.")
    println()
    println("To find the total distance between the left list and the right list, add up the distances between all of the pairs you found.")
    println("In the example above, this is 2 + 1 + 0 + 1 + 2 + 5, a total distance of 11!")
    println()
    println("***************************************")
    println("Total Distance: $totalDistance")
    println("***************************************")
}