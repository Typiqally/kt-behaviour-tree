package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import com.tpcly.behaviourtree.node.succeeder
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
        val mockNode = mockk<TreeNode<Any>> {
            every { execute(any()) } returns TreeNodeResult(this, inputStatus)
        }

        val node = succeeder {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }
}