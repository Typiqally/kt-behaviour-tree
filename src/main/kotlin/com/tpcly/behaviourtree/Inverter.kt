package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class Inverter(
    override val child: TreeNode,
) : TreeNode.Decorator {
    override fun execute(): TreeNodeStatus {
        return when (val result = child.execute()) {
            TreeNodeStatus.SUCCESS -> TreeNodeStatus.FAILURE
            TreeNodeStatus.FAILURE -> TreeNodeStatus.SUCCESS
            else -> result
        }
    }
}