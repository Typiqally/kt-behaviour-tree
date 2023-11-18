package com.tpcly.behaviourtree.node

import com.tpcly.behaviourtree.BehaviourTreeDslMarker

/**
 * This is an abstract class for a type of tree node called the action node
 * Action nodes represent actual actions that the agent can perform
 * These could be things like moving, attacking, or interacting with objects
 *
 * @property name a descriptive name of the actions' usage
 */
@BehaviourTreeDslMarker
abstract class Action(override val name: String) : TreeNode