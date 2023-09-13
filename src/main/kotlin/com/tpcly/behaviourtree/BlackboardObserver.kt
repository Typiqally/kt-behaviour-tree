package com.tpcly.behaviourtree

/**
 * Interface for an observer of a blackboard
 */
interface BlackboardObserver {
    /**
     * Called when a value of the blackboard is updated
     *
     * @param key the key of the updated entry
     * @param value the value of the updated entry
     */
    fun update(key: String, value: Any)
}