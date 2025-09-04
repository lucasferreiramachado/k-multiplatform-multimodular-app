rootProject.name = "k-multiplatform-app"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

//include(":kapp-product:kapp-product-core")
//include(":kapp-home:kapp-home-core")
//include(":kapp-auth:kapp-auth-core")

//include(":kapp-deeplink:kapp-deeplink-core")

//include(":kapp-data:kapp-data-user")
//include(":kapp-data:kapp-data-product")
//include(":kapp-data:kapp-data-purchase")

include(":kapp:composeApp")