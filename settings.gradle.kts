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
        
        // Tuya/ThingClips SDK-এর জন্য সঠিক এবং নতুন অফিশিয়াল লাইব্রেরি ইউআরএলসমূহ
        maven { url = java.net.URI("https://tuyacn.com") }
        maven { url = java.net.URI("https://maven-other.tuya.com/repository/maven-releases/") }
    }
}
rootProject.name = "FreeHomeSmart"
include(":app")
