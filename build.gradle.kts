plugins {
    application
    kotlin("jvm") version "1.4.21"
    id("com.justai.jaicf.jaicp-build-plugin") version "0.1.1"
}

group = "com.justai.jaicf"
version = "1.0.0"

val jaicf = "1.1.1"
val logback = "1.2.3"
val junit = "5.6.0"

// Main class to run application on heroku. Either JaicpPollerKt, or JaicpServerKt. Will propagate to .jar main class.
application {
    mainClassName = "com.justai.jaicf.template.connections.JaicpServerKt"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("ch.qos.logback:logback-classic:$logback")

    implementation("com.just-ai.jaicf:core:$jaicf")
    implementation("com.just-ai.jaicf:jaicp:$jaicf")
    implementation("com.just-ai.jaicf:caila:$jaicf")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    test {
        useJUnitPlatform()
    }
}

tasks.create("stage") {
    dependsOn("shadowJar")
}

tasks.withType<com.justai.jaicf.plugins.jaicp.build.JaicpBuild> {
    mainClassName.set(application.mainClassName)
}
