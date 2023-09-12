package com.tpcly.behaviourtree

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
            every { execute() } returns TreeNodeResult(this, inputStatus)
        }

        val gate = gate(predicate = { true }) {
            mockNode
        }

        // Act
        val result = gate.execute()

        // Assert
        assertEquals(inputStatus, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }

    @Test
    fun testClosedGate() {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult(this, Status.SUCCESS)
        }

        val gate = gate(predicate = { false }) {
            mockNode
        }

        // Act
        val result = gate.execute()

        // Assert
        assertEquals(Status.FAILURE, result.status)
        verify(exactly = 0) { mockNode.execute() }
    }
}