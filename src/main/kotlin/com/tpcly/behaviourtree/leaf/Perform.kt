package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Perform(val func: () -> Unit) : TreeNode {
    override fun execute(): Status {
        func()
        return Status.SUCCESS
    }
}