package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Succeeder(child: TreeNode) : Decorator(child) {
    override fun execute(): Status {
        child.execute()
        return Status.SUCCESS
    }
}