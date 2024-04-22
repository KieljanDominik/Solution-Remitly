# Solution-Remitly

## How to run it 

First, you should use PowerShell or the command prompt to run the `AWS-IAM-Role-Policy.jar` file.

Ensure that the required JDK is installed in the correct version. I recommend JDK 21 or JDK 17.

Write the command as follows: 
```powershell
java -jar AWS-IAM-Role-Policy.jar [absolute path to your file] 
```
Then the program will work properly with your file.


## Structure of project

The project has 2 classes: `Main` to build the JAR file and `IAMPolicyValidator` which validates the given file by its absolute path.

The project contains a `pom.xml` file to attach dependencies to my project.

Additionally, the project includes a `test` package, which contains 2 classes responsible for testing JSON and YAML files. This package includes YAML and JSON files for testing purposes.


## Output of program

The program has three possible output options: `true`, `false`, and `File didn't pass the validation`.

If the file doesn't contain the `PolicyName` or `PolicyDocument` fields, or if there's an issue with the file's specification, the program will output `File didn't pass the validation`. Additionally, if the file is not found, the program will catch the exception and also output `File didn't pass the validation` with message of exception.

If the file has a proper structure according to the properties outlined on the website [AWS::IAM::Role Policy](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-role-policy.html), then the program will output `true` or `false`, as indicated in the docx file provided with the home exercise.

I organized the program in this way to inform the user if their file doesn't comply with the regulations of the AWS::IAM::Role Policy, and the user needs to adjust the file structure if they want to check the `Resource` field.


## Properties

Program checks the properties form website:
[AWS::IAM::Role Policy](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-role-policy.html)

## YAML

Description of Home exercise doesn't require handlig of file YAML but I noticed that option of file on website:
[AWS::IAM::Role Policy](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-role-policy.html), and I decided to develop my program to handling file of this type.

## Tests

I have written some tests to check whether the program works properly according to my expectations and the expectations of the home exercise.

The tests are divided into two classes. The first class works only with JSON files, while the second class works only with YAML files. Both classes check the same cases, such as whether the file has a Statement field, whether the Statement field has a Resource field, whether the Resource field contains one asterisk, whether the file has the PolicyName and PolicyDocument fields, whether both PolicyName and PolicyDocument are of the correct type, and whether the PolicyName meets the restrictions regarding size and legal characters.

The **YAML_FILE** contains files to test the program's functionality with YAML files, while the **JSON_FILE** contains files to test the program's functionality with JSON files.

## Maven

File maven has some dependencies like : **jackson-core**, **jackson-databind**, **jackson-dataformat-yaml**, **junit-jupiter-engine**, **junit-jupiter-api**, **junit-jupiter-params**.

These dependencies are required to good handling file and write tests to application.

File AWS-IAM-Role-Policy.jar has included these dependencies.
