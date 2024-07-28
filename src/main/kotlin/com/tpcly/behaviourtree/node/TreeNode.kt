package com.tpcly.behaviourtree.node

interface TreeNode {
    val name: String
    val description: String

    interface Composite : TreeNode {
        val children: List<TreeNode>
    }

    interface Decorator : TreeNode {
        val child: TreeNode
    }
}