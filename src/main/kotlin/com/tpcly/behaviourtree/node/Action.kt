package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.BehaviourTreeDslMarker
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * This is an abstract class for a type of tree node called the action node
 * Action nodes represent actual actions that the agent can perform
 * These could be things like moving, attacking, or interacting with objects
 *
 * @property name a descriptive name of the actions' usage
 */
@BehaviourTreeDslMarker
abstract class Action<S>(override val name: String) : TreeNode<S>

fun action(
    name: String = "",
    func: () -> Status
) = object : Action<Any>(name) {
    override fun execute(state: Any): TreeNodeResult<Any> {
        return TreeNodeResult(this, func())
    }
}

@JvmName("actionWithState")
fun <S> action(
    name: String = "",
    func: () -> Status
) = object : Action<S>(name) {
    override fun execute(state: S): TreeNodeResult<S> {
        return TreeNodeResult(this, func())
    }
}