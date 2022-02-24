package lesson7

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class DynamicTestsJava : AbstractDynamicTests() {

    @Test
    @Tag("5")
    fun testLongestCommonSubSequenceJava() {
        longestCommonSubSequence { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
        assertEquals("", JavaDynamicTasks.longestCommonSubSequence("", ""))
        assertEquals("", JavaDynamicTasks.longestCommonSubSequence("1", ""))
        assertEquals("", JavaDynamicTasks.longestCommonSubSequence("", "1"))
    }

    @Test
    @Tag("7")
    fun testLongestIncreasingSubSequenceJava() {
        longestIncreasingSubSequence { JavaDynamicTasks.longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("4")
    fun testShortestPathOnFieldJava() {
        shortestPathOnField { JavaDynamicTasks.shortestPathOnField(it) }
    }
}