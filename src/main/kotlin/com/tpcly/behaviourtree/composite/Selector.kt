package com.tpcly.behaviourtree.composite

import com.tpcly.behaviourtree.Composite
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Selector(name: String = "", private val random: Boolean) : Composite(name) {
    override fun execute(callStack: ArrayDeque<TreeNode>): Status {
        val children = if (random) {
            children.shuffled()
        } else {
            children
        }

        for (child in children) {
            val result = child.executeTrace(callStack)

            if (result == Status.SUCCESS) {
                return result
            }
        }

        return Status.FAILURE
    }
}