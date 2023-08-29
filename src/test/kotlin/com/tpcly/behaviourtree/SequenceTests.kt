package com.tpcly.behaviourtree

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SequenceTests {
    @Test
    fun testExecutionSuccess() {
        // Arrange
        val mockNode = mockk<TreeNode>()
        every { mockNode.execute() } returns Status.SUCCESS

        val sequence = sequence {
            +mockNode
        }

        // Act
        val result = sequence.execute()

        // Assert
        assertEquals(Status.SUCCESS, result)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testExecutionFailure() {
        // Arrange
        val mockNode = mockk<TreeNode>()
        every { mockNode.execute() } returns Status.FAILURE

        val sequence = sequence {
            +mockNode
        }

        // Act
        val result = sequence.execute()

        // Assert
        assertEquals(Status.FAILURE, result)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testOrder() {
        // Arrange
        val mockNode = mockk<TreeNode>()
        every { mockNode.execute() } returns Status.SUCCESS

        val sequence = sequence {
            +mockNode
            +mockNode
        }

        // Act
        val result = sequence.execute()

        // Assert
        assertEquals(Status.SUCCESS, result)
        verify(exactly = 2) { mockNode.execute() }
    }

    @Test
    fun testEarlyExit() {
        // Arrange
        val mockNode = mockk<TreeNode>()
        every { mockNode.execute() } returns Status.SUCCESS

        val sequence = sequence {
            +mockNode
            +sequence { +leaf { Status.FAILURE } }
            +mockNode
        }

        // Act
        val result = sequence.execute()

        // Assert
        assertEquals(Status.FAILURE, result)
        verify(exactly = 1) { mockNode.execute() }
    }
}