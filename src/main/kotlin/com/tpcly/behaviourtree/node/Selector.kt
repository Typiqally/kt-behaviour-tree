package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A composite node which executes its child nodes in order until one succeeds or all fail, similar to an `or` operator
 *
 * @property random randomize the order of children before executing
 */
class Selector(name: String, private val random: Boolean) : Composite(name) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val children = if (random) {
            children.shuffled()
        } else {
            children
        }

        val results = mutableListOf<TreeNodeResult>()

        for (child in children) {
            val result = child.execute(blackboard)
            results.add(result)

            if (result.status == Status.SUCCESS || result.status == Status.ABORT) {
                return TreeNodeResult(this, result.status, results)
            }
        }

        return TreeNodeResult.failure(this, results)
    }
}