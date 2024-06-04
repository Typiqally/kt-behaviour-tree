package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child until the specified condition is met
 */
abstract class RepeatUntil(
    override val name: String,
    private val limit: Int,
    override val child: TreeNode
) : Decorator {
    abstract fun validate(result: TreeNodeResult): Boolean

    override fun execute(): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()
        var iteration = 0

        var result: TreeNodeResult
        do {
            result = child.execute()
            results.add(result)

            iteration++
        } while (!validate(result) && result.status != Status.ABORT && iteration < limit)

        val status = if (iteration >= limit) {
            Status.FAILURE
        } else {
            result.status
        }

        return TreeNodeResult(this, status, results)
    }
}