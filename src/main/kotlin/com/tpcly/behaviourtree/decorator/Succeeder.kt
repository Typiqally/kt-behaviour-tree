package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.TreeNode
import com.tpcly.behaviourtree.TreeNodeResult

class Succeeder(name: String = "", child: TreeNode) : Decorator(name, child) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val result = child.execute(blackboard)
        return TreeNodeResult.success(this, listOf(result))
    }
}