package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node which executes its child only when a condition is met
 * Returns status of the child if executed, otherwise failure
 *
 * @property predicate the predicate which determines whether the gate is open or closed
 */
abstract class Gate<S>(
    override val name: String,
    override val child: TreeNode<S>
) : Decorator<S> {
    abstract fun validate(state: S?): Boolean

    override fun execute(state: S?): TreeNodeResult<S> {
        if (validate(state)) {
            val result = child.execute(state)
            return TreeNodeResult(this, result.status, listOf(result))
        }

        return TreeNodeResult.failure(this)
    }
}
