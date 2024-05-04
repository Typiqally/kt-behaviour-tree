package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * Leaf nodes
 */
fun <S> action(
    name: String = "",
    func: (state: S?) -> Status
) = Action(name, func)

@JvmName("voidAction")
fun action(
    name: String = "",
    func: (state: Unit?) -> Status
) = Action(name, func)

fun <S> condition(
    name: String = "",
    predicate: (state: S?) -> Boolean
) = Condition(name, predicate)

@JvmName("voidCondition")
fun condition(
    name: String = "",
    predicate: (state: Unit?) -> Boolean
) = Condition(name, predicate)


fun <S> perform(
    name: String = "",
    func: (state: S?) -> Unit
) = Perform(name, func)

@JvmName("voidPerform")
fun perform(
    name: String = "",
    func: (state: Unit?) -> Unit
) = Perform(name, func)

/**
 * Decorator nodes
 */
fun <S> gate(
    name: String = "",
    validate: () -> Boolean,
    init: () -> TreeNode<S>
) = Gate(name, validate, init())

@JvmName("voidGate")
fun gate(
    name: String = "",
    validate: () -> Boolean,
    init: () -> TreeNode<Unit>
) = Gate(name, validate, init())

fun <S> inverter(
    name: String = "",
    init: () -> TreeNode<S>
) = Inverter(name, init())

@JvmName("voidInverter")
fun inverter(
    name: String = "",
    init: () -> TreeNode<Unit>
) = Inverter(name, init())

fun <S> succeeder(
    name: String = "",
    init: () -> TreeNode<S>
) = Succeeder(name, init())

@JvmName("voidSucceeder")
fun succeeder(
    name: String = "",
    init: () -> TreeNode<Unit>
) = Succeeder(name, init())

fun <S> repeatWhen(
    name: String = "",
    condition: () -> Boolean,
    limit: Int = 10,
    init: () -> TreeNode<S>
) = RepeatWhen(name, condition, limit, init())

@JvmName("voidRepeatWhen")
fun repeatWhen(
    name: String = "",
    condition: () -> Boolean,
    limit: Int = 10,
    init: () -> TreeNode<Unit>
) = RepeatWhen(name, condition, limit, init())

fun <S> repeatUntil(
    predicate: (TreeNodeResult<S>) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<S>
) = RepeatUntil(name, predicate, limit, init())

@JvmName("voidRepeatUntil")
fun repeatUntil(
    predicate: (TreeNodeResult<Unit>) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<Unit>
) = RepeatUntil(name, predicate, limit, init())

fun <S> repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<S>
) = RepeatUntil(name, status, limit, init())

@JvmName("voidRepeatUntil")
fun repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<Unit>
) = RepeatUntil(name, status, limit, init())

/**
 * Composite nodes
 */
fun <S> selector(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Selector<S>.() -> Unit
) = initNode(Selector(name, executionOrder), init)

@JvmName("voidSelector")
fun selector(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Selector<Unit>.() -> Unit
) = initNode(Selector(name, executionOrder), init)

fun <S> sequence(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Sequence<S>.() -> Unit
) = initNode(Sequence(name, executionOrder), init)

@JvmName("voidSequence")
fun sequence(
    name: String = "",
    executionOrder: ExecutionOrder = ExecutionOrder.IN_ORDER,
    init: Sequence<Unit>.() -> Unit
) = initNode(Sequence(name, executionOrder), init)


internal fun <T : TreeNode<S>, S> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}