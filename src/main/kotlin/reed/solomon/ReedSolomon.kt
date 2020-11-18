package reed.solomon

import kotlin.collections.HashMap
import kotlin.math.pow

val m = 8
val aMap: Map<Int, String> = genA8()
val inversedAMap: Map<String, String> = getInverAMap(aMap)

fun getInverAMap(aMap: Map<Int, String>): Map<String, String> {
    val inverMap = HashMap<String, String>()
    for (entry in aMap.entries)
        inverMap[entry.value] = entry.key.toString()
    return inverMap
}

fun main() {
    val inMessage = readLine()!!.padStart(m, '0')

    val code = encode(inMessage)

}

fun encode(inMessage: String): String {
    var code = "";
    for (i in 0..254) {
        var tempCode = inMessage[0].toInt()
        for (j in inMessage.subSequence(1..inMessage.lastIndex)) {
            if (j != '0')
                tempCode = tempCode.xor(aMap[j.toInt() * i % 255]!!.toInt(2))
        }

        code += inversedAMap[Integer.toBinaryString(tempCode).padStart(m, '0')] + " "
    }
    return code.substring(0, code.lastIndex)
}

fun genA8(): Map<Int, String> {
    val map = HashMap<Int, String>()

    for (i in 0..7)
        map[i] = Integer.toBinaryString(2.0.pow(7.0 - i).toInt()).padStart(m, '0')

    for (i in 8..254) {
        map[i] = Integer.toBinaryString(Integer.parseInt(map[i - 8], 2)
                .xor(Integer.parseInt(map[i - 6], 2))
                .xor(Integer.parseInt(map[i - 5], 2))
                .xor(Integer.parseInt(map[i - 4], 2))).padStart(m, '0')
    }
    return map
}