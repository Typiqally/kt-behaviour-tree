package com.tpcly.osrs.api.tree

abstract class Decorator : TreeNode {
    lateinit var leaf: TreeNode

    override fun <T : TreeNode> initNode(node: T, init: T.() -> Unit): T {
        node.init()
        leaf = node
        return node
    }
}