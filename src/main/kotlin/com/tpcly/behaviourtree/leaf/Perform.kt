package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Perform(override val name: String = "", val func: () -> Unit) : TreeNode {
    override fun execute(callStack: ArrayDeque<TreeNode>): Status {
        func()
        return Status.SUCCESS
    }
}