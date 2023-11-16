package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RepeatWhenTests {
    @Test
    fun testStatus() {
        // Arrange
        var count = 0
        val node = repeatWhen({ count != 3 }) {
            perform {
                count++
            }
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        assertEquals(3, count)
    }

    @Test
    fun testAlreadySatisfied() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult.failure(this)
        }

        val node = repeatWhen({ false }) {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 0) { mockNode.execute(any()) }
    }

    @Test
    fun testAbort() {
        // Arrange
        var count = 0
        val node = repeatWhen({ true }) {
            action {
                when (count) {
                    3 -> Status.ABORT
                    else -> {
                        count++
                        Status.SUCCESS
                    }
                }
            }
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.ABORT, result.status)
        assertEquals(3, count)
    }
}