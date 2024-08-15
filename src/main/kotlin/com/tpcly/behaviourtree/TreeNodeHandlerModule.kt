package com.tpcly.behaviourtree

class TreeNodeHandlerModule(handlers: Map<String, TreeNodeHandler<*>>) {
    private val allHandlers = handlers + defaultHandlers

    // Justification: TreeNodeHandler has a generic class constraint
    @Suppress("UNCHECKED_CAST")
    operator fun get(child: TreeNode): TreeNodeHandler<TreeNode> {
        val className = child.javaClass.name
        val handler = allHandlers[className] ?: throw NullPointerException("Node $className not found")
        return handler as TreeNodeHandler<TreeNode>
    }

    companion object {
        private val defaultHandlers = mapOf(
            // Composite
            Sequencer::class.java.name to Sequencer.Handler(),
            Selector::class.java.name to Selector.Handler(),
            // Decorators
            RepeatUntil::class.java.name to RepeatUntil.Handler(),
            Succeeder::class.java.name to Succeeder.Handler(),
            Inverter::class.java.name to Inverter.Handler()
        )
    }
}