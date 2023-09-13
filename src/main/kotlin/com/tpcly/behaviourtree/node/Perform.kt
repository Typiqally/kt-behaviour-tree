package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status

/**
 * An action node which performs an action and always returns a successful result
 *
 * @property func, a unit which is executed as part of the action node
 */
class Perform(
    override val name: String,
    val func: (blackboard: Blackboard) -> Unit
) : Action(name) {
    override fun action(blackboard: Blackboard): Status {
        func(blackboard)
        return Status.SUCCESS
    }
}