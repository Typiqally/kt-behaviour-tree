package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.ExecutionOrder
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * Leaf nodes
 */
fun <S> run(
    name: String = "",
    func: (state: S?) -> Status
) = object : Action<S>(name) {
    override fun run(state: S?): Status = func(state)
}

@JvmName("voidAction")
fun run(
    name: String = "",
    func: () -> Status
) = object : Action<Unit>(name) {
    override fun run(state: Unit?): Status = func()
}

fun <S> condition(
    name: String = "",
    validate: (state: S?) -> Boolean
) = object : Condition<S>(name) {
    override fun validate(state: S?): Boolean = validate(state)
}

@JvmName("voidCondition")
fun condition(
    name: String = "",
    validate: () -> Boolean
) = object : Condition<Unit>(name) {
    override fun validate(state: Unit?): Boolean = validate()
}


fun <S> perform(
    name: String = "",
    action: (state: S?) -> Unit
) = object : Perform<S>(name) {
    override fun run(state: S?) = action(state)
}

@JvmName("voidPerform")
fun perform(
    name: String = "",
    action: () -> Unit
) = object : Perform<Unit>(name) {
    override fun run(state: Unit?) = action()
}

/**
 * Decorator nodes
 */
fun <S> gate(
    name: String = "",
    validate: (state: S?) -> Boolean,
    init: () -> TreeNode<S>
) = object : Gate<S>(name, init()) {
    override fun validate(state: S?): Boolean = validate(state)
}

@JvmName("voidGate")
fun gate(
    name: String = "",
    validate: () -> Boolean,
    init: () -> TreeNode<Unit>
) = object : Gate<Unit>(name, init()) {
    override fun validate(state: Unit?): Boolean = validate()
}

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
    validate: (state: S?) -> Boolean,
    limit: Int = 10,
    init: () -> TreeNode<S>
) = object : RepeatWhen<S>(name, limit, init()) {
    override fun validate(state: S?): Boolean = validate(state)
}

@JvmName("voidRepeatWhen")
fun repeatWhen(
    name: String = "",
    validate: () -> Boolean,
    limit: Int = 10,
    init: () -> TreeNode<Unit>
) = object : RepeatWhen<Unit>(name, limit, init()) {
    override fun validate(state: Unit?): Boolean = validate()
}

fun <S> repeatUntil(
    validate: (TreeNodeResult<S>) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<S>
) = object : RepeatUntil<S>(name, limit, init()) {
    override fun validate(result: TreeNodeResult<S>): Boolean = validate(result)
}

@JvmName("voidRepeatUntil")
fun repeatUntil(
    validate: (TreeNodeResult<Unit>) -> Boolean,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<Unit>
) = object : RepeatUntil<Unit>(name, limit, init()) {
    override fun validate(result: TreeNodeResult<Unit>): Boolean = validate(result)
}

fun <S> repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<S>
) = object : RepeatUntil<S>(name, limit, init()) {
    override fun validate(result: TreeNodeResult<S>): Boolean = result.status == status
}

@JvmName("voidRepeatUntil")
fun repeatUntil(
    status: Status,
    limit: Int = 10,
    name: String = "",
    init: () -> TreeNode<Unit>
) = object : RepeatUntil<Unit>(name, limit, init()) {
    override fun validate(result: TreeNodeResult<Unit>): Boolean = result.status == status
}

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