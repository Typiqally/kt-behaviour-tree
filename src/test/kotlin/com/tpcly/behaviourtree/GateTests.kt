package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GateTests {

    @Test
    fun testOpenGateSuccess() = testOpenGate(Status.SUCCESS)

    @Test
    fun testOpenGateFailure() = testOpenGate(Status.FAILURE)

    @Test
    fun testOpenGateRunning() = testOpenGate(Status.RUNNING)

    @Test
    fun testOpenGateExit() = testOpenGate(Status.ABORT)

    private fun testOpenGate(inputStatus: Status) {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult(this, inputStatus)
        }

        val node = gate(validate = { true }) {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(inputStatus, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }

    @Test
    fun testClosedGate() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult(this, Status.SUCCESS)
        }

        val node = gate(validate = { false }) {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 0) { mockNode.execute(any()) }
    }

    @Test
    fun testOpenBlackboardGate() {
        // Arrange
        val nodeMock = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult(this, Status.SUCCESS)
        }

        val blackboardMock = mockk<Blackboard> {
            every { this@mockk["test"] } returns true
        }

        val node = gate(key = "test", value = true) {
            nodeMock
        }

        // Act
        val result = node.execute(blackboardMock)

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { blackboardMock["test"] }
        verify(exactly = 1) { nodeMock.execute(any()) }
    }

    @Test
    fun testClosedBlackboardGate() {
        // Arrange
        val nodeMock = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult(this, Status.SUCCESS)
        }

        val blackboardMock = mockk<Blackboard> {
            every { this@mockk["test"] } returns false
        }

        val node = gate(key = "test", value = true) {
            nodeMock
        }

        // Act
        val result = node.execute(blackboardMock)

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 1) { blackboardMock["test"] }
        verify(exactly = 0) { nodeMock.execute(any()) }
    }
}