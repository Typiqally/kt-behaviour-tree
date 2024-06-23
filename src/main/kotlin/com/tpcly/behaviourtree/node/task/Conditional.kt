package com.tpcly.behaviourtree.node.task

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which turns a boolean result into a status, with true being successful and false being failure
 */
abstract class Conditional(
    override val name: String,
) : Task {
    abstract fun validate(): Boolean

    override fun execute(): TreeNodeResult = TreeNodeResult(this, Status.fromCondition(validate()))
}