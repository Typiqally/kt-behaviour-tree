package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Condition(val predicate: () -> Boolean) : TreeNode {
    override fun execute(): Status {
        return when (predicate()) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }
    }
}