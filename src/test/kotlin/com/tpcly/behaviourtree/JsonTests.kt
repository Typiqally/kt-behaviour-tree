package com.tpcly.behaviourtree

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.junit.jupiter.api.Test

class JsonTests {
    @Test
    fun testJson() {
        val tree = sequencer {
            +succeeder {
                MockAction("test_1", 1337)
            }
            +repeatUntil {
                MockAction("test_2", 69)
            }
            +MockAction("test_2", 69)
            +MockAction("test_3", 420)
        }

        val json = Json {
            prettyPrint = true
            serializersModule = SerializersModule {
                polymorphic(TreeNode::class) {
                    subclass(MockAction::class)
                    subclass(RepeatUntil::class)
                    subclass(Succeeder::class)
                }
            }
        }

        val serialized = json.encodeToString(tree)
        println(serialized)
    }
}