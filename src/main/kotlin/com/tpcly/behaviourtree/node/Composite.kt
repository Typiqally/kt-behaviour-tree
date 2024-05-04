package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.BehaviourTreeDslMarker

/**
 * This is an abstract class for a type of tree node called the composite node
 * Composite nodes are used to process one or more children in a sequence depending on the implementation
 *
 * @property name a descriptive name of the composites' usage
 */
@BehaviourTreeDslMarker
sealed interface Composite<S> : TreeNode<S> {
    val children: MutableList<TreeNode<S>>

    operator fun TreeNode<S>.unaryPlus() {
        children += this
    }

    operator fun TreeNode<S>.unaryMinus() {
        children -= this
    }
}