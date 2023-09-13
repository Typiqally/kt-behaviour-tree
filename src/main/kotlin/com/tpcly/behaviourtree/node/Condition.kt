package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status

/**
 * An action node which turns a boolean result into a status, with true being successful and false being failure
 *
 * @property predicate, a predicate function which holds the condition and is executed as part of the action node
 */
class Condition(
    override val name: String,
    val predicate: (blackboard: Blackboard) -> Boolean
) : Action(name) {
    override fun action(blackboard: Blackboard): Status {
        return when (predicate(blackboard)) {
            true -> Status.SUCCESS
            false -> Status.FAILURE
        }
    }
}