package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class RepeatUntil(
    override val child: TreeNode,
    val targetStatus: TreeNodeHandler.Status = TreeNodeHandler.Status.SUCCESS,
    val limit: Int = 10,
) : TreeNode.Decorator {
    class Handler : TreeNodeHandler<RepeatUntil> {
        override fun execute(handlers: TreeNodeHandlerModule, descriptor: RepeatUntil): TreeNodeHandler.Status {
            var iteration = 0
            var currentStatus: TreeNodeHandler.Status

            do {
                currentStatus = handlers.execute(descriptor.child)
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