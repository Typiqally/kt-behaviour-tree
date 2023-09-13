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
    child: TreeNode
) : Decorator(name, child) {
    constructor(name: String, status: Status, child: TreeNode)
            : this(name, { it.status == status }, child)

    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()

        var result: TreeNodeResult
        do {
            result = child.execute(blackboard)
            results.add(result)
        } while (!stopCondition(result) && result.status != Status.ABORT)

        return TreeNodeResult(this, result.status, results)
    }
}