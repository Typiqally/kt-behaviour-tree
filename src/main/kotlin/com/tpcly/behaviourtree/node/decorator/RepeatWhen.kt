package com.tpcly.behaviourtree.node.decorator

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult
import com.tpcly.behaviourtree.node.TreeNode

/**
 * A decorator node that repeatedly executes its child if the specified condition is met
 */
open class RepeatWhen(
    override val name: String,
    private val limit: Int,
    override val child: TreeNode,
    val validate: () -> Boolean
) : Decorator {
    override fun execute(): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()
        var iteration = 0

        while (validate() && iteration < limit) {
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
