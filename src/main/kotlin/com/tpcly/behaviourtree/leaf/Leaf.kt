package com.tpcly.behaviourtree.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNode
import com.tpcly.behaviourtree.TreeNodeResult

class Leaf(override val name: String = "", val func: () -> Status) : TreeNode {
    override fun execute(): TreeNodeResult {
        return TreeNodeResult(this, func())
    }
}