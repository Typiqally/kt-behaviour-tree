package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SequenceTests {
    @Test
    fun testExecutionSuccess() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult.success(this)
        }

        val node = sequence {
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testExecutionFailure() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult.failure(this)
        }

        val node = sequence {
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testOrder() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult.success(this)
        }

        val node = sequence {
            +mockNode
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 2) { mockNode.execute() }
    }

    @Test
    fun testEarlyExit() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult.success(this)
        }

        val node = sequence {
            +mockNode
            +sequence { +action { Status.FAILURE } }
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testAbort() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult.success(this)
        }

        val node = sequence {
            +mockNode
            +sequence {
                +mockNode
                +action { Status.ABORT }
                +mockNode
            }
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.ABORT, result.status)
        verify(exactly = 2) { mockNode.execute() }
    }
}