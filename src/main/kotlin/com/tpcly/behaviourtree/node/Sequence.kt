package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A composite node which executes its child nodes in order until one fails or all succeed, similar to an `and` operator
 *
 * @property order the order in which the children should be executed
 */
class Sequence<S : Any>(
    name: String,
    private val order: ExecutionOrder
) : Composite<S>(name) {
    override fun execute(state: S): TreeNodeResult<S> {
        val children = if (order == ExecutionOrder.RANDOM) {
            children.shuffled()
        } else {
            children
        }

        val results = mutableListOf<TreeNodeResult<S>>()

        for (child in children) {
            val result = child.execute(state)
            results.add(result)

            if (result.status == Status.FAILURE || result.status == Status.ABORT) {
                return TreeNodeResult(this, result.status, results)
            }
        }

        return TreeNodeResult.success(this, results)
    }
}

fun sequence(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Sequence<Any>.() -> Unit
) = initNode(Sequence(name, executionOrder), init)

@JvmName("sequenceWithState")
fun <S : Any> sequence(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Sequence<S>.() -> Unit
) = initNode(Sequence(name, executionOrder), init)
