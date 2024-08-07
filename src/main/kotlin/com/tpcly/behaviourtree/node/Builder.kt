package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeExecutionOrder

/**
 * Composite nodes
 */
fun selector(
    executionOrder: TreeExecutionOrder = TreeExecutionOrder.IN_ORDER,
    init: TreeNodeCompositeBuilder.() -> Unit,
): Selector {
    val builder = TreeNodeCompositeBuilder().apply(init)
    return Selector(executionOrder, builder.build())
}

fun sequencer(
    executionOrder: TreeExecutionOrder = TreeExecutionOrder.IN_ORDER,
    init: TreeNodeCompositeBuilder.() -> Unit,
): Sequencer {
    val builder = TreeNodeCompositeBuilder().apply(init)
    return Sequencer(executionOrder, builder.build())
}

/**
 * Decorator nodes
 */
fun inverter(init: () -> TreeNode) = Inverter(init())

fun succeeder(init: () -> TreeNode) = Succeeder(init())

fun repeatUntil(
    status: TreeNodeHandler.Status = TreeNodeHandler.Status.SUCCESS,
    limit: Int = 10,
    init: () -> TreeNode,
) = RepeatUntil(init(), status, limit)