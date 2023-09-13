package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.*

fun sequence(random: Boolean = false, name: String = "", init: Sequence.() -> Unit) = initNode(Sequence(name, random), init)

fun selector(random: Boolean = false, name: String = "", init: Selector.() -> Unit) = initNode(Selector(name, random), init)

fun inverter(name: String = "", init: () -> TreeNode) = Inverter(name, init())

fun TreeNode.inverted() = Inverter(name, this)

fun succeeder(name: String = "", init: () -> TreeNode): Succeeder = Succeeder(name, init())

fun repeatUntil(status: Status, name: String = "", init: () -> TreeNode) = RepeatUntil(name, status, init())

fun gate(validate: (blackboard: Blackboard) -> Boolean, name: String = "", init: () -> TreeNode) = Gate(name, validate, init())

fun gate(key: String, value: Any, name: String = "", init: () -> TreeNode) = Gate(name, { it[key] == value }, init())

fun action(name: String = "", func: (blackboard: Blackboard) -> Status) = object : Action(name) {
    override fun action(blackboard: Blackboard): Status {
        return func(blackboard)
    }
}

fun condition(predicate: (blackboard: Blackboard) -> Boolean, name: String = "") = Condition(name, predicate)

fun perform(name: String = "", func: (blackboard: Blackboard) -> Unit): Perform = Perform(name, func)

private fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}