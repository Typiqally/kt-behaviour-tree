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
        ): TreeNodeHandler.Status {
            return when (val result = handlers.execute(descriptor.child)) {
                TreeNodeHandler.Status.SUCCESS -> TreeNodeHandler.Status.FAILURE
                TreeNodeHandler.Status.FAILURE -> TreeNodeHandler.Status.SUCCESS
                else -> result
            }
        }
    }
}