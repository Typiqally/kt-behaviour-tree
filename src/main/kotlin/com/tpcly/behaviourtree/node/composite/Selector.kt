package com.tpcly.behaviourtree.node.composite

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult
import com.tpcly.behaviourtree.node.TreeNode

/**
 * A composite node which executes its child nodes in order until one succeeds or all fail, similar to an `or` operator
 *
 * @property order the order in which the children should be executed
 */
open class Selector(
    override val name: String,
    private val order: ExecutionOrder = ExecutionOrder.IN_ORDER,
    override val children: MutableList<TreeNode> = mutableListOf(),
) : Composite {
    override fun execute(): TreeNodeResult {
        val children = if (order == ExecutionOrder.RANDOM) {
            children.shuffled()
        } else {
            children
        }

        val results = mutableListOf<TreeNodeResult>()

        for (child in children) {
            val result = child.execute()
            results.add(result)

            if (result.status == Status.SUCCESS || result.status == Status.ABORT) {
                return TreeNodeResult(this, result.status, results)
            }
        }

        return TreeNodeResult.failure(this, results)
    }
}

