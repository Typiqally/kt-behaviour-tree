package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that inverts the result from its child, success becomes failure and vice-versa
 */
class Inverter<S>(name: String, child: TreeNode<S>) : Decorator<S>(name, child) {
    override fun execute(state: S): TreeNodeResult<S> {
        val result = child.execute(state)
        val status = when (result.status) {
            Status.SUCCESS -> Status.FAILURE
            Status.FAILURE -> Status.SUCCESS
            else -> result.status
        }

        return TreeNodeResult(this, status, listOf(result))
    }
}

fun inverter(
    name: String = "",
    init: () -> TreeNode<Any>
) = Inverter(name, init())

@JvmName("inverterWithState")
fun <S> inverter(
    name: String = "",
    init: () -> TreeNode<S>
) = Inverter(name, init())

fun <S> TreeNode<S>.inverted() = Inverter(name, this)