pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven { url = uri("https://www.jitpack.io" ) }
        mavenCentral()
    }
}

rootProject.name = "ExchangeRate"
include(":ExchangeRates")
include(":core")
include(":network")
include(":fixer-data")
include(":fixer-domain")
