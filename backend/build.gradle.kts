import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    idea
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    kotlin("plugin.allopen") version "1.9.21"
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

group = "com.sdt"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        exclude(group = "ch.qos.logback", module = "logback-classic")
//        exclude(module = "junit")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("jakarta.validation:jakarta.validation-api")

    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.jupiter:junit-vintage-engine")
        exclude(module = "mockito-core")
        exclude(module = "junit")
        exclude(module = "mockito-junit-jupiter")
    }
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("com.ninja-squad:springmockk:4.0.2") {
        exclude("org.jetbrains:annotations")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-params")

    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mongodb")
}

tasks.getByName<BootRun>("bootRun") {
    val environment = System.getenv("ENV") ?: "local"
    val appConfig = "$projectDir/tmp/config"

    val logsDir = "$projectDir/tmp/logs"
    val logsFolder = File(logsDir)
    if (!logsFolder.exists()) {
        logsFolder.mkdirs()
    }

    val appConfigFolder = File(appConfig)
    if (!appConfigFolder.exists()) {
        appConfigFolder.mkdirs()
    }

    jvmArgs = listOf(
        "-Denvironment=$environment",
        "-Dlogging.config=classpath:log4j2-spring.xml",
        "-DlogFilePath=$logsDir",
        "-Dapp.config=$appConfig",
    )
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    failFast = true
    testLogging {
        showCauses = true
        showExceptions = true
        showStackTraces = true
        events("passed", "skipped", "failed")
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true
    }
}
