import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion = "1.4.1"
val coroutineVersion = "1.4.0-M1"

plugins {
    kotlin("jvm") version "1.4.10"
}


group = "com.transcodium"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven( "https://jitpack.io" )
    maven("http://tomp2p.net/dev/mvn/")
}
dependencies {
    testImplementation(kotlin("test-junit"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    implementation("net.tomp2p:tomp2p-all:5.0-Beta8")
    implementation("com.typesafe:config:1.4.0")
    implementation ("org.web3j:core:4.6.3")
    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
    implementation("org.apache.logging.log4j:log4j-core:2.13.3")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}