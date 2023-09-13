package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.*

fun sequence(name: String = "", random: Boolean = false, init: Sequence.() -> Unit) = initNode(Sequence(name, random), init)

fun selector(name: String = "", random: Boolean = false, init: Selector.() -> Unit) = initNode(Selector(name, random), init)

fun inverter(name: String = "", init: () -> TreeNode) = Inverter(name, init())

fun TreeNode.inverted() = Inverter(name, this)

fun succeeder(name: String = "", init: () -> TreeNode): Succeeder = Succeeder(name, init())

fun repeatUntil(name: String = "", status: Status, init: () -> TreeNode) = RepeatUntil(name, status, init())

fun gate(name: String = "", validate: (blackboard: Blackboard) -> Boolean, init: () -> TreeNode) = Gate(name, validate, init())

fun gate(name: String = "", key: String, value: Any, init: () -> TreeNode) = Gate(name, { it[key] == value }, init())

fun action(name: String = "", func: (blackboard: Blackboard) -> Status) = object : Action(name) {
    override fun action(blackboard: Blackboard): Status {
        return func(blackboard)
    }
}

fun condition(name: String = "", predicate: (blackboard: Blackboard) -> Boolean) = Condition(name, predicate)

fun perform(name: String = "", func: (blackboard: Blackboard) -> Unit): Perform = Perform(name, func)

private fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}