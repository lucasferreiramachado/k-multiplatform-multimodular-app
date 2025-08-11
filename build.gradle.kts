plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

allprojects {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/lucasferreiramachado/*") // Replace with your owner/repo
            credentials {
                username = project.findProperty("githubPackagesUsername")?.toString()
                password = project.findProperty("githubPackagesPassword")?.toString()
            }
        }
        google()
        mavenCentral()
    }
}