package nl.vintik.sample.infra

import software.amazon.awscdk.App
import software.amazon.awscdk.Environment
import software.amazon.awscdk.StackProps

fun main() {
    val app = App()

    val environment = Environment.builder()
        .account(System.getenv("DEPLOY_TARGET_ACCOUNT"))
        .region(System.getenv("DEPLOY_TARGET_REGION"))
        .build()

    val stackNameTable = "Kotlin-Lambda-SnapStart-table"
    InfrastructureTableStack(
        app, stackNameTable, StackProps.builder()
            .stackName(stackNameTable)
            .env(environment)
            .description("Dynamo Table used for SnapStart example")
            .build()
    )

    val stackNameJVMArm64 = "Kotlin-Lambda-JVM-Arm64-4compare-example"
    InfrastructureJvmArm64Stack(
        app, stackNameJVMArm64,
        StackProps.builder()
            .stackName(stackNameJVMArm64)
            .env(environment)
            .description("JVM Arm64 example")
            .build()
    )

    val stackNameJVMC1Arm64 = "Kotlin-Lambda-JVM-C1-Arm64-4compare-example"
    InfrastructureJvmC1Arm64Stack(
        app,
        stackNameJVMC1Arm64,
        StackProps.builder()
            .stackName(stackNameJVMC1Arm64)
            .env(environment)
            .description("JVM C1 Arm64 example")
            .build()
    )

    val stackNameJVMSnapStart = "Kotlin-Lambda-JVM-SnapStart-4compare-example"
    InfrastructureJvmSnapStartStack(
        app,
        stackNameJVMSnapStart,
        StackProps.builder()
            .stackName(stackNameJVMSnapStart)
            .env(environment)
            .description("JVM SnapStart example")
            .build()
    )

    val stackNameJVMC1SnapStart = "Kotlin-Lambda-JVM-C1-SnapStart-4compare-example"
    InfrastructureJvmC1SnapStartStack(
        app,
        stackNameJVMC1SnapStart,
        StackProps.builder()
            .stackName(stackNameJVMC1SnapStart)
            .env(environment)
            .description("JVM C1 SnapStart example")
            .build()
    )

    val stackNameSnapStartAndCrac = "Kotlin-Lambda-SnapStart-AndCRaC-example"
    InfrastructureJvmC1SnapStartAndCracStack(
        app,
        stackNameSnapStartAndCrac,
        StackProps.builder()
            .stackName(stackNameSnapStartAndCrac)
            .env(environment)
            .description("SnapStart C1 and CraC example")
            .build()
    )

    val stackNameSnapStartC2AndCrac = "Kotlin-Lambda-SnapStart-C2-AndCRaC-example"
    InfrastructureJvmC1SnapStartAndCracStack(
        app,
        stackNameSnapStartC2AndCrac,
        StackProps.builder()
            .stackName(stackNameSnapStartC2AndCrac)
            .env(environment)
            .description("SnapStart and CraC example")
            .build()
    )

    app.synth()
}
