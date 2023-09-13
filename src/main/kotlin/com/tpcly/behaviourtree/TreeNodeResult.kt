package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode

data class TreeNodeResult(
    val parent: TreeNode,
    val status: Status,
    val children: List<TreeNodeResult>? = null
) {
    companion object {
        fun success(parent: TreeNode, children: List<TreeNodeResult>? = null) = TreeNodeResult(parent, Status.SUCCESS, children)

        fun failure(parent: TreeNode, children: List<TreeNodeResult>? = null) = TreeNodeResult(parent, Status.FAILURE, children)
    }
}