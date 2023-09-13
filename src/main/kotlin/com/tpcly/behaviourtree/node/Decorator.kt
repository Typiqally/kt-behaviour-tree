package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.BehaviourTreeDslMarker

@BehaviourTreeDslMarker
abstract class Decorator(override val name: String, val child: TreeNode) : TreeNode