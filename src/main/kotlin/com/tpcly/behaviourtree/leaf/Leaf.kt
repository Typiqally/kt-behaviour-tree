package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode

class Leaf(val func: () -> Status) : TreeNode {
    override fun execute(): Status {
        return func()
    }
}