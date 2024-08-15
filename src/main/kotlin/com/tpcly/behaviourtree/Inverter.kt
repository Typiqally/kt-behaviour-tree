package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class Inverter(
    override val child: TreeNode,
) : TreeNode.Decorator {
    class Handler : TreeNodeHandler<Inverter> {
        override fun execute(
            handlers: TreeNodeHandlerModule,
            descriptor: Inverter,
        ): TreeNodeStatus {
            return when (val result = handlers.execute(descriptor.child)) {
                TreeNodeStatus.SUCCESS -> TreeNodeStatus.FAILURE
                TreeNodeStatus.FAILURE -> TreeNodeStatus.SUCCESS
                else -> result
            }
        }
    }
}