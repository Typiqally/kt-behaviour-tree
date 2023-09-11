package com.tpcly.behaviourtree

@BehaviourTreeDslMarker
abstract class Action(override val name: String) : TreeNode {
    abstract fun action(blackboard: MutableMap<String, Any>): Status

    override fun execute(blackboard: MutableMap<String, Any>): TreeNodeResult {
        return TreeNodeResult(this, action(blackboard))
    }
}