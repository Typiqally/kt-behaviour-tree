package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * An action node which turns a boolean result into a status, with true being successful and false being failure
 *
 * @property predicate, a predicate function which holds the condition and is executed as part of the action node
 */
abstract class Condition<S>(
    override val name: String
) : Leaf<S> {
    abstract fun validate(state: S?): Boolean

    override fun execute(state: S?): TreeNodeResult<S> {
        val status = when (validate(state)) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }

        return TreeNodeResult(this, status)
    }
}