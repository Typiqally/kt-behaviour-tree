package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class RepeatUntil(
    override val child: TreeNode,
    val targetStatus: TreeNodeStatus = TreeNodeStatus.SUCCESS,
    val limit: Int = 10,
) : TreeNode.Decorator {
    class Handler : TreeNodeHandler<RepeatUntil> {
        override fun execute(handlers: TreeNodeHandlerModule, descriptor: RepeatUntil): TreeNodeStatus {
            var iteration = 0
            var currentStatus: TreeNodeStatus

            do {
                currentStatus = handlers.execute(descriptor.child)
                iteration++
            } while (
                currentStatus != descriptor.targetStatus &&
                currentStatus != TreeNodeStatus.ABORT &&
                iteration < descriptor.limit
            )

            return when {
                iteration >= descriptor.limit -> TreeNodeStatus.FAILURE
                else -> currentStatus
            }
        }
    }
}