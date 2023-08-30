package com.tpcly.behaviourtree

@BehaviourTreeDslMarker
abstract class Action(override val name: String) : TreeNode {
    abstract fun action(): Status

    override fun execute(): TreeNodeResult {
        return TreeNodeResult(this, action())
    }
}