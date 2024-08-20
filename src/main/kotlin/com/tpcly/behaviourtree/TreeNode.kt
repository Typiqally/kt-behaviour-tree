package com.tpcly.behaviourtree

interface TreeNode {
    fun execute(): TreeNodeStatus

    interface Composite : TreeNode {
        val children: List<TreeNode>
    }

    interface Decorator : TreeNode {
        val child: TreeNode
    }

    interface Sub : TreeNode {
        val tree: TreeNode

        override fun execute(): TreeNodeStatus {
            return tree.execute()
        }
    }
}