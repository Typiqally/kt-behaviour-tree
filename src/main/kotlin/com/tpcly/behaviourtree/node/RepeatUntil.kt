package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that repeatedly executes its child until the specified [stopCondition] is met
 *
 * @property stopCondition the condition which determines when the loop terminates
 */
class RepeatUntil<S>(
    name: String,
    private val stopCondition: (TreeNodeResult<S>) -> Boolean,
    private val limit: Int,
    child: TreeNode<S>
) : Decorator<S>(name, child) {
    constructor(name: String, status: Status, limit: Int, child: TreeNode<S>)
            : this(name, { it.status == status }, limit, child)

    override fun execute(state: S): TreeNodeResult<S> {
        val results = mutableListOf<TreeNodeResult<S>>()
        var iteration = 0

        var result: TreeNodeResult<S>
        do {
            result = child.execute(state)
            results.add(result)

            iteration++
        } while (!stopCondition(result) && result.status != Status.ABORT && iteration < limit)

        val status = if (iteration >= limit) {
            Status.FAILURE
        } else {
            result.status
        }

        return TreeNodeResult(this, status, results)
    }
}

fun repeatUntil(
    stopCondition: (TreeNodeResult<Any>) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<Any>
) = RepeatUntil(name, stopCondition, limit, init())

fun repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<Any>
) = RepeatUntil(name, status, limit, init())

@JvmName("repeatUntilWithConditionAndState")
fun <S> repeatUntil(
    stopCondition: (TreeNodeResult<S>) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<S>
) = RepeatUntil(name, stopCondition, limit, init())

@JvmName("repeatUntilWithStatusAndState")
fun <S> repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<S>
) = RepeatUntil(name, status, limit, init())
