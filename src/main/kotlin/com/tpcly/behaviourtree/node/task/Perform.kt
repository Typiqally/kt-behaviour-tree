package com.tpcly.behaviourtree.node.task

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which performs an action and always returns a successful resul
 */
abstract class Perform(
    override val name: String,
) : Task {
    abstract fun action()

    override fun execute(): TreeNodeResult {
        action()
        return TreeNodeResult.success(this)
    }
}
