package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class RepeatUntil(name: String = "", private val status: Status, child: TreeNode) : Decorator(name, child) {
    override fun execute(callStack: ArrayDeque<TreeNode>): Status {
        trace(callStack)

        var result = child.executeTrace(callStack)

        while (result != status) {
            result = child.executeTrace(callStack)
        }

        return result
    }
}