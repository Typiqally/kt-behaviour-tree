package com.tpcly.behaviourtree

class TreeNodeHandlerModuleBuilder {
    val handlers: MutableMap<String, TreeNodeHandler<*>> = mutableMapOf()

    fun <T : TreeNode> handler(name: String, handler: TreeNodeHandler<T>) {
        handlers[name] = handler
    }

    internal fun build(): TreeNodeHandlerModule {
        return TreeNodeHandlerModule(handlers)
    }
}

fun TreeNodeHandlerModule(builderAction: TreeNodeHandlerModuleBuilder.() -> Unit): TreeNodeHandlerModule {
    val builder = TreeNodeHandlerModuleBuilder()
    builder.builderAction()
    return builder.build()
}