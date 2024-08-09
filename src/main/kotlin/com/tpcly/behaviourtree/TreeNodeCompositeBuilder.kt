package com.tpcly.behaviourtree

class TreeNodeCompositeBuilder {
    private val children: MutableList<TreeNode> = mutableListOf()

    operator fun TreeNode.unaryPlus() {
        children += this
    }

    operator fun TreeNode.unaryMinus() {
        children -= this
    }

    fun build(): List<TreeNode> {
        return children.toList()
    }
}