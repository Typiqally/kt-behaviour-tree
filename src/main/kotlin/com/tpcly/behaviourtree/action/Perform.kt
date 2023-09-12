package com.tpcly.behaviourtree.action

import com.tpcly.behaviourtree.Action
import com.tpcly.behaviourtree.Blackboard
import com.tpcly.behaviourtree.Status

class Perform(
    override val name: String = "",
    val func: (blackboard: Blackboard) -> Unit
) : Action(name) {
    override fun action(blackboard: Blackboard): Status {
        func(blackboard)
        return Status.SUCCESS
    }
}