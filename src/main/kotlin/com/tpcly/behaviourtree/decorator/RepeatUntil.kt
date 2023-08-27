package com.tpcly.behaviourtree.decorator

import com.tpcly.behaviourtree.Decorator
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class RepeatUntil(private val status: Status, child: TreeNode) : Decorator(child) {
    override fun execute(): Status {
        var result = child.execute()

        while (result != status) {
            result = child.execute()
        }

        return result
    }
}