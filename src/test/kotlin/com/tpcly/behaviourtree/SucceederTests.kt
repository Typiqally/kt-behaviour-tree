package com.tpcly.behaviourtree

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SucceederTests {

    @Test
    fun testExecutionSuccess() = testExecution(Status.SUCCESS)

    @Test
    fun testExecutionFailure() = testExecution(Status.FAILURE)

    @Test
    fun testExecutionRunning() = testExecution(Status.RUNNING)

    @Test
    fun testExecutionExit() = testExecution(Status.ABORT)

    private fun testExecution(inputStatus: Status) {
        // Arrange
        val mockNode = mockk<TreeNode> {
            every { execute() } returns TreeNodeResult(this, inputStatus)
        }

        val selector = succeeder {
            mockNode
        }

        // Act
        val result = selector.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute() }
    }
}