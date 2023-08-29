package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Succeeder(name: String = "", child: TreeNode) : Decorator(name, child) {
    override fun execute(callStack: ArrayDeque<TreeNode>): Status {
        child.executeTrace(callStack)
        return Status.SUCCESS
    }
}