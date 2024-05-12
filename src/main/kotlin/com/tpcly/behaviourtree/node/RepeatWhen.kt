package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child if the specified [predicate] is met
 *
 * @property predicate the condition which determines whether the loop executes
 */
abstract class RepeatWhen<S>(
    override val name: String,
    private val limit: Int,
    override val child: TreeNode<S>
) : Decorator<S> {
    abstract fun validate(state: S?): Boolean

    override fun execute(state: S?): TreeNodeResult<S> {
        val results = mutableListOf<TreeNodeResult<S>>()
        var iteration = 0

        while (validate(state) && iteration < limit) {
            val result = child.execute(state)
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
