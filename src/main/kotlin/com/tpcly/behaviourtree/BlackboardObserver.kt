package com.tpcly.behaviourtree

interface BlackboardObserver {
    fun update(key: String, value: Any)
}