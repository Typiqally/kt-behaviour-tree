package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.TreeNode
import com.tpcly.behaviourtree.TreeNodeResult

class Succeeder(name: String = "", child: TreeNode) : Decorator(name, child) {
    override fun execute(): TreeNodeResult {
        val result = child.execute()
        return TreeNodeResult.success(this, listOf(result))
    }
}