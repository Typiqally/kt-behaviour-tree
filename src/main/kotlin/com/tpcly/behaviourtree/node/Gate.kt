package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node which executes its child only when a condition is met
 * Returns status of the child if executed, otherwise failure
 *
 * @property predicate the predicate which determines whether the gate is open or closed
 */
class Gate(
    name: String,
    val predicate: (blackboard: Blackboard) -> Boolean,
    child: TreeNode
) : Decorator(name, child) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        if (predicate(blackboard)) {
            val result = child.execute(blackboard)
            return TreeNodeResult(this, result.status, listOf(result))
        }

        return TreeNodeResult.failure(this)
    }
}