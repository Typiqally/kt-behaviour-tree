package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class Sequencer(
    val executionOrder: TreeExecutionOrder,
    override val children: List<TreeNode>,
) : TreeNode.Composite {
    override fun execute(): TreeNodeStatus {
        val children = when (executionOrder) {
            TreeExecutionOrder.RANDOM -> children.shuffled()
            else -> children
        }

        for (child in children) {
            val result = child.execute()

            if (result == TreeNodeStatus.FAILURE || result == TreeNodeStatus.ABORT) {
                return result
            }
        }

        return TreeNodeStatus.SUCCESS
    }
}