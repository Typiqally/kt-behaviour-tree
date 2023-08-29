package com.tpcly.behaviourtree

@BehaviourTreeDslMarker
abstract class Composite(override val name: String) : TreeNode {
    val children = mutableListOf<TreeNode>()

    operator fun TreeNode.unaryPlus() {
        children += this
    }

    operator fun TreeNode.unaryMinus() {
        children -= this
    }
}