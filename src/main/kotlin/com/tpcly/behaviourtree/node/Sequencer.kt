package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeExecutionOrder
import kotlinx.serialization.Serializable

@Serializable
data class Sequencer(
    val executionOrder: TreeExecutionOrder,
    override val children: List<TreeNode>,
) : TreeNode.Composite {
    override val name: String = "sequencer"
    override val description = "Executes its child nodes in order until one fails or all succeed, similar to an `and` operator"

    class Handler : TreeNodeHandler<Sequencer> {
        override fun execute(
            handlers: TreeNodeHandlerCollection,
            descriptor: Sequencer,
        ): TreeNodeHandler.Status {
            val children = when (descriptor.executionOrder) {
                TreeExecutionOrder.RANDOM -> descriptor.children.shuffled()
                else -> descriptor.children
            }

            for (child in children) {
                val handler = handlers[child.name]
                val result = handler.execute(handlers, child)

                if (result == TreeNodeHandler.Status.FAILURE || result == TreeNodeHandler.Status.ABORT) {
                    return result
                }
            }

            return TreeNodeHandler.Status.SUCCESS
        }
    }
}