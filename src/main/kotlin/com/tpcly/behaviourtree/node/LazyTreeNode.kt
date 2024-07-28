package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

abstract class LazyTreeNode : TreeNode {
    private val node: TreeNode by lazy { build() }

    abstract fun build(): TreeNode

    override fun execute(): TreeNodeResult {
        return node.execute()
    }
}