import java.io.File

fun main() {
    val wordSearch = readText("day4Input.txt")
    val countXMAS = countXMAS(wordSearch)
    print(countXMAS)
}

fun readText(fileName: String): MutableList<String> {
    val codeLines = mutableListOf<String>()
    val file = File(fileName)
    file.forEachLine { line ->
        codeLines.add(line)
    }
    return codeLines
}

fun countXMAS(wordSearch: MutableList<String>): Int {
    var countX = 0

    for (lineIndex in wordSearch.indices) {
        val line = wordSearch[lineIndex]
        for (charIndex in line.indices) {
            if (line[charIndex] == 'A') {
                if (validateFirstMASDiagonalXMAS(wordSearch, lineIndex, charIndex) && validateSecondMASDiagonalXMAS(wordSearch, lineIndex, charIndex)) {
                    countX++
                }
            }
        }
    }

    return countX
}

fun validateFirstMASDiagonalXMAS(wordSearch: MutableList<String>, lineIndex: Int, aCharIndex: Int): Boolean {
    // Check the previous line in position -1 for 'M' and the next line in position +1 for 'S' or vice versa
    if (lineIndex - 1 >= 0 && lineIndex + 1 < wordSearch.size && aCharIndex - 1 >= 0 && aCharIndex + 1 < wordSearch[lineIndex].length) {
        if (wordSearch[lineIndex - 1][aCharIndex - 1] == 'M' && wordSearch[lineIndex + 1][aCharIndex + 1] == 'S') {
            return true
        }
        if (wordSearch[lineIndex - 1][aCharIndex - 1] == 'S' && wordSearch[lineIndex + 1][aCharIndex + 1] == 'M') {
            return true
        }
    }
    return false
}

fun validateSecondMASDiagonalXMAS(wordSearch: MutableList<String>, lineIndex: Int, aCharIndex: Int): Boolean {
    // Check the previous line in position +1 for 'M' and the next line in position -1 for 'S' or vice versa
    if (lineIndex - 1 >= 0 && lineIndex + 1 < wordSearch.size && aCharIndex - 1 >= 0 && aCharIndex + 1 < wordSearch[lineIndex].length) {
        if (wordSearch[lineIndex + 1][aCharIndex - 1] == 'M' && wordSearch[lineIndex - 1][aCharIndex + 1] == 'S') {
            return true
        }
        if (wordSearch[lineIndex + 1][aCharIndex - 1] == 'S' && wordSearch[lineIndex - 1][aCharIndex + 1] == 'M') {
            return true
        }
    }
    return false
}

fun print(value: Int) {
    println("---------------------------------------")
    println("     Day 4: Ceres Search (Part 2)")
    println("---------------------------------------")
    println("The Elf looks quizzically at you. Did you misunderstand the assignment?")
    println()
    println("Looking for the instructions, you flip over the word search to find that this isn't actually an XMAS puzzle; it's an X-MAS puzzle in which you're supposed to find two MAS in the shape of an X.")
    println("Within the X, each MAS can be written forwards or backwards.")
    println("Flip the word search from the instructions back over to the word search side and try again. How many times does an X-MAS appear?")
    println()
    println("***************************************")
    println("Number of instances of X-MAS: $value")
    println("***************************************")
}