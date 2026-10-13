plugins {
    java
}

group = "com.solidgate"
version = "1.0.0"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.get())
    }
}

dependencies {
    implementation(libs.selenide)
    implementation(libs.rest.assured)
    implementation(platform(libs.jackson.bom))
    implementation(libs.jackson.databind)
    implementation(libs.jackson.annotations)
    implementation(libs.awaitility)
    implementation(libs.dotenv)
    runtimeOnly(libs.slf4j.simple)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.platform.suite)
    testImplementation(libs.assertj)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.test {
    useJUnitPlatform()
    include("**/runners/**")
    outputs.upToDateWhen { false }
    jvmArgs("--sun-misc-unsafe-memory-access=allow")
    System.getProperties()
        .stringPropertyNames()
        .filter { it.startsWith("SOLIDGATE_") }
        .forEach { systemProperty(it, System.getProperty(it)) }
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showStandardStreams = false
    }
}
