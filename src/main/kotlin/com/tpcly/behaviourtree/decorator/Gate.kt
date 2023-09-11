package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.TreeNode
import com.tpcly.behaviourtree.TreeNodeResult

class Gate(
    name: String,
    val predicate: () -> Boolean,
    child: TreeNode
) : Decorator(name, child) {
    override fun execute(blackboard: MutableMap<String, Any>): TreeNodeResult {
        if (predicate()) {
            val result = child.execute(blackboard)
            return TreeNodeResult(this, result.status, listOf(result))
        }

        return TreeNodeResult.failure(this)
    }
}