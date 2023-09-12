package com.tpcly.behaviourtree

@BehaviourTreeDslMarker
abstract class Action(override val name: String) : TreeNode {
    abstract fun action(blackboard: Blackboard): Status

    override fun execute(blackboard: Blackboard): TreeNodeResult {
        return TreeNodeResult(this, action(blackboard))
    }
}