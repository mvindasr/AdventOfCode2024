import java.io.File

fun main() {
    val programContent = readText("day3Input.txt")
    var sumMul = 0
    val numberMulPairs = mutableListOf<Pair<Int, Int>>()

    processCorruptedCode(programContent, numberMulPairs)

    numberMulPairs.forEach { pair ->
        val result = mul(pair.first, pair.second)
        sumMul += result
    }

    print(sumMul)
}

fun readText(fileName: String): String {
    return File(fileName).readText()
}

fun mul(a: Int, b: Int): Int {
    return a * b
}

fun processCorruptedCode(programContent: String, numberPairs: MutableList<Pair<Int, Int>>) {
    val regexMul = Regex("""mul\((\d+),(\d+)\)""")
    val regexDo = Regex("""do\(\)""")
    val regexDont = Regex("""don't\(\)""")

    var ignore = false
    var doIndex = -1
    var dontIndex = -1

    regexMul.findAll(programContent).forEach { matchResult ->
        val mulIndex = matchResult.range.first

        val doMatch = regexDo.find(programContent, doIndex + 1)
        val dontMatch = regexDont.find(programContent, dontIndex + 1)

        if (doMatch != null && doMatch.range.first < mulIndex) {
            doIndex = doMatch.range.first
            ignore = false
        }

        if (dontMatch != null && dontMatch.range.first < mulIndex) {
            dontIndex = dontMatch.range.first
            ignore = true
        }

        if (ignore) {
            return@forEach
        }

        val (first, second) = matchResult.destructured
        val firstInt = first.toIntOrNull()
        val secondInt = second.toIntOrNull()
        if (firstInt != null && secondInt != null) {
            numberPairs.add(Pair(firstInt, secondInt))
        }
    }
}

fun print(value: Int) {
    println("---------------------------------------")
    println("     Day 3: Mull It Over (Part 2)")
    println("---------------------------------------")
    println("As you scan through the corrupted memory, you notice that some of the conditional statements are also still intact. If you handle some of the uncorrupted conditional statements in the program, you might be able to get an even more accurate result.")
    println()
    println("There are two new instructions you'll need to handle:")
    println()
    println("The do() instruction enables future mul instructions.")
    println("The don't() instruction disables future mul instructions.")
    println("Only the most recent do() or don't() instruction applies. At the beginning of the program, mul instructions are enabled.")
    println()
    println("Handle the new instructions; what do you get if you add up all of the results of just the enabled multiplications?")
    println()
    println("***************************************")
    println("Sum of all mul operations: $value")
    println("***************************************")
}