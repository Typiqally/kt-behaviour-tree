package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.conditional
import com.tpcly.behaviourtree.node.perform
import com.tpcly.behaviourtree.node.sequencer
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StateTests {
    data class MockDataClass(override val rootValue: String, override val childValue: String) : MockRootData

    interface MockRootData : MockChildDate {
        val rootValue: String
    }

    interface MockChildDate {
        val childValue: String
    }

    @Test
    fun testStateHierarchy() {
        // Arrange
        val state = MockDataClass("test_root", "test_child")
        val tree = sequencer {
            +conditional {
                state.rootValue == "test_root"
            }
            +conditional {
                state.childValue == "test_child"
            }
            +perform {
                println("test")
            }
        }

        // Act
        val result = tree.execute()

        // Assert
        assertEquals(Status.SUCCESS, result.status)
    }
}