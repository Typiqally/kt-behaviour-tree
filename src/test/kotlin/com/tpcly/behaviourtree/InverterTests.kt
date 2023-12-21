package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import com.tpcly.behaviourtree.node.execute
import com.tpcly.behaviourtree.node.inverter
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
        val mockNode = mockk<TreeNode<Any>> {
            every { execute(any()) } returns TreeNodeResult(this, inputStatus)
        }


        val node = inverter {
            mockNode
        }

        // Act
        val result = node.execute()

        // Assert
        assertEquals(expectedOutputStatus, result.status)
        verify(exactly = 1) { mockNode.execute(any()) }
    }
}