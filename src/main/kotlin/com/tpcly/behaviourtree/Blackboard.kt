package com.tpcly.behaviourtree

class Blackboard(val values: MutableMap<String, Any> = mutableMapOf()) {
    private val observers = mutableListOf<BlackboardObserver>()

    fun attach(observer: BlackboardObserver) {
        observers.add(observer)
    }

    fun detach(observer: BlackboardObserver) {
        observers.remove(observer)
    }

    inline operator fun <reified T> get(key: String): T? {
        val value = values[key] ?: return null

        return if (value is T) {
            value
        } else error("Value at key $key is not of type ${T::class.java.name}")
    }

    operator fun <T : Any> set(key: String, value: T) {
        values[key] = value
        observers.forEach { it.update(key, value) }
    }
}