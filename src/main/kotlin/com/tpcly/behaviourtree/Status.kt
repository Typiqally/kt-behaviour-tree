package com.tpcly.behaviourtree

/**
 * Enum of all possible tree result statuses
 */
enum class Status {
    SUCCESS,
    FAILURE,
    RUNNING,
    ABORT;

    companion object {
        fun fromCondition(condition: Boolean): Status = if (condition) SUCCESS else FAILURE
    }
}