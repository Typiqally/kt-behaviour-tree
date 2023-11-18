package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that turns the result of its child into success, regardless of what the child returns
 */
class Succeeder(
    name: String,
    child: TreeNode
) : Decorator(name, child) {
    override fun execute(): TreeNodeResult {
        val result = child.execute()
        return TreeNodeResult.success(this, listOf(result))
    }
}