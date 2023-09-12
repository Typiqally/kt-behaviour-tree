package com.tpcly.behaviourtree

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BlackboardTests {
    @Test
    fun testSet() {
        // Arrange
        val mapMock = mockk<MutableMap<String, Any>> {
            every { this@mockk[any()] = any() } answers {}
        }

        val blackboard = Blackboard(mapMock)

        // Act
        blackboard["test"] = true

        // Assert
        verify(exactly = 1) { mapMock["test"] = true }
    }

    @Test
    fun testGet() {
        // Arrange
        val mapMock = mockk<MutableMap<String, Any>> {
            every { this@mockk[any()] } returns true
        }

        val blackboard = Blackboard(mapMock)

        // Act
        val result = blackboard.get<Boolean>("test")

        // Assert
        assertEquals(true, result)
        verify(exactly = 1) { mapMock["test"] }
    }

    @Test
    fun testObserverUpdate() {
        // Arrange
        val observerMock = mockk<BlackboardObserver> {
            every { update(any(), any()) } answers {}
        }

        val blackboard = Blackboard()
        blackboard.attach(observerMock)

        // Act
        blackboard["test"] = true

        // Assert
        verify(exactly = 1) { observerMock.update("test", true) }
    }
}