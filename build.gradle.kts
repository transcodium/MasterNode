import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion = "1.4.10"
val coroutineVersion = "1.4.0-M1"

plugins {
    kotlin("jvm") version "1.4.10"
}


group = "com.transcodium"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven( "https://jitpack.io" )
}
dependencies {
    testImplementation(kotlin("test-junit"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    implementation("com.github.tomp2p:TomP2P:tomp2p-parent-5.0-Beta8.1")
    implementation("com.typesafe:config:1.4.0")
    implementation ("org.web3j:core:4.6.3")
    implementation("com.github.ipfs:java-ipfs-http-client:v1.3.3")
    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
    implementation("org.apache.logging.log4j:log4j-core:2.13.3")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}