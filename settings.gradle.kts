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
        
        // Tuya-এর অফিশিয়াল রিলিজ এবং কমার্শিয়াল রিপোজিটরি লিংক
        maven { url = uri("https://maven-other.tuya.com/repository/maven-releases/") }
        maven { url = uri("https://maven-other.tuya.com/repository/maven-commercial-releases/") }
        
        // অতিরিক্ত থার্ড-পার্টি প্লাগইনের জন্য
        maven { url = uri("https://jitpack.io") }
    }
}
rootProject.name = "FreeHomeSmart"
include(":app")
