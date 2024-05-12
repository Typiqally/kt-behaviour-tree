package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import com.tpcly.behaviourtree.node.perform
import com.tpcly.behaviourtree.node.repeatWhen
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
        val node = repeatWhen(validate = { count != 3 }) {
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
        val mockNode = mockk<TreeNode<Any>> {
            every { execute() } returns TreeNodeResult.failure(this)
        }

        val node = repeatWhen(validate = { false }) {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 0) { mockNode.execute() }
    }

    @Test
    fun testAbort() {
        // Arrange
        var count = 0
        val node = repeatWhen(validate = { true }) {
            com.tpcly.behaviourtree.node.run {
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

    @Test
    fun testLimit() {
        // Arrange
        val mockNode = mockk<TreeNode<Any>> {
            every { execute(any()) } returns TreeNodeResult.success(this)
        }

        val node = repeatWhen(validate = { true }, limit = 10) {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 10) { mockNode.execute(any()) }
    }
}