plugins {
    kotlin("jvm") version "1.9.0"
}

group = "com.tpcly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.7")
}

tasks.test {
    useJUnitPlatform()
}
