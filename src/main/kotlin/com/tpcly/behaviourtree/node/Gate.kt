package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node which executes its child only when a condition is met
 * Returns status of the child if executed, otherwise failure
 *
 * @property predicate the predicate which determines whether the gate is open or closed
 */
class Gate<S>(
    name: String,
    val predicate: () -> Boolean,
    child: TreeNode<S>
) : Decorator<S>(name, child) {
    override fun execute(state: S): TreeNodeResult<S> {
        if (predicate()) {
            val result = child.execute(state)
            return TreeNodeResult(this, result.status, listOf(result))
        }

        return TreeNodeResult.failure(this)
    }
}

fun gate(
    validate: () -> Boolean,
    name: String = "",
    init: () -> TreeNode<Any>
) = Gate(name, validate, init())

@JvmName("gateWithState")
fun <S> gate(
    validate: () -> Boolean,
    name: String = "",
    init: () -> TreeNode<S>
) = Gate(name, validate, init())
