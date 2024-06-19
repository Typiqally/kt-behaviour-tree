package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * Leaf nodes
 */

fun run(
    name: String = "",
    action: () -> Status,
) = Action(name, action)

fun condition(
    name: String = "",
    validate: () -> Boolean,
) =  Condition(name, validate)

fun perform(
    name: String = "",
    action: () -> Unit,
) = Perform(name, action)

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