package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which performs an action and always returns a successful result
 *
 * @property func, a unit which is executed as part of the action node
 */
class Perform<S>(
    override val name: String,
    val func: (state: S?) -> Unit
) : TreeNode<S> {
    override fun execute(state: S?): TreeNodeResult<S> {
        func(state)
        return TreeNodeResult.success(this)
    }
}
