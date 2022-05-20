import com.diffplug.gradle.spotless.SpotlessExtension

apply(plugin = "com.diffplug.spotless")

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint(Versions.ktLint).userData(
            mapOf(
                "indent_size" to "4",
                "continuation_indent_size" to "8",
                "kotlin_imports_layout" to "ascii",
                "disabled_rules" to "no-wildcard-imports"
            )
        )

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("xml") {
        target("**/res/**/*.xml")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    kotlinGradle {
        target("**/*.gradle.kts", "*.gradle.kts")

        ktlint(Versions.ktLint).userData(
            mapOf(
                "indent_size" to "4",
                "continuation_indent_size" to "4",
                "kotlin_imports_layout" to "ascii"
            )
        )

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}