package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which performs an action and always returns a successful result
 *
 * @property func, a unit which is executed as part of the action node
 */
abstract class Perform<S>(
    override val name: String
) : Leaf<S> {
    abstract fun run(state: S?)

    override fun execute(state: S?): TreeNodeResult<S> {
        run(state)
        return TreeNodeResult.success(this)
    }
}
