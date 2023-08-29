package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Leaf(override val name: String = "", val func: () -> Status): TreeNode {
    override fun execute(callStack: ArrayDeque<TreeNode>): Status {
        return func()
    }
}