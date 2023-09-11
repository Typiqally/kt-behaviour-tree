package com.tpcly.behaviourtree

interface TreeNode {
    val name: String

    fun execute(blackboard: MutableMap<String, Any>): TreeNodeResult
}