package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Condition(override val name: String = "", val predicate: () -> Boolean) : TreeNode {
    override fun execute(callStack: ArrayDeque<TreeNode>): Status {
        return when (predicate()) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }
    }
}