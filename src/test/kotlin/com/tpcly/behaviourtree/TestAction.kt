package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode
import com.tpcly.behaviourtree.node.TreeNodeHandler
import com.tpcly.behaviourtree.node.TreeNodeHandlerCollection
import kotlinx.serialization.Serializable

@Serializable
data class TestAction(
    val inputOne: String,
    val inputTwo: Int,
) : TreeNode {
    override val name: String = "test"
    override val description: String = "Test"

    class Handler : TreeNodeHandler<TestAction> {
        override fun execute(handlers: TreeNodeHandlerCollection, descriptor: TestAction): TreeNodeHandler.Status {
            println("${descriptor.inputOne} ${descriptor.inputTwo}")
            return TreeNodeHandler.Status.SUCCESS
        }
    }
}