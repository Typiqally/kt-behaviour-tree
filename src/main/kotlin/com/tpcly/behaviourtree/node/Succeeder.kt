package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * A decorator node that turns the result of its child into success, regardless of what the child returns
 */
class Succeeder<S>(
    name: String,
    child: TreeNode<S>
) : Decorator<S>(name, child) {
    override fun execute(state: S): TreeNodeResult<S> {
        val result = child.execute(state)
        return TreeNodeResult.success(this, listOf(result))
    }
}

fun succeeder(
    name: String = "",
    init: () -> TreeNode<Any>
) = Succeeder(name, init())

@JvmName("succeederWithState")
fun <S> succeeder(
    name: String = "",
    init: () -> TreeNode<S>
) = Succeeder(name, init())
