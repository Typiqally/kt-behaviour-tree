package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child if the specified [continueCondition] is met
 *
 * @property continueCondition the condition which determines whether the loop executes
 */
class RepeatWhen<S>(
    name: String,
    private val continueCondition: () -> Boolean,
    private val limit: Int,
    child: TreeNode<S>
) : Decorator<S>(name, child) {
    override fun execute(state: S): TreeNodeResult<S> {
        val results = mutableListOf<TreeNodeResult<S>>()
        var iteration = 0

        while (continueCondition() && iteration < limit) {
            val result = child.execute(state)
            results.add(result)

            if (result.status == Status.ABORT) {
                break
            }

            iteration++
        }

        val status = if (iteration >= limit) {
            Status.FAILURE
        } else {
            results.lastOrNull()?.status ?: Status.SUCCESS
        }

        return TreeNodeResult(this, status, results)
    }
}

fun repeatWhen(
    continueCondition: () -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<Any>
) = RepeatWhen(name, continueCondition, limit, init())

@JvmName("repeatWhenWithState")
fun <S> repeatWhen(
    continueCondition: () -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<S>
) = RepeatWhen(name, continueCondition, limit, init())
