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
                TestAction("test_1", 1337)
            }
            +repeatUntil {
                TestAction("test_2", 69)
            }
            +TestAction("test_2", 69)
            +TestAction("test_3", 420)
        }

        val json = Json {
            prettyPrint = true
            serializersModule = SerializersModule {
                polymorphic(TreeNode::class) {
                    subclass(TestAction::class)
                    subclass(RepeatUntil::class)
                    subclass(Succeeder::class)
                }
            }
        }

        val serialized = json.encodeToString(tree)
        println(serialized)
    }
}