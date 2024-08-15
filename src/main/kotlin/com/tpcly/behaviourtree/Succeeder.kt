package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class Succeeder(
    override val child: TreeNode,
) : TreeNode.Decorator {
    class Handler : TreeNodeHandler<Succeeder> {
        override fun execute(
            handlers: TreeNodeHandlerModule,
            descriptor: Succeeder,
        ): TreeNodeStatus {
            handlers.execute(descriptor.child)
            return TreeNodeStatus.SUCCESS
        }
    }
}