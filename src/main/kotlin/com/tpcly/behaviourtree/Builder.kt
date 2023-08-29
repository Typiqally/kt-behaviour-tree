package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.composite.Selector
import com.tpcly.behaviourtree.composite.Sequence
import com.tpcly.behaviourtree.decorator.Inverter
import com.tpcly.behaviourtree.decorator.RepeatUntil
import com.tpcly.behaviourtree.decorator.Succeeder
import com.tpcly.behaviourtree.leaf.Condition
import com.tpcly.behaviourtree.leaf.Leaf
import com.tpcly.behaviourtree.leaf.Perform

fun sequence(name: String = "", random: Boolean = false, init: Sequence.() -> Unit) = initNode(Sequence(name, random), init)

fun selector(name: String = "", random: Boolean = false, init: Selector.() -> Unit) = initNode(Selector(name, random), init)

fun inverter(name: String = "", init: () -> TreeNode) = Inverter(name, init())

fun succeeder(name: String = "", init: () -> TreeNode): Succeeder = Succeeder(name, init())

fun repeatUntil(name: String = "", status: Status, init: () -> TreeNode) = RepeatUntil(name, status, init())

fun leaf(name: String = "", func: () -> Status) = Leaf(name, func)

fun condition(name: String = "", predicate: () -> Boolean) = Condition(name, predicate)

fun perform(name: String = "", func: () -> Unit): Perform = Perform(name, func)

private fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}