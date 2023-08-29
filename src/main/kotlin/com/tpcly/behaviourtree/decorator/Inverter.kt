package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode


class Inverter(name: String, child: TreeNode) : Decorator(name, child) {
    override fun execute(callStack: ArrayDeque<TreeNode>): Status {
        return when (val result = child.executeTrace(callStack)) {
            Status.SUCCESS -> Status.FAILURE
            Status.FAILURE -> Status.SUCCESS
            else -> result
        }
    }
}