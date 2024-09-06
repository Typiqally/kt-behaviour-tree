package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class Succeeder(
    override val child: TreeNode,
) : TreeNode.Decorator {
    override fun execute(): TreeNodeStatus {
        child.execute()
        return TreeNodeStatus.SUCCESS
    }
}