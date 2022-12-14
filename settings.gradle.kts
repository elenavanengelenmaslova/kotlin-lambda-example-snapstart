/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/7.5.1/userguide/multi_project_builds.html
 */

rootProject.name = "kotlin-lambda-example-snapstart"
include(":products")
project(":products").projectDir = file("software/products")
include(":products-on-crac")
project(":products-on-crac").projectDir = file("software/products-on-crac")
include(":infrastructure")
project(":infrastructure").projectDir = file("infrastructure")
