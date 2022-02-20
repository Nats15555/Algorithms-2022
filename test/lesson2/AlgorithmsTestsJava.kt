package lesson2

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    @Tag("2")
    fun testOptimizeBuyAndSellJava() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("2")
    fun testJosephTaskJava() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("4")
    fun testLongestCommonSubstringJava() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
        assertEquals("", JavaAlgorithms.longestCommonSubstring("", "я"))
        assertEquals("", JavaAlgorithms.longestCommonSubstring("z", ""))
        assertEquals("", JavaAlgorithms.longestCommonSubstring("z", ""))
    }

    @Test
    @Tag("3")
    fun testCalcPrimesNumberJava() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
        assertEquals(0, JavaAlgorithms.calcPrimesNumber(0))
        assertEquals(0, JavaAlgorithms.calcPrimesNumber(-100))
        assertEquals(0, JavaAlgorithms.calcPrimesNumber(-2))
    }
}