package com.tpcly.osrs.api.tree

interface TreeNode {
    fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T

    fun execute(): Status
}