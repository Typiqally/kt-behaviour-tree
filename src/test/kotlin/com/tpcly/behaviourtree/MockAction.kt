package com.tpcly.behaviourtree

import kotlinx.serialization.Serializable

@Serializable
data class MockAction(
    val inputOne: String,
    val inputTwo: Int,
) : TreeNode {
    override fun execute(): TreeNodeStatus {
        println("$inputOne $inputTwo")
        return TreeNodeStatus.SUCCESS
    }
}