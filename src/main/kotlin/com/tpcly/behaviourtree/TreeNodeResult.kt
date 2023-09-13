package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode

/**
 * This class holds the result for each executed node and its children
 *
 * @property parent the origin node of the result
 * @property status the status of the result
 * @property children the children of the result
 */
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