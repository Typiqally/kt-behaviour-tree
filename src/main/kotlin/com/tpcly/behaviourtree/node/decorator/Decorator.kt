package com.tpcly.behaviourtree.node.decorator

import com.tpcly.behaviourtree.BehaviourTreeDslMarker
import com.tpcly.behaviourtree.node.TreeNode

/**
 * This is an abstract class for a type of tree node called the decorator node
 * Decorator nodes are used to modify the behaviour of their child node
 *
 * @property name a descriptive name of the decorators' usage
 * @property child the child node which behaviour is modified by the decorator
 */
@BehaviourTreeDslMarker
sealed interface Decorator: TreeNode {
    val child: TreeNode
}