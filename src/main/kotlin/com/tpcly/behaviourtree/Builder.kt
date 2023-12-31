package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.*

fun sequence(name: String = "", order: ExecutionOrder = ExecutionOrder.IN_ORDER, init: Sequence.() -> Unit) = initNode(Sequence(name, order), init)

fun selector(name: String = "", order: ExecutionOrder = ExecutionOrder.IN_ORDER, init: Selector.() -> Unit) = initNode(Selector(name, order), init)

fun inverter(name: String = "", init: () -> TreeNode) = Inverter(name, init())

fun TreeNode.inverted() = Inverter(name, this)

fun succeeder(name: String = "", init: () -> TreeNode): Succeeder = Succeeder(name, init())

fun repeatUntil(stopCondition: (TreeNodeResult) -> Boolean, limit: Int = 10, name: String = "", init: () -> TreeNode) =
    RepeatUntil(name, stopCondition, limit, init())

fun repeatUntil(status: Status, limit: Int = 10, name: String = "", init: () -> TreeNode) = RepeatUntil(name, status, limit, init())

fun repeatWhen(continueCondition: () -> Boolean, limit: Int = 10, name: String = "", init: () -> TreeNode) = RepeatWhen(name, continueCondition, limit, init())

fun gate(validate: (blackboard: Blackboard) -> Boolean, name: String = "", init: () -> TreeNode) = Gate(name, validate, init())

fun gate(key: String, value: Any, name: String = "", init: () -> TreeNode) = Gate(name, { it[key] == value }, init())

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