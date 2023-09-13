package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.*

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