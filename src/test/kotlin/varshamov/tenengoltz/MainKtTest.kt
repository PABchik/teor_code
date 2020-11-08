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

}