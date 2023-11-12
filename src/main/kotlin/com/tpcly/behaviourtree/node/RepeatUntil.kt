package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child until the specified [stopCondition] is met
 *
 * @property stopCondition the condition which determines when the loop terminates
 */
class RepeatUntil(
    name: String,
    private val stopCondition: (TreeNodeResult) -> Boolean,
    private val limit: Int,
    child: TreeNode
) : Decorator(name, child) {
    constructor(name: String, status: Status, limit: Int, child: TreeNode)
            : this(name, { it.status == status }, limit, child)

    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()
        var iteration = 0

        var result: TreeNodeResult
        do {
            result = child.execute(blackboard)
            results.add(result)

            iteration++
        } while (!stopCondition(result) && result.status != Status.ABORT && iteration < limit)

        return TreeNodeResult(this, result.status, results)
    }
}