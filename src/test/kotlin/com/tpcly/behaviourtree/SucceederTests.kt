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
    fun testExecutionExit() = testExecution(Status.EXIT)

    private fun testExecution(inputStatus: Status) {
        // Arrange
        val mockNode = mockk<TreeNode>()
        every { mockNode.execute() } returns inputStatus

        val selector = succeeder {
            mockNode
        }

        // Act
        val result = selector.execute()

        // Assert
        assertEquals(Status.SUCCESS, result)
        verify(exactly = 1) { mockNode.execute() }
    }
}