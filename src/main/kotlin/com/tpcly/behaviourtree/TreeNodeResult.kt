package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.TreeNode

/**
 * This class holds the result for each executed node and its children
 *
 * @property origin the origin node of the result
 * @property status the status of the result
 * @property children the children of the result
 */
data class TreeNodeResult<in S>(
    val origin: TreeNode<S>,
    val status: Status,
    val children: List<TreeNodeResult<S>>? = null
) {
    fun stackTrace(indent: Int = 0): Sequence<String> = sequence<String> {
        yield("${"\t".repeat(indent)}> ${origin.javaClass.simpleName} :${origin.name} ${status.name}")

        // Append children if they exist
        children?.forEach {
            yieldAll(it.stackTrace(indent + 1))
        }
    }

    companion object {
        fun <S> success(parent: TreeNode<S>, children: List<TreeNodeResult<S>>? = null) = TreeNodeResult(parent, Status.SUCCESS, children)

        fun <S> failure(parent: TreeNode<S>, children: List<TreeNodeResult<S>>? = null) = TreeNodeResult(parent, Status.FAILURE, children)
    }
}