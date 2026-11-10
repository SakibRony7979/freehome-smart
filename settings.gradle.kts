pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Tuya SDK-এর জন্য এই নতুন এবং সঠিক অফিশিয়াল লিংকটি যুক্ত করা হলো
        maven { url = java.net.URI("https://maven-other.tuya.com/repository/maven-releases/") }
        maven { url = java.net.URI("https://maven-sdk.tuya.com/repository/maven-releases/") }
    }
}
rootProject.name = "FreeHomeSmart"
include(":app")
