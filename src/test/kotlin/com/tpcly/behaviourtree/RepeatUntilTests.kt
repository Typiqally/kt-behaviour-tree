package com.tpcly.behaviourtree

import kotlin.test.Test
import kotlin.test.assertEquals

internal class RepeatUntilTests {
    @Test
    fun testStatus() {
        // Arrange
        var count = 0
        val repeater = repeatUntil(status = Status.SUCCESS) {
            action {
                when (count) {
                    3 -> Status.SUCCESS
                    else -> {
                        count++
                        Status.FAILURE
                    }
                }
            }
        }

        // Act
        val blackboard = Blackboard()
        val result = repeater.execute(blackboard)

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        assertEquals(3, count)
    }

    @Test
    fun testAbort() {
        // Arrange
        var count = 0
        val repeater = repeatUntil(status = Status.SUCCESS) {
            action {
                when (count) {
                    3 -> Status.ABORT
                    else -> {
                        count++
                        Status.FAILURE
                    }
                }
            }
        }

        // Act
        val blackboard = Blackboard()
        val result = repeater.execute(blackboard)

        // Assert
        assertEquals(Status.ABORT, result.status)
        assertEquals(3, count)
    }
}