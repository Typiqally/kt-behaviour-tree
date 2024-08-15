package com.tpcly.behaviourtree

class TreeNodeHandlerModuleBuilder {
    val handlers: MutableMap<String, TreeNodeHandler<*>> = mutableMapOf()

    inline fun <reified T : TreeNode> handler(handler: TreeNodeHandler<T>) {
        handlers[T::class.java.name] = handler
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