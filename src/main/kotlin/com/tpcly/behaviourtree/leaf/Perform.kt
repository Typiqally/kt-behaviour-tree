package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.TreeNode
import com.tpcly.behaviourtree.TreeNodeResult

class Perform(override val name: String = "", val func: () -> Unit) : TreeNode {
    override fun execute(): TreeNodeResult {
        func()
        return TreeNodeResult.success(this)
    }
}