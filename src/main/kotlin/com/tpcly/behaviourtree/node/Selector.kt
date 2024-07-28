package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeExecutionOrder
import kotlinx.serialization.Serializable

@Serializable
data class Selector(
    val executionOrder: TreeExecutionOrder,
    override val children: List<TreeNode>,
) : TreeNode.Composite {
    override val name: String = "selector"
    override val description = "Executes its child nodes in order until one succeeds or all fail, similar to an `or` operator"

    class Handler : TreeNodeHandler<Selector> {
        override fun execute(
            handlers: TreeNodeHandlerCollection,
            descriptor: Selector,
        ): TreeNodeHandler.Status {
            val children = when (descriptor.executionOrder) {
                TreeExecutionOrder.RANDOM -> descriptor.children.shuffled()
                else -> descriptor.children
            }

            for (child in children) {
                val handler = handlers[child.name]
                val result = handler.execute(handlers, child)

                if (result == TreeNodeHandler.Status.SUCCESS || result == TreeNodeHandler.Status.ABORT) {
                    return result
                }
            }

            return TreeNodeHandler.Status.FAILURE
        }
    }
}