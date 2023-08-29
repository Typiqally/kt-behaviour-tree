package com.tpcly.behaviourtree

@BehaviourTreeDslMarker
abstract class Decorator(val child: TreeNode) : TreeNode {
}