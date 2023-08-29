package com.tpcly.behaviourtree

@BehaviourTreeDslMarker
abstract class Composite : TreeNode {
    val children = mutableListOf<TreeNode>()

    operator fun TreeNode.unaryPlus() {
        children += this
    }

    operator fun TreeNode.unaryMinus() {
        children -= this
    }
}