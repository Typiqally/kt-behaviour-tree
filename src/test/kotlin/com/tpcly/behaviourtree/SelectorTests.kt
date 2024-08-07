package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import com.tpcly.behaviourtree.node.selector
import com.tpcly.behaviourtree.node.sequencer
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
            every { execute() } returns TreeNodeResult.success(this)
        }

        val node = selector {
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

        val node = selector {
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

        val node = selector {
            +mockNode
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testEarlyExit() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult.success(this)
        }

        val node = selector {
            +mockNode
            +selector { +com.tpcly.behaviourtree.node.run { Status.FAILURE } }
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testAbort() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult.failure(this)
        }

        val node = selector {
            +mockNode
            +sequencer {
                +com.tpcly.behaviourtree.node.run { Status.ABORT }
                +mockNode
            }
            +mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.ABORT, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }
}