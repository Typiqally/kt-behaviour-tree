package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.BehaviourTreeDslMarker
import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

@BehaviourTreeDslMarker
abstract class Action(override val name: String) : TreeNode {
    abstract fun action(blackboard: Blackboard): Status

    override fun execute(blackboard: Blackboard): TreeNodeResult {
        return TreeNodeResult(this, action(blackboard))
    }
}