package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult
import com.tpcly.behaviourtree.node.composite.Selector
import com.tpcly.behaviourtree.node.composite.Sequencer
import com.tpcly.behaviourtree.node.decorator.*
import com.tpcly.behaviourtree.node.task.Action
import com.tpcly.behaviourtree.node.task.Conditional
import com.tpcly.behaviourtree.node.task.Perform

/**
 * Task nodes
 */
fun run(
    name: String = "",
    action: () -> Status,
) = object : Action(name) {
    override fun action(): Status = action()
}

fun conditional(
    name: String = "",
    validate: () -> Boolean,
) = object : Conditional(name) {
    override fun validate(): Boolean = validate()
}

fun perform(
    name: String = "",
    action: () -> Unit,
) = object : Perform(name) {
    override fun action() = action()
}

/**
 * Decorator nodes
 */
fun gate(
    name: String = "",
    validate: () -> Boolean,
    init: () -> TreeNode,
) = Gate(name, init(), validate)

fun inverter(
    name: String = "",
    init: () -> TreeNode,
) = Inverter(name, init())

fun succeeder(
    name: String = "",
    init: () -> TreeNode,
) = Succeeder(name, init())

fun repeatWhen(
    name: String = "",
    validate: () -> Boolean,
    limit: Int = 10,
    init: () -> TreeNode,
) = RepeatWhen(name, limit, init(), validate)

fun repeatUntil(
    validate: (TreeNodeResult) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode,
) = RepeatUntil(name, limit, init(), validate)

fun repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode,
) = RepeatUntil(name, limit, init()) { it.status == status }

/**
 * Composite nodes
 */

fun selector(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Selector.() -> Unit,
) = initNode(Selector(name, executionOrder), init)

fun sequencer(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Sequencer.() -> Unit,
) = initNode(Sequencer(name, executionOrder), init)

internal fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}