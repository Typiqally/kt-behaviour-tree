package com.tpcly.behaviourtree.node

class TreeNodeHandlerCollection(
    handlers: Map<String, TreeNodeHandler<*>>,
) {
    private val allHandlers = handlers + defaultHandlers

    // Justification: TreeNodeHandler has a generic class constraint
    @Suppress("UNCHECKED_CAST")
    operator fun get(name: String): TreeNodeHandler<TreeNode> {
        val handler = allHandlers[name] ?: throw NullPointerException("Node $name not found")
        return handler as TreeNodeHandler<TreeNode>
    }

    companion object {
        private val defaultHandlers = mapOf(
            // Composite
            "sequencer" to Sequencer.Handler(),
            "selector" to Selector.Handler(),
            // Decorators
            "repeat_until" to RepeatUntil.Handler(),
            "succeeder" to Succeeder.Handler(),
            "inverter" to Inverter.Handler()
        )
    }
}