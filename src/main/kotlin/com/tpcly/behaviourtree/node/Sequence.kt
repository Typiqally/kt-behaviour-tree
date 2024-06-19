package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A composite node which executes its child nodes in order until one fails or all succeed, similar to an `and` operator
 *
 * @property order the order in which the children should be executed
 */
open class Sequence(
    override val name: String,
    private val order: ExecutionOrder = ExecutionOrder.IN_ORDER,
    override val children: MutableList<TreeNode> = mutableListOf()
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

            if (result.status == Status.FAILURE || result.status == Status.ABORT) {
                return TreeNodeResult(this, result.status, results)
            }
        }

        return TreeNodeResult.success(this, results)
    }
}
