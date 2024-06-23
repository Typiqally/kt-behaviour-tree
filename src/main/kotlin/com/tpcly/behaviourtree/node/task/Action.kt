package com.tpcly.behaviourtree.node.task

import com.tpcly.behaviourtree.Status
import com.tpcly.behaviourtree.TreeNodeResult

/**
 * This is an abstract class for a type of tree node called the action node
 * Action nodes represent actual actions that the agent can perform
 * These could be things like moving, attacking, or interacting with objects
 *
 * @property name a descriptive name of the actions' usage
 */
abstract class Action(
    override val name: String,
) : Task {
    abstract fun action(): Status

    override fun execute(): TreeNodeResult = TreeNodeResult(this, action())
}