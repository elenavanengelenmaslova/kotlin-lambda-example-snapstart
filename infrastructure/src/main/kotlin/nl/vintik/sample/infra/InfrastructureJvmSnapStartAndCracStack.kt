package nl.vintik.sample.infra

import software.amazon.awscdk.*
import software.amazon.awscdk.services.dynamodb.Table
import software.amazon.awscdk.services.lambda.*
import software.amazon.awscdk.services.lambda.Function
import software.amazon.awscdk.services.logs.RetentionDays
import software.constructs.Construct

class InfrastructureJvmSnapStartAndCracStack(scope: Construct, id: String, props: StackProps) :
    Stack(scope, id, props) {
    init {
        val productsTable =
            Table.fromTableArn(this, "dynamoTable", Fn.importValue("Products-SnapStart-ExampleTableArn"))
        val functionId = "lambdaJvmSnapStartAndCrac"
        val function = Function.Builder.create(this, functionId)
            .description("Kotlin Lambda JVM SnapStart And CRaC Example")
            .handler("nl.vintik.sample.KotlinLambda::handleRequest")
            .runtime(Runtime.JAVA_11)
            .code(Code.fromAsset("../build/dist/function-on-crac.zip"))
            .environment(
                mapOf(
                    // Ensure lambda version is updated with latest lambda code
                    "CodeVersionString" to System.getenv("BUILD_NO"),
                )
            )
            .logRetention(RetentionDays.ONE_WEEK)
            .memorySize(512)
            .timeout(Duration.seconds(120))
            .build()

        //enable SnapStart
        (function.node.defaultChild as CfnFunction).setSnapStart(
            CfnFunction.SnapStartProperty.builder().applyOn("PublishedVersions").build()
        )
        // publish a version
        function.currentVersion

        productsTable.grantReadData(function)

        CfnOutput(
            this, "${functionId}-fn-arn",
            CfnOutputProps.builder()
                .value(function.functionArn)
                .description("The arn of the $functionId function")
                .exportName("${functionId}FnArn").build()
        )
    }
}