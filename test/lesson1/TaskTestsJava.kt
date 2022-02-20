package lesson1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.assertThrows
import java.io.File
import kotlin.test.Test

class TaskTestsJava : AbstractTaskTests() {

    @Test
    @Tag("3")
    fun testSortTimesJava() {
        sortTimes { inputName, outputName -> JavaTasks.sortTimes(inputName, outputName) }
    }

    @Test
    @Tag("4")
    fun testSortAddressesJava() {
        sortAddresses { inputName, outputName -> JavaTasks.sortAddresses(inputName, outputName) }
    }

    @Test
    @Tag("4")
    fun testSortTemperaturesJava() {
        sortTemperatures { inputName, outputName -> JavaTasks.sortTemperatures(inputName, outputName) }

        Assertions.assertThrows(
            IllegalArgumentException::class.java
        ) { JavaTasks.sortTemperatures("input/temp_mytest.txt", "temp.txt") }

    }

    @Test
    @Tag("4")
    fun testSortSequenceJava() {
        sortSequence { inputName, outputName -> JavaTasks.sortSequence(inputName, outputName) }
        Assertions.assertThrows(
            IllegalArgumentException::class.java
        ) { JavaTasks.sortSequence("input/seq_mytest.txt", "temp.txt") }

    }


    @Test
    @Tag("2")
    fun testMergeArraysJava() {
        mergeArrays { first, second -> JavaTasks.mergeArrays<Int?>(first, second) }
    }
}
