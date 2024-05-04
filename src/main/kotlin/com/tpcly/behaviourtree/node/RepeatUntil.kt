package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child until the specified [predicate] is met
 *
 * @property predicate the condition which determines when the loop terminates
 */
class RepeatUntil<S>(
    override val name: String,
    private val predicate: (TreeNodeResult<S>) -> Boolean,
    private val limit: Int,
    override val child: TreeNode<S>
) : Decorator<S> {
    constructor(name: String, status: Status, limit: Int, child: TreeNode<S>)
            : this(name, { it.status == status }, limit, child)

    override fun execute(state: S?): TreeNodeResult<S> {
        val results = mutableListOf<TreeNodeResult<S>>()
        var iteration = 0

        var result: TreeNodeResult<S>
        do {
            result = child.execute(state)
            results.add(result)

            iteration++
        } while (!predicate(result) && result.status != Status.ABORT && iteration < limit)

        val status = if (iteration >= limit) {
            Status.FAILURE
        } else {
            result.status
        }

        return TreeNodeResult(this, status, results)
    }
}