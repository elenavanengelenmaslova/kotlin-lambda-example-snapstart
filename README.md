# kotlin-lambda-example-snapstart

Kotlin Lambda SnapStart example contains multiple setups of the same Lambda implementation in order to compare performance effect SnapStart has on Kotlin/JVM AWS Lambda. The results are published in the following blog: [SnapStart your Kotlin AWS Lambda with CRaC](https://medium.com/@elenavanengelen/snapstart-your-kotlin-aws-lambda-with-crac-e253c8a376cb). 

## Infrastructure
Kotlin Lambda SnapStart Example contains 7 CDK stacks: stack for DynamoDB table resource, and 6 stacks containing AWS Lambda resource variants - 
- JVM ARM64 
- JVM ARM64 with C1 compiler optimisation
- JVM SnapStart with C1 compiler optimisation
- JVM SnapStart with priming using CRaC hooks
- JVM SnapStart with C1 compiler optimisation and priming using CRaC hooks.

## Build & Deployment from local machine
### Build kotlin app
Unit tests are using Testcontainers to run DynamoDB locally.
Ensure docker is running locally, then execute:
```
./gradlew clean build
```
### Set up CDK deployment

Install CDK (if you have not already):
```
npm install -g aws-cdk
```

If you have not set up CDK in you AWS account yet, please run (replace variables in brackets with actual values):
```
cdk bootstrap aws://[aws_account_id]/[aws_region]
```

Now deploy all stacks:
```
cdk deploy -vv --require-approval never --all
```

## Build & Deployment to AWS account from GitHub
Set up the following secrets in your GitHub project:
```
AWS_ACCOUNT_ID
AWS_ACCESS_KEY
AWS_SECRET_KEY
```
Update AWS region in `workflow-build-deploy.yml` in `.github` folder of the project

