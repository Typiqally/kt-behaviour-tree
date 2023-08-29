package com.tpcly.behaviourtree

interface TreeNode {
    val name: String

    fun execute(callStack: ArrayDeque<TreeNode> = ArrayDeque()): Status

    fun executeTrace(callStack: ArrayDeque<TreeNode>): Status {
        trace(callStack)
        return execute(callStack)
    }

    fun trace(callStack: ArrayDeque<TreeNode>) {
        callStack.add(this)
    }
}