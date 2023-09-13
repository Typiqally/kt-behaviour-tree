package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.BehaviourTreeDslMarker

/**
 * This is an abstract class for a type of tree node called the decorator node
 * Decorator nodes are used to modify the behaviour of their child node
 *
 * @property name a descriptive name of the decorators' usage
 * @property child the child node which behaviour is modified by the decorator
 */
@BehaviourTreeDslMarker
abstract class Decorator(
    override val name: String,
    val child: TreeNode
) : TreeNode