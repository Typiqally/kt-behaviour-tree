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
            val handler = handlers[descriptor.child]
            return when (val result = handler.execute(handlers, descriptor.child)) {
                TreeNodeHandler.Status.SUCCESS -> TreeNodeHandler.Status.FAILURE
                TreeNodeHandler.Status.FAILURE -> TreeNodeHandler.Status.SUCCESS
                else -> result
            }
        }
    }
}