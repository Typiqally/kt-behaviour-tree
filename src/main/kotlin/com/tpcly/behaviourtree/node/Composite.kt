package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.BehaviourTreeDslMarker

/**
 * This is an abstract class for a type of tree node called the composite node
 * Composite nodes are used to process one or more children in a sequence depending on the implementation
 *
 * @property name a descriptive name of the composites' usage
 */
@BehaviourTreeDslMarker
abstract class Composite<in S : Any>(override val name: String) : TreeNode<S> {
    internal val children = mutableListOf<TreeNode<Any>>()

    @Suppress("UNCHECKED_CAST")
    operator fun TreeNode<@UnsafeVariance S>.unaryPlus() {
        children += this as TreeNode<Any>
    }

    @Suppress("UNCHECKED_CAST")
    operator fun TreeNode<@UnsafeVariance S>.unaryMinus() {
        children -= this as TreeNode<Any>
    }
}