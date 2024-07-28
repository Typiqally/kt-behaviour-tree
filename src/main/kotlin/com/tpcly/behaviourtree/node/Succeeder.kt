package com.tpcly.behaviourtree.node

import kotlinx.serialization.Serializable

@Serializable
data class Succeeder(
    override val child: TreeNode,
) : TreeNode.Decorator {
    override val name: String = "succeeder"
    override val description: String = "Transforms the result of its child into success, regardless of what the child returns"

    class Handler : TreeNodeHandler<Succeeder> {
        override fun execute(
            handlers: TreeNodeHandlerCollection,
            descriptor: Succeeder,
        ): TreeNodeHandler.Status {
            val handler = handlers[descriptor.child.name]
            return handler.execute(handlers, descriptor.child)
        }
    }
}