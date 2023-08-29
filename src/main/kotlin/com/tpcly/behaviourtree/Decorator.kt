package com.tpcly.behaviourtree

@BehaviourTreeDslMarker
abstract class Decorator(override val name: String, val child: TreeNode) : TreeNode