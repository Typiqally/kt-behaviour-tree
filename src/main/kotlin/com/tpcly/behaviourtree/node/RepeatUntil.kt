package com.tpcly.behaviourtree.node

import kotlinx.serialization.Serializable

@Serializable
data class RepeatUntil(
    override val child: TreeNode,
    val targetStatus: TreeNodeHandler.Status = TreeNodeHandler.Status.SUCCESS,
    val limit: Int = 10,
) : TreeNode.Decorator {
    override val name: String = "repeat_until"
    override val description: String = ""

    class Handler : TreeNodeHandler<RepeatUntil> {
        override fun execute(handlers: TreeNodeHandlerCollection, descriptor: RepeatUntil): TreeNodeHandler.Status {
            val handler = handlers[descriptor.child.name]

            var iteration = 0
            var currentStatus: TreeNodeHandler.Status

            do {
                currentStatus = handler.execute(handlers, descriptor.child)
                iteration++
            } while (
                currentStatus != descriptor.targetStatus &&
                currentStatus != TreeNodeHandler.Status.ABORT &&
                iteration < descriptor.limit
            )

            return when {
                iteration >= descriptor.limit -> TreeNodeHandler.Status.FAILURE
                else -> currentStatus
            }
        }
    }
}