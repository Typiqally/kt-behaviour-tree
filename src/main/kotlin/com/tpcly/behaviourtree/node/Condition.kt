package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which turns a boolean result into a status, with true being successful and false being failure
 *
 * @property predicate, a predicate function which holds the condition and is executed as part of the action node
 */
class Condition(
    override val name: String,
    val predicate: () -> Boolean
) : Action(name) {
    override fun execute(): TreeNodeResult {
        val status = when (predicate()) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }

        return TreeNodeResult(this, status)
    }

}