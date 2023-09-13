package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.TreeNodeResult

interface TreeNode {
    val name: String

    fun execute(blackboard: Blackboard = Blackboard()): TreeNodeResult
}