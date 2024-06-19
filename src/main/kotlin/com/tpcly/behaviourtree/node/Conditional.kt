package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which turns a boolean result into a status, with true being successful and false being failure
 */
open class Conditional(
    override val name: String,
    val validate: () -> Boolean
) : Leaf {
    override fun execute(): TreeNodeResult {
        val status = when (validate()) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }

        return TreeNodeResult(this, status)
    }
}