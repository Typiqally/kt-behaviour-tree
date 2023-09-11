package com.tpcly.behaviourtree

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SelectorTests {
    @Test
    fun testExecutionSuccess() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult.success(this)
        }

        val selector = selector {
            +mockNode
        }

        // Act
        val blackboard = mutableMapOf<String, Any>()
        val result = selector.execute(blackboard)

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }

    @Test
    fun testExecutionFailure() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult.failure(this)
        }

        val selector = selector {
            +mockNode
        }

        // Act
        val blackboard = mutableMapOf<String, Any>()
        val result = selector.execute(blackboard)

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }

    @Test
    fun testOrder() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult.success(this)
        }

        val selector = selector {
            +mockNode
            +mockNode
        }

        // Act
        val blackboard = mutableMapOf<String, Any>()
        val result = selector.execute(blackboard)

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }

    @Test
    fun testEarlyExit() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult.success(this)
        }

        val selector = selector {
            +mockNode
            +selector { +action { Status.FAILURE } }
            +mockNode
        }

        // Act
        val blackboard = mutableMapOf<String, Any>()
        val result = selector.execute(blackboard)

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }

    @Test
    fun testAbort() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult.failure(this)
        }

        val selector = selector {
            +mockNode
            +sequence {
                +action { Status.ABORT }
                +mockNode
            }
            +mockNode
        }

        // Act
        val blackboard = mutableMapOf<String, Any>()
        val result = selector.execute(blackboard)

        // Assert
        assertEquals(Status.ABORT, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }
}