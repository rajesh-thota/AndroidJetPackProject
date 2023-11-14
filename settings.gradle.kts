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
    }
}

rootProject.name = "NYCSchool"
include(":app")
include(":shared")
include(":shared:network")
include(":shared:database")
include(":shared:data")
include(":shared:security")
include(":shared:mocks")
