package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child until the specified [status] is returned
 *
 * @property status the status to loop until
 */
class RepeatUntil(
    name: String,
    private val status: Status,
    child: TreeNode
) : Decorator(name, child) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()

        var result: TreeNodeResult
        do {
            result = child.execute(blackboard)
            results.add(result)
        } while (result.status != status && result.status != Status.ABORT)

        return TreeNodeResult(this, result.status, results)
    }
}