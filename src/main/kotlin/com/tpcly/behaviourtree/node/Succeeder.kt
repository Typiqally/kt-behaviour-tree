package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.TreeNodeResult

class Succeeder(name: String, child: TreeNode) : Decorator(name, child) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val result = child.execute(blackboard)
        return TreeNodeResult.success(this, listOf(result))
    }
}