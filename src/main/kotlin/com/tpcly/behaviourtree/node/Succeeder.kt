package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that turns the result of its child into success, regardless of what the child returns
 */
class Succeeder<S>(
    override val name: String,
    override val child: TreeNode<S>
) : Decorator<S> {
    override fun execute(state: S?): TreeNodeResult<S> {
        val result = child.execute(state)
        return TreeNodeResult.success(this, listOf(result))
    }
}