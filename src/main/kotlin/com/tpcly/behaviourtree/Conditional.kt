package com.tpcly.behaviourtree

abstract class Conditional : TreeNode {
    abstract fun validate(): Boolean

    override fun execute(): TreeNodeStatus {
        return TreeNodeStatus.fromBoolean(validate())
    }
}