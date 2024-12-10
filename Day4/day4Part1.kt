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

    // For each line in wordSearch, we find the index of all 'X' characters and validate the word XMAS in all directions
    for (lineIndex in wordSearch.indices) {
        val line = wordSearch[lineIndex]
        for (charIndex in line.indices) {
            if (line[charIndex] == 'X') {
                if (validateHorizontalXMAS(wordSearch, lineIndex, charIndex)) countX++
                if (validateBackwardsHorizontalXMAS(wordSearch, lineIndex, charIndex)) countX++
                if (validateVerticalXMAS(wordSearch, lineIndex, charIndex)) countX++
                if (validateBackwardsVerticalXMAS(wordSearch, lineIndex, charIndex)) countX++
                if (validateDiagonalDownXMAS(wordSearch, lineIndex, charIndex)) countX++
                if (validateBackwardsDiagonalDownXMAS(wordSearch, lineIndex, charIndex)) countX++
                if (validateDiagonalUpXMAS(wordSearch, lineIndex, charIndex)) countX++
                if (validateBackwardsDiagonalUpXMAS(wordSearch, lineIndex, charIndex)) countX++
            }
        }
    }

    return countX
}

fun validateHorizontalXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the next 3 characters are M, A, and S
    return if (xCharIndex + 3 < wordSearch[lineIndex].length) {
        wordSearch[lineIndex][xCharIndex + 1] == 'M' &&
        wordSearch[lineIndex][xCharIndex + 2] == 'A' &&
        wordSearch[lineIndex][xCharIndex + 3] == 'S'
    } else {
        false
    }
}

fun validateBackwardsHorizontalXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the previous 3 characters are M, A, and S
    return if (xCharIndex - 3 >= 0) {
        wordSearch[lineIndex][xCharIndex - 1] == 'M' &&
        wordSearch[lineIndex][xCharIndex - 2] == 'A' &&
        wordSearch[lineIndex][xCharIndex - 3] == 'S'
    } else {
        false
    }
}

fun validateVerticalXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the next 3 characters in the next lines are M, A, and S
    return if (lineIndex + 3 < wordSearch.size) {
        wordSearch[lineIndex + 1][xCharIndex] == 'M' &&
        wordSearch[lineIndex + 2][xCharIndex] == 'A' &&
        wordSearch[lineIndex + 3][xCharIndex] == 'S'
    } else {
        false
    }
}

fun validateBackwardsVerticalXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the previous 3 characters in the previous lines are M, A, and S
    return if (lineIndex - 3 >= 0) {
        wordSearch[lineIndex - 1][xCharIndex] == 'M' &&
        wordSearch[lineIndex - 2][xCharIndex] == 'A' &&
        wordSearch[lineIndex - 3][xCharIndex] == 'S'
    } else {
        false
    }
}

fun validateDiagonalDownXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the next character in the next line is in position+1, then the next one in position+2, and the last one in position+3
    return if (lineIndex + 3 < wordSearch.size && xCharIndex + 3 < wordSearch[lineIndex].length) {
        wordSearch[lineIndex + 1][xCharIndex + 1] == 'M' &&
        wordSearch[lineIndex + 2][xCharIndex + 2] == 'A' &&
        wordSearch[lineIndex + 3][xCharIndex + 3] == 'S'
    } else {
        false
    }
}

fun validateBackwardsDiagonalDownXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the previous character in the previous line is in position-1, then the previous one in position-2, and the last one in position-3
    return if (lineIndex + 3 < wordSearch.size && xCharIndex - 3 >= 0) {
        wordSearch[lineIndex + 1][xCharIndex - 1] == 'M' &&
        wordSearch[lineIndex + 2][xCharIndex - 2] == 'A' &&
        wordSearch[lineIndex + 3][xCharIndex - 3] == 'S'
    } else {
        false
    }
}

fun validateDiagonalUpXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the next character in the previous line is in position+1, then the next one in position+2, and the last one in position+3
    return if (lineIndex - 3 >= 0 && xCharIndex + 3 < wordSearch[lineIndex].length) {
        wordSearch[lineIndex - 1][xCharIndex + 1] == 'M' &&
        wordSearch[lineIndex - 2][xCharIndex + 2] == 'A' &&
        wordSearch[lineIndex - 3][xCharIndex + 3] == 'S'
    } else {
        false
    }
}

fun validateBackwardsDiagonalUpXMAS(wordSearch: MutableList<String>, lineIndex: Int, xCharIndex: Int): Boolean {
    // Check if the previous character in the next line is in position-1, then the previous one in position-2, and the last one in position-3
    return if (lineIndex - 3 >= 0 && xCharIndex - 3 >= 0) {
        wordSearch[lineIndex - 1][xCharIndex - 1] == 'M' &&
        wordSearch[lineIndex - 2][xCharIndex - 2] == 'A' &&
        wordSearch[lineIndex - 3][xCharIndex - 3] == 'S'
    } else {
        false
    }
}

fun print(value: Int) {
    println("---------------------------------------")
    println("     Day 4: Ceres Search (Part 1)")
    println("---------------------------------------")
    println("\"Looks like the Chief's not here. Next!\" One of The Historians pulls out a device and pushes the only button on it. After a brief flash, you recognize the interior of the Ceres monitoring station!")
    println()
    println("As the search for the Chief continues, a small Elf who lives on the station tugs on your shirt; she'd like to know if you could help her with her word search (your puzzle input). She only has to find one word: XMAS.")
    println()
    println("This word search allows words to be horizontal, vertical, diagonal, written backwards, or even overlapping other words. It's a little unusual, though, as you don't merely need to find one instance of XMAS - you need to find all of them.")
    println()
    println("***************************************")
    println("Number of instances of XMAS: $value")
    println("***************************************")
}