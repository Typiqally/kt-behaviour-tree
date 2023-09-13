package com.tpcly.behaviourtree

class Blackboard(private val values: MutableMap<String, Any> = mutableMapOf()) {
    private val observers = mutableListOf<BlackboardObserver>()

    fun attach(observer: BlackboardObserver) {
        observers.add(observer)
    }

    fun detach(observer: BlackboardObserver) {
        observers.remove(observer)
    }

    operator fun get(key: String): Any? {
        return values[key]
    }

    operator fun <T : Any> set(key: String, value: T) {
        values[key] = value
        observers.forEach { it.update(key, value) }
    }
}