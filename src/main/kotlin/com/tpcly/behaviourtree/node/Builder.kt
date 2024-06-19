package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * Leaf nodes
 */

fun run(
    name: String = "",
    func: () -> Status
) = object : Action(name) {
    override fun run(): Status = func()
}

fun condition(
    name: String = "",
    validate: () -> Boolean
) = object : Condition(name) {
    override fun validate(): Boolean = validate()
}

fun perform(
    name: String = "",
    action: () -> Unit
) = object : Perform(name) {
    override fun run() = action()
}

/**
 * Decorator nodes
 */
fun gate(
    name: String = "",
    validate: () -> Boolean,
    init: () -> TreeNode
) = object : Gate(name, init()) {
    override fun validate(): Boolean = validate()
}

fun inverter(
    name: String = "",
    init: () -> TreeNode
) = Inverter(name, init())

fun succeeder(
    name: String = "",
    init: () -> TreeNode
) = Succeeder(name, init())

fun repeatWhen(
    name: String = "",
    validate: () -> Boolean,
    limit: Int = 10,
    init: () -> TreeNode
) = object : RepeatWhen(name, limit, init()) {
    override fun validate(): Boolean = validate()
}

fun repeatUntil(
    validate: (TreeNodeResult) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode
) = object : RepeatUntil(name, limit, init()) {
    override fun validate(result: TreeNodeResult): Boolean = validate(result)
}

fun repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode
) = object : RepeatUntil(name, limit, init()) {
    override fun validate(result: TreeNodeResult): Boolean = result.status == status
}

/**
 * Composite nodes
 */

fun selector(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Selector.() -> Unit
) = initNode(Selector(name, executionOrder), init)

fun sequence(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Sequence.() -> Unit
) = initNode(Sequence(name, executionOrder), init)

internal fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}