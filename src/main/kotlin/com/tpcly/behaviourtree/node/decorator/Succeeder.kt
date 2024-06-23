package com.tpcly.behaviourtree.node.decorator

import com.tpcly.behaviourtree.TreeNodeResult
import com.tpcly.behaviourtree.node.TreeNode

/**
 * A decorator node that turns the result of its child into success, regardless of what the child returns
 */
class Succeeder(
    override val name: String,
    override val child: TreeNode
) : Decorator {
    override fun execute(): TreeNodeResult {
        val result = child.execute()
        return TreeNodeResult.success(this, listOf(result))
    }
}