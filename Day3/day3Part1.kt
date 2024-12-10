import java.io.File

fun main() {
    val programLines = readText("day3Input.txt")
    var sumMul = 0
    val numberMulPairs = mutableListOf<Pair<Int, Int>>()

    processCorruptedCode(programLines, numberMulPairs)

    numberMulPairs.forEach { pair ->
        val result = mul(pair.first, pair.second)
        sumMul += result
    }

    print(sumMul);
}

fun readText(fileName: String): MutableList<String> {
    val codeLines = mutableListOf<String>()
    val file = File(fileName)
    file.forEachLine { line ->
        codeLines.add(line)
    }
    return codeLines
}

fun mul(a: Int, b: Int): Int {
    return a * b
}

fun processCorruptedCode(programLines: MutableList<String>, numberPairs: MutableList<Pair<Int, Int>>) {
    val regex = Regex("""mul\((\d+),(\d+)\)""")
    programLines.forEach { line ->
        regex.findAll(line).forEach { matchResult ->
            val (first, second) = matchResult.destructured
            val firstInt = first.toIntOrNull()
            val secondInt = second.toIntOrNull()
            if (firstInt != null && secondInt != null) {
                numberPairs.add(Pair(firstInt, secondInt))
            }
        }
    }
}

fun print(value: Int) {
    println("---------------------------------------")
    println("     Day 3: Mull It Over (Part 1)")
    println("---------------------------------------")
    println("Our computers are having issues, so I have no idea if we have any Chief Historians in stock! You're welcome to check the warehouse, though,\" says the mildly flustered shopkeeper at the North Pole Toboggan Rental Shop. The Historians head out to take a look.")
    println()
    println("The shopkeeper turns to you. \"Any chance you can see why our computers are having issues again?\"")
    println()
    println("The computer appears to be trying to run a program, but its memory (your puzzle input) is corrupted. All of the instructions have been jumbled up!")
    println()
    println("It seems like the goal of the program is just to multiply some numbers. It does that with instructions like mul(X,Y), where X and Y are each 1-3 digit numbers. For instance, mul(44,46) multiplies 44 by 46 to get a result of 2024. Similarly, mul(123,4) would multiply 123 by 4.")
    println()
    println("However, because the program's memory has been corrupted, there are also many invalid characters that should be ignored, even if they look like part of a mul instruction. Sequences like mul(4*, mul(6,9!, ?(12,34), or mul ( 2 , 4 ) do nothing.")
    println("Scan the corrupted memory for uncorrupted mul instructions. What do you get if you add up all of the results of the multiplications?")
    println()
    println("***************************************")
    println("Sum of all mul operations: $value")
    println("***************************************")
}