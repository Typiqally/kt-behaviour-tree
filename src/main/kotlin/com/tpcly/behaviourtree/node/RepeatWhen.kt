package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child if the specified [continueCondition] is met
 *
 * @property continueCondition the condition which determines whether the loop executes
 */
class RepeatWhen(
    name: String,
    private val continueCondition: () -> Boolean,
    private val limit: Int,
    child: TreeNode
) : Decorator(name, child) {
    override fun execute(): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()
        var iteration = 0

        while (continueCondition() && iteration < limit) {
            val result = child.execute()
            results.add(result)

            if (result.status == Status.ABORT) {
                break
            }

            iteration++
        }

        val status = if (iteration >= limit) {
            Status.FAILURE
        } else {
            results.lastOrNull()?.status ?: Status.SUCCESS
        }

        return TreeNodeResult(this, status, results)
    }
}