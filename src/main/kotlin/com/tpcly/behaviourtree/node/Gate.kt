package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node which executes its child only when a condition is met
 * Returns status of the child if executed, otherwise failure
 */
open class Gate(
    override val name: String,
    override val child: TreeNode,
    val validate: () -> Boolean
) : Decorator {
    override fun execute(): TreeNodeResult {
        if (validate()) {
            val result = child.execute()
            return TreeNodeResult(this, result.status, listOf(result))
        }

        return TreeNodeResult.failure(this)
    }
}
