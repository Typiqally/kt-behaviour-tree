package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class RepeatUntil(
    override val child: TreeNode,
    val targetStatus: TreeNodeStatus = TreeNodeStatus.SUCCESS,
    val limit: Int = 10,
) : TreeNode.Decorator {
    override fun execute(): TreeNodeStatus {
        var iteration = 0
        var currentStatus: TreeNodeStatus

        do {
            currentStatus = child.execute()
            iteration++
        } while (
            currentStatus != targetStatus &&
            currentStatus != TreeNodeStatus.ABORT &&
            iteration < limit
        )

        return when {
            iteration >= limit -> TreeNodeStatus.FAILURE
            else -> currentStatus
        }
    }
}