package com.tpcly.behaviourtree

interface TreeNode {
    val name: String

    fun execute(blackboard: Blackboard): TreeNodeResult
}