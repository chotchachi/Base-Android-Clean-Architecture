import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

allprojects {
    apply(plugin = "com.github.ben-manes.versions")

    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = freeCompilerArgs + listOf("-Xopt-in=kotlin.RequiresOptIn")
        }
    }

    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin" && requested.name == "kotlin-reflect") {
                useVersion(Versions.kotlin.core)
            }
        }
    }
}

subprojects {
    apply(from = "${rootProject.rootDir}/spotless.gradle.kts")
}

tasks.register("clean", Delete::class) { delete(rootProject.buildDir) }