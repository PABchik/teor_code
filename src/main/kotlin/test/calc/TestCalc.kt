package test.calc

import java.lang.Math.log
import java.lang.Math.pow
import kotlin.math.log2

fun main() {
    var result = 0
    for (i1 in 0..31) {
        for (i2 in 0..31) {
            for (i3 in 0..31) {
                for (i4 in 0..31) {
                    for (i5 in 0..31) {
                        val word = i1.toString() +
                                i2.toString() +
                                i3.toString() +
                                i4.toString() +
                                i5.toString()
                        if (word == word.reversed())
                            result++
                    }
                }
            }
        }
//        val code = Integer.toBinaryString(i).padStart(8, '0')

//        if (code.replace("0", "").length == 3)
//            result++
    }
    println(result)
//    println(Math.log(result * 8 * 8 * 8.0 / (32*32*32*32*32*32*32*32)))
//    println(result * 8 * 8 * 8.0)
    println(log2(result / pow(2.0, 40.0)))
}
