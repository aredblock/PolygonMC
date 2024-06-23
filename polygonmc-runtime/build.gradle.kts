import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    application
    alias(libs.plugins.shadow)
}

group = "de.aredblock"
version = "dev"

repositories {
    mavenCentral()
}

dependencies {
    implementation(rootProject)

    runtimeOnly(libs.bundles.logback)
}

tasks {
    application {
        mainClass.set("de.aredblock.polygonmc.runtime.Bootstrap")
    }

    withType<ShadowJar> {
        archiveFileName.set("polygon-1.21.jar")
    }
}

tasks.test {
    useJUnitPlatform()
}