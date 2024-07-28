package com.tpcly.behaviourtree

import com.tpcly.behaviourtree.node.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.junit.jupiter.api.Test

class TreeExecutorTests {

    @Test
    fun testSequencer() {
        val handlers = TreeNodeHandlerCollection(
            mapOf(
                "test" to TestAction.Handler()
            )
        )

        val json = Json {
            prettyPrint = true
            serializersModule = SerializersModule {
                polymorphic(TreeNode::class) {
                    subclass(Sequencer::class)
                    subclass(Selector::class)
                    subclass(Succeeder::class)
                    subclass(TestAction::class)
                }
            }
        }

        val tree= json.decodeFromString<TreeNode>(
            """
            {
                "type": "com.tpcly.behaviourtree.node.Sequencer",
                "executionOrder": "IN_ORDER",
                "children": [
                    {
                        "type": "com.tpcly.behaviourtree.node.Succeeder",
                        "child": {
                            "type": "com.tpcly.behaviourtree.TestAction",
                            "inputOne": "test_1",
                            "inputTwo": 1337
                        }
                    },
                    {
                        "type": "com.tpcly.behaviourtree.TestAction",
                        "inputOne": "test_2",
                        "inputTwo": 69
                    },
                    {
                        "type": "com.tpcly.behaviourtree.TestAction",
                        "inputOne": "test_3",
                        "inputTwo": 420
                    }
                ]
            }
        """
        )

        println(tree)

//        val tree = Sequencer(
//            TreeExecutionOrder.IN_ORDER,
//            listOf(
//                Succeeder(
//                    Action("test", "")
//                ),
//                Action("test", ""),
//                Action("test", "")
//            )
//        )


        val rootHandler = handlers.get(tree.name)
        rootHandler.execute(handlers, tree)
    }
}