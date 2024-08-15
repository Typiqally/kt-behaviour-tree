package com.tpcly.behaviourtree

interface TreeNode {
    interface Composite : TreeNode {
        val children: List<TreeNode>
    }

    interface Decorator : TreeNode {
        val child: TreeNode
    }
}