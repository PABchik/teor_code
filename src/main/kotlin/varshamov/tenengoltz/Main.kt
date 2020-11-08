package varshamov.tenengoltz

import kotlin.math.pow
import kotlin.math.roundToInt

fun main() {
    val n = readLine()!!.toInt()
    val validNlenCodeWords = getAvailableCodes(n)
    print("All valid code words with length $n : $validNlenCodeWords");

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
