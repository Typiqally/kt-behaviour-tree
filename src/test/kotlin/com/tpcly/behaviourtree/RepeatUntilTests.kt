package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import com.tpcly.behaviourtree.node.action
import com.tpcly.behaviourtree.node.execute
import com.tpcly.behaviourtree.node.repeatUntil
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RepeatUntilTests {
    @Test
    fun testStatus() {
        // Arrange
        var count = 0
        val node = repeatUntil(Status.SUCCESS) {
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
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        assertEquals(3, count)
    }

    @Test
    fun testAbort() {
        // Arrange
        var count = 0
        val node = repeatUntil(Status.SUCCESS) {
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

        val node = repeatUntil(Status.FAILURE, 10) {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 10) { mockNode.execute(any()) }
    }
}