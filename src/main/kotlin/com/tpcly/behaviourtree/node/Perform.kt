package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which performs an action and always returns a successful resul
 */
abstract class Perform(
    override val name: String,
) : Leaf {
    abstract fun action()

    override fun execute(): TreeNodeResult {
        action()
        return TreeNodeResult.success(this)
    }
}
