plugins {
    alias(libs.plugins.java)
}

subprojects {
    apply(plugin = rootProject.libs.plugins.java.get().pluginId)

    group = "it.unibo.pps"

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(rootProject.libs.versions.jdk.get())
        }
    }

    dependencies {
        testImplementation(platform(rootProject.libs.junit.bom))
        testImplementation(rootProject.libs.junit.jupiter)
        testRuntimeOnly(rootProject.libs.junit.launcher)
    }

    tasks.test {
        useJUnitPlatform()
        javaLauncher = javaToolchains.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(rootProject.libs.versions.jdk.get()))
        }
    }
}
