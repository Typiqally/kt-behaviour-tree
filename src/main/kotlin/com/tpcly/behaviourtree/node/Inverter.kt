package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that inverts the result from its child, success becomes failure and vice-versa
 */
class Inverter(name: String, child: TreeNode) : Decorator(name, child) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val result = child.execute(blackboard)
        val status = when (result.status) {
            Status.SUCCESS -> Status.FAILURE
            Status.FAILURE -> Status.SUCCESS
            else -> result.status
        }

        return TreeNodeResult(this, status, listOf(result))
    }
}