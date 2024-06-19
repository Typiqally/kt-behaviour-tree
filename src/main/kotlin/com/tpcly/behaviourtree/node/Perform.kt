package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which performs an action and always returns a successful resul
 */
open class Perform(
    override val name: String,
    val action: () -> Unit
) : Leaf {
    override fun execute(): TreeNodeResult {
        action()
        return TreeNodeResult.success(this)
    }
}
