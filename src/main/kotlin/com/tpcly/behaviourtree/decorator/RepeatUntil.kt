package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode
import com.tpcly.behaviourtree.TreeNodeResult

class RepeatUntil(name: String = "", private val status: Status, child: TreeNode) : Decorator(name, child) {
    override fun execute(blackboard: MutableMap<String, Any>): TreeNodeResult {
        val results = mutableListOf<TreeNodeResult>()

        var result = child.execute(blackboard)
        results.add(result)

        while (result.status != status && result.status != Status.ABORT) {
            result = child.execute(blackboard)
            results.add(result)
        }

        return TreeNodeResult(this, result.status, results)
    }
}