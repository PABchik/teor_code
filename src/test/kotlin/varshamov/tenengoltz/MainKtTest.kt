package varshamov.tenengoltz

import org.junit.Assert.assertEquals
import org.junit.Test

class MainKtTest {

    @Test
    fun countS() {
        assertEquals(0, countS("0000"))
        assertEquals(1, countS("1000"))
        assertEquals(2, countS("0100"))
        assertEquals(7, countS("0011"))
    }

    @Test
    fun countSTest() {
        assertEquals(0, countS("0000"))
        assertEquals(1, countS("1000"))
        assertEquals(2, countS("0100"))
        assertEquals(7, countS("0011"))
    }

    @Test
    fun checkInsertCorrection() {
        assertEquals("000000", correctWord("0000000", 6))
        assertEquals("000000", correctWord("0000001", 6))
        assertEquals("000000", correctWord("1000000", 6))
        assertEquals("000000", correctWord("0100000", 6))
        assertEquals("000000", correctWord("0010000", 6))
        assertEquals("000000", correctWord("0001000", 6))
        assertEquals("000000", correctWord("0000100", 6))
        assertEquals("000000", correctWord("0000010", 6))

        assertEquals("100001", correctWord("1000101", 6))
        assertEquals("100001", correctWord("1010001", 6))
        assertEquals("100001", correctWord("1001001", 6))
        assertEquals("100001", correctWord("1000011", 6))
        assertEquals("100001", correctWord("1000001", 6))
        assertEquals("100001", correctWord("1000001", 6))
        assertEquals("100001", correctWord("1000001", 6))
        assertEquals("100001", correctWord("1100001", 6))

        assertEquals("111111", correctWord("1111111", 6))
        assertEquals("111111", correctWord("1101111", 6))
        assertEquals("111111", correctWord("1110111", 6))
        assertEquals("111111", correctWord("1111011", 6))
        assertEquals("111111", correctWord("0111111", 6))
        assertEquals("111111", correctWord("1111110", 6))
        assertEquals("111111", correctWord("1011111", 6))
        assertEquals("111111", correctWord("1111101", 6))

        assertEquals("00111", correctWord("000111", 5))
        assertEquals("00111", correctWord("001111", 5))
        assertEquals("00111", correctWord("010111", 5))
        assertEquals("00111", correctWord("000111", 5))
        assertEquals("00111", correctWord("100111", 5))
        assertEquals("00111", correctWord("001101", 5))
        assertEquals("00111", correctWord("001011", 5))
        assertEquals("00111", correctWord("001110", 5))

        assertEquals("1001", correctWord("11001", 4))
        assertEquals("1001", correctWord("01001", 4))
        assertEquals("1001", correctWord("10011", 4))
        assertEquals("1001", correctWord("10010", 4))
        assertEquals("1001", correctWord("10001", 4))
        assertEquals("1001", correctWord("10101", 4))
        assertEquals("1001", correctWord("11001", 4))
        assertEquals("1001", correctWord("10011", 4))
    }

    @Test
    fun checkDeleteCorrection() {
        assertEquals("000000", correctWord("00000", 6))

        assertEquals("001011", correctWord("00101", 6))
        assertEquals("001011", correctWord("00101", 6))
        assertEquals("001011", correctWord("00111", 6))
        assertEquals("001011", correctWord("00011", 6))
        assertEquals("001011", correctWord("01011", 6))
        assertEquals("001011", correctWord("01011", 6))

        assertEquals("111111", correctWord("11111", 6))

        assertEquals("00111", correctWord("0011", 5))
        assertEquals("00111", correctWord("0111", 5))

        assertEquals("1001", correctWord("001", 4))
        assertEquals("1001", correctWord("100", 4))
        assertEquals("1001", correctWord("101", 4))

    }

    @Test
    fun checkFailedDelete() {
        assertEquals("1001", correctWord("001", 4))
        assertEquals("001011", correctWord("00011", 6))//failed
    }
}