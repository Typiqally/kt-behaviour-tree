package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A composite node which executes its child nodes in order until one succeeds or all fail, similar to an `or` operator
 *
 * @property order the order in which the children should be executed
 */
class Selector<S : Any>(
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

            if (result.status == Status.SUCCESS || result.status == Status.ABORT) {
                return TreeNodeResult(this, result.status, results)
            }
        }

        return TreeNodeResult.failure(this, results)
    }
}

fun selector(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Selector<Any>.() -> Unit
) = initNode(Selector(name, executionOrder), init)

@JvmName("selectorWithState")
fun <S : Any> selector(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Selector<S>.() -> Unit
) = initNode(Selector(name, executionOrder), init)
