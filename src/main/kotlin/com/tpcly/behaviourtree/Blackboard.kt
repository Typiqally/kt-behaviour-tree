package com.tpcly.behaviourtree

/**
 * The blackboard is a simple map where data can be written and read
 * The blackboard is mostly used for caching conditions, and making decisions based on these conditions
 *
 * @property values an initial map of values for the blackboard
 */
class Blackboard(private val values: MutableMap<String, Any> = mutableMapOf()) {
    private val observers = mutableListOf<BlackboardObserver>()

    /**
     * Attaches an observer to the blackboard
     */
    fun attach(observer: BlackboardObserver) {
        observers.add(observer)
    }

    /**
     * Detaches an observer from the blackboard
     */
    fun detach(observer: BlackboardObserver) {
        observers.remove(observer)
    }

    /**
     * Reads an item from the blackboard
     *
     * @param key the key of the element
     * @return the value of the entry specified at the [key]
     */
    operator fun get(key: String): Any? {
        return values[key]
    }

    /**
     * Writes an entry to the blackboard
     *
     * @param key the key of the entry
     * @param value the value of the entry
     */
    operator fun <T : Any> set(key: String, value: T) {
        values[key] = value
        observers.forEach { it.update(key, value) }
    }
}