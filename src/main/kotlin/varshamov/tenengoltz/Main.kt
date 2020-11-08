package varshamov.tenengoltz

import sun.util.calendar.CalendarUtils.mod
import kotlin.math.pow
import kotlin.math.roundToInt

fun main() {
    val n = readLine()!!.toInt()
    val validNlenCodeWords = getAvailableCodes(n)
    println("All valid code words with length $n : $validNlenCodeWords")
    val receivedWord = readLine()!!
    println("Corrected word is ${correctWord(receivedWord, n)}")
}

fun correctWord(receivedWord: String, n: Int): String {
    if (receivedWord.isEmpty() || receivedWord.length !in arrayOf(n - 1, n + 1)) {
        println("Cannot correct received word")
        return receivedWord
    }
    when (receivedWord.length) {
        n + 1 -> return delFun(receivedWord)
        n - 1 -> return addFun(receivedWord)
    }
    return receivedWord
}

fun addFun(word: String): String {
    val T = mod(-countS(word), word.length + 2)
    val weight = word.replace("0", "").length
    return when (T) {
        in 0..weight -> addSym(word, T, '1')
        in weight + 1..word.length + 1 -> addSym(word, word.length + 1 - T, '0')
        else -> word
    }
}

fun addSym(word: String, T: Int, sym: Char): String {
    val index = getIndex(word, T, sym)
    return (word.reversed().slice(0 until index) + (if (sym == '0') "1" else "0") + word.reversed().slice(index..word.lastIndex)).reversed()
}

fun delFun(word: String): String {
    val T = mod(countS(word), word.length)
    val weight = word.replace("0", "").length
    return when (T) {
        0 -> word.slice(0 until word.lastIndex)
        weight -> word.slice(1..word.lastIndex)
        in 0 until weight -> delSym(word, T, '1')
        in weight + 1..word.length -> delSym(word, word.length - T, '0')
        else -> word
    }
}

fun delSym(word: String, T: Int, sym: Char): String {
    val rmIndex = getIndex(word, T, sym)
    return word.reversed().removeRange(rmIndex..rmIndex).reversed()
}

fun getIndex(word: String, T: Int, sym: Char): Int {
    var t = T
    var index = 0
    if (t != 0) {
        for ((i, ch) in word.reversed().withIndex()) {
            if (ch == sym && t != 0) {
                if (--t == 0) {
                    index = i + 1
                    break
                }
            }
        }
    }
    return index
}

fun getAvailableCodes(n: Int): List<String> {
    val availableCodes = ArrayList<String>()
    for (i in 0 until 2.0.pow(n.toDouble()).roundToInt()) {
        val code = Integer.toBinaryString(i).padStart(n, '0')
        if (countS(code) % (n + 1) == 0) availableCodes.add(code)
    }
    return availableCodes
}

fun countS(code: String): Int {
    var result = 0
    code.forEachIndexed {i, num -> result+= num.toString().toInt() * (i + 1)}
    return result
}
