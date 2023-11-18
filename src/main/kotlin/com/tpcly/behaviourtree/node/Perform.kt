package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which performs an action and always returns a successful result
 *
 * @property func, a unit which is executed as part of the action node
 */
class Perform(
    override val name: String,
    val func: () -> Unit
) : Action(name) {
    override fun execute(): TreeNodeResult {
        func()
        return TreeNodeResult.success(this)
    }

}