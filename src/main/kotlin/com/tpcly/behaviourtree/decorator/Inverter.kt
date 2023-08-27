package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode


class Inverter(child: TreeNode) : Decorator(child) {
    override fun execute(): Status {
        return when (val result = child.execute()) {
            Status.SUCCESS -> Status.FAILURE
            Status.FAILURE -> Status.SUCCESS
            else -> result
        }
    }
}