package com.tpcly.behaviourtree

interface TreeNodeHandler<in T : TreeNode> {
    fun execute(
        handlers: TreeNodeHandlerModule,
        descriptor: T,
    ): TreeNodeStatus
}