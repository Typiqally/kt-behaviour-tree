package com.tpcly.behaviourtree

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class InverterTests {

    @Test
    fun testExecutionSuccess() = testExecution(Status.SUCCESS, Status.FAILURE)

    @Test
    fun testExecutionFailure() = testExecution(Status.FAILURE, Status.SUCCESS)

    @Test
    fun testExecutionRunning() = testExecution(Status.RUNNING, Status.RUNNING)

    @Test
    fun testExecutionExit() = testExecution(Status.ABORT, Status.ABORT)

    private fun testExecution(inputStatus: Status, expectedOutputStatus: Status) {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute(any()) } returns TreeNodeResult(this, inputStatus)
        }


        val selector = inverter {
            mockNode
        }

        // Act
        val blackboard = mutableMapOf<String, Any>()
        val result = selector.execute(blackboard)

        // Assert
        assertEquals(expectedOutputStatus, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }
}