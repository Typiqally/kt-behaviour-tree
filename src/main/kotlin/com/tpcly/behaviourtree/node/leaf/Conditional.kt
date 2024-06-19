package com.tpcly.behaviourtree.node.leaf

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which turns a boolean result into a status, with true being successful and false being failure
 */
abstract class Conditional(
    override val name: String,
) : Leaf {
    abstract fun validate(): Boolean

    override fun execute(): TreeNodeResult {
        val status = when (validate()) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }

        return TreeNodeResult(this, status)
    }
}