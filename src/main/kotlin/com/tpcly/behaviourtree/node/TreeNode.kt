package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.TreeNodeResult

/**
 * Interface for a node of an executable tree
 */
interface TreeNode<in S> {
    /**
     * A descriptive name for the tree node
     */
    val name: String

    /**
     * Executes a certain behaviour of the node
     * @return the result of the execution, including relevant children results
     */
    fun execute(state: S): TreeNodeResult<S>
}

fun TreeNode<Any>.execute(): TreeNodeResult<Any> {
    return execute(Any())
}

internal fun <T : TreeNode<S>, S> initNode(node: T, init: T.() -> Unit): T {
    node.init()
    return node
}