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
        
        // Tuya Smart Life SDK-এর জন্য দুটি নির্দিষ্ট অফিশিয়াল রিপোজিটরি লিংক
        maven { url = java.net.URI("https://maven-other.tuya.com/repository/maven-releases/") }
        maven { url = java.net.URI("https://tuya.com") }
        
        // কোনো কোনো ডিপেন্ডেন্সির জন্য এটি প্রয়োজন হতে পারে
        maven { url = java.net.URI("https://jitpack.io") }
    }
}
rootProject.name = "FreeHomeSmart"
include(":app")
