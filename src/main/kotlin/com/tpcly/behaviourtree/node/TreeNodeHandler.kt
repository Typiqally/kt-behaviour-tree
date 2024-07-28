package com.tpcly.behaviourtree.node

interface TreeNodeHandler<in T : TreeNode> {
    fun execute(
        handlers: TreeNodeHandlerCollection,
        descriptor: T,
    ): Status

    enum class Status {
        SUCCESS,
        FAILURE,
        ABORT;

        companion object {
            fun fromBoolean(value: Boolean): Status = if (value) SUCCESS else FAILURE
        }
    }
}