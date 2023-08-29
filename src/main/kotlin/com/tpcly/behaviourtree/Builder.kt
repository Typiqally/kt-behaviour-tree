package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.composite.Selector
import com.tpcly.behaviourtree.composite.Sequence
import com.tpcly.behaviourtree.decorator.Inverter
import com.tpcly.behaviourtree.decorator.RepeatUntil
import com.tpcly.behaviourtree.decorator.Succeeder
import com.tpcly.behaviourtree.leaf.Condition
import com.tpcly.behaviourtree.leaf.Leaf
import com.tpcly.behaviourtree.leaf.Perform

fun sequence(random: Boolean = false, init: Sequence.() -> Unit) = initNode(Sequence(random), init)

fun selector(random: Boolean = false, init: Selector.() -> Unit) = initNode(Selector(random), init)

fun inverter(init: () -> TreeNode) = Inverter(init())

fun succeeder(init: () -> TreeNode): Succeeder = Succeeder(init())

fun repeatUntil(status: Status, init: () -> TreeNode) = RepeatUntil(status, init())

fun leaf(func: () -> Status) = Leaf(func)

fun condition(predicate: () -> Boolean) = Condition(predicate)

fun perform(func: () -> Unit): Perform = Perform(func)

private fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}