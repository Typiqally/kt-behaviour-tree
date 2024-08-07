package com.tpcly.behaviourtree.node

import kotlinx.serialization.Serializable

@Serializable
data class Inverter(
    override val child: TreeNode,
) : TreeNode.Decorator {
    override val name: String = "inverter"
    override val description: String = "Inverts the result of its child, success becomes failure and vice-versa"

    class Handler : TreeNodeHandler<Inverter> {
        override fun execute(
            handlers: TreeNodeHandlerCollection,
            descriptor: Inverter,
        ): TreeNodeHandler.Status {
            val handler = handlers[descriptor.child.name]
            return when (val result = handler.execute(handlers, descriptor.child)) {
                TreeNodeHandler.Status.SUCCESS -> TreeNodeHandler.Status.FAILURE
                TreeNodeHandler.Status.FAILURE -> TreeNodeHandler.Status.SUCCESS
                else -> result
            }
        }
    }
}