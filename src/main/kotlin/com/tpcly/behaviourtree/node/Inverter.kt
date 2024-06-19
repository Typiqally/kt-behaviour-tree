package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that inverts the result from its child, success becomes failure and vice-versa
 */
open class Inverter(
    override val name: String,
    override val child: TreeNode
) : Decorator {
    override fun execute(): TreeNodeResult {
        val result = child.execute()
        val status = when (result.status) {
            Status.SUCCESS -> Status.FAILURE
            Status.FAILURE -> Status.SUCCESS
            else -> result.status
        }

        return TreeNodeResult(this, status, listOf(result))
    }
}

