package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class Selector(
    val executionOrder: TreeExecutionOrder,
    override val children: List<TreeNode>,
) : TreeNode.Composite {
    class Handler : TreeNodeHandler<Selector> {
        override fun execute(
            handlers: TreeNodeHandlerModule,
            descriptor: Selector,
        ): TreeNodeStatus {
            val children = when (descriptor.executionOrder) {
                TreeExecutionOrder.RANDOM -> descriptor.children.shuffled()
                else -> descriptor.children
            }

            for (child in children) {
                val result = handlers.execute(child)

                if (result == TreeNodeStatus.SUCCESS || result == TreeNodeStatus.ABORT) {
                    return result
                }
            }

            return TreeNodeStatus.FAILURE
        }
    }
}