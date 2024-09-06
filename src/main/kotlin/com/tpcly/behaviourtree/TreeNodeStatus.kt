package com.tpcly.behaviourtree

enum class TreeNodeStatus {
    SUCCESS,
    FAILURE,
    ABORT;

    companion object {
        fun fromBoolean(value: Boolean): TreeNodeStatus = if (value) SUCCESS else FAILURE
    }
}