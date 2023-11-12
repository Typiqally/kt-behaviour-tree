package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
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
    child: TreeNode
) : Decorator(name, child) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()

        while (continueCondition()) {
            val result = child.execute(blackboard)
            results.add(result)

            if (result.status == Status.ABORT) {
                break
            }
        }

        val status = results.lastOrNull()?.status ?: Status.SUCCESS

        return TreeNodeResult(this, status, results)
    }
}