package com.tpcly.behaviourtree

object TreeBuilder {
    /**
     * Pre-defined leaf nodes
     */
    fun action(execute: () -> TreeNodeStatus): TreeNode = object : TreeNode {
        override fun execute(): TreeNodeStatus = execute()
    }

    fun conditional(validate: () -> Boolean): TreeNode = object : TreeNode {
        override fun execute(): TreeNodeStatus = TreeNodeStatus.fromBoolean(validate())
    }

    fun perform(execute: () -> Unit): TreeNode = object : TreeNode {
        override fun execute(): TreeNodeStatus {
            execute()
            return TreeNodeStatus.SUCCESS
        }
    }

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

    fun TreeNode.inverted() = Inverter(this)

    fun succeeder(init: () -> TreeNode) = Succeeder(init())

    fun failer(init: () -> TreeNode) = succeeder(init).inverted()

    fun repeatUntil(
        status: TreeNodeStatus = TreeNodeStatus.SUCCESS,
        limit: Int = 10,
        init: () -> TreeNode,
    ) = RepeatUntil(init(), status, limit)
}
