package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode
import com.tpcly.behaviourtree.TreeNodeResult

class Condition(override val name: String = "", val predicate: () -> Boolean) : TreeNode {
    override fun execute(): TreeNodeResult {
        val status = when (predicate()) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }

        return TreeNodeResult(this, status)
    }
}