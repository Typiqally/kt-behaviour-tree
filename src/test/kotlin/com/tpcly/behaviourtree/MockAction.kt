package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class MockAction(
    val inputOne: String,
    val inputTwo: Int,
) : TreeNode {
    class Handler : TreeNodeHandler<MockAction> {
        override fun execute(handlers: TreeNodeHandlerModule, descriptor: MockAction): TreeNodeStatus {
            println("${descriptor.inputOne} ${descriptor.inputTwo}")
            return TreeNodeStatus.SUCCESS
        }
    }
}