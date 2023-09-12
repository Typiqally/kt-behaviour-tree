package com.tpcly.behaviourtree.composite

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Composite
import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

class Sequence(name: String = "", private val random: Boolean) : Composite(name) {
    override fun execute(blackboard: Blackboard): TreeNodeResult {
        val children = if (random) {
            children.shuffled()
        } else {
            children
        }

        val results = mutableListOf<TreeNodeResult>()

        for (child in children) {
            val result = child.execute(blackboard)
            results.add(result)

            if (result.status == Status.FAILURE || result.status == Status.ABORT) {
                return TreeNodeResult(this, result.status, results)
            }
        }

        return TreeNodeResult.success(this, results)
    }
}