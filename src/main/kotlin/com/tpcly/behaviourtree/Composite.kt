package com.tpcly.osrs.api.tree

abstract class Composite : TreeNode {
    private val children = mutableListOf<TreeNode>()

    fun sequence(init: Sequence.() -> Unit) = initNode(Sequence(), init)

    fun selector(init: Selector.() -> Unit) = initNode(Selector(), init)

    fun randomSequence(init: RandomSequence.() -> Unit) = initNode(RandomSequence(), init)

    fun inverter(init: Inverter.() -> Unit) = initNode(Inverter(), init)

    fun succeeder(init: Succeeder.() -> Unit) = initNode(Succeeder(), init)

    fun repeatUntil(status: Status, init: RepeatUntil.() -> Unit) = initNode(RepeatUntil(), init)

    override fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
        node.init()
        children.add(node)
        return node
    }
}