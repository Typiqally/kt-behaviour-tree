plugins {
    kotlin("jvm") version "1.8.10"
}

group = "com.tpcly"
version = "1.1.1"

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
