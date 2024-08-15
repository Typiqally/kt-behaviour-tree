package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class Sequencer(
    val executionOrder: TreeExecutionOrder,
    override val children: List<TreeNode>,
) : TreeNode.Composite {
    class Handler : TreeNodeHandler<Sequencer> {
        override fun execute(
            handlers: TreeNodeHandlerModule,
            descriptor: Sequencer,
        ): TreeNodeHandler.Status {
            val children = when (descriptor.executionOrder) {
                TreeExecutionOrder.RANDOM -> descriptor.children.shuffled()
                else -> descriptor.children
            }

            for (child in children) {
                val result = handlers.execute(child)

                if (result == TreeNodeHandler.Status.FAILURE || result == TreeNodeHandler.Status.ABORT) {
                    return result
                }
            }

            return TreeNodeHandler.Status.SUCCESS
        }
    }
}