import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileYAMLTest {
    IAMPolicyValidator IAMPolicyValidator = new IAMPolicyValidator();

    @Test
    @DisplayName("Tested yaml file which has filed Statement")
    void checkStatementFieldYAML()
    {
        assertTrue(IAMPolicyValidator.isStatementFile("src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"), "File doesn't have field Statement");
    }
    @Test
    @DisplayName("Tested json file which has not field Statement")
    void checkStatementFieldFalse() {
        assertFalse(IAMPolicyValidator.isStatementFile("src/test/resources/YAML_FILE/file_without_Statement.yaml"), "File has field Statement");
    }
    @Test
    @DisplayName("Tested json file which has field Resource in Statement")
    void checkResourceFieldInStatement()
    {
        assertTrue(IAMPolicyValidator.isResourceFieldInStatement("src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"));
    }
    @Test
    @DisplayName("Tested json file which has not field Resource in Statement")
    void checkResourceFieldInStatementFalse()
    {
        assertFalse(IAMPolicyValidator.isResourceFieldInStatement("src/test/resources/YAML_FILE/file_without_Resource.yaml"));
    }
    @Test
    @DisplayName("Tested json file which has field Resource in Statement and field Resource has value '*'")
    void checkResourceAsterisk()
    {
        assertFalse(IAMPolicyValidator.isResourceHaveAsterisk("src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"));
    }
    @Test
    @DisplayName("Tested json file which has field Resource in Statement and field Resource has other value than '*'")
    void checkResourceAsteriskTrue()
    {
        assertTrue(IAMPolicyValidator.isResourceHaveAsterisk("src/test/resources/YAML_FILE/file_Resource_Not_Asterisk.yaml"));
    }
    @Test
    @DisplayName("Tested json file has fields Policy Name and Policy Document ")
    void checkPolicyNamePolicyDocument()
    {
        assertTrue(IAMPolicyValidator.isPolicyName("src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"));
        assertTrue(IAMPolicyValidator.isPolicyDocument("src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"));
    }
    @Test
    @DisplayName("Tested json file has not one of fields Policy Name and Policy Document")
    void checkPolicyNamePolicyDocumentFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyName("src/test/resources/YAML_FILE/file_Changed_PolicyName_PolicyDocument.yaml"));
        assertFalse(IAMPolicyValidator.isPolicyDocument("src/test/resources/YAML_FILE/file_Changed_PolicyName_PolicyDocument.yaml"));
    }
    @Test
    @DisplayName("Tested type of field Policy Name")
    void checkPolicyNameType()
    {
        assertTrue(IAMPolicyValidator.isPolicyNameCorrectType("src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"));
    }
    @Test
    @DisplayName("Incorrect tested type of field Policy Name")
    void checkPolicyNameTypeFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectType("src/test/resources/YAML_FILE/file_Wrong_Type_PolicyName.yaml"));
    }
    @Test
    @DisplayName("Tested type of field Policy Document")
    void checkPolicyDocumentType()
    {
        assertTrue(IAMPolicyValidator.isPolicyDocumentCorrectType("C:/Users/domin//Pulpit/Remitly/AWS-IAM-Role-Policy/src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"));
    }
    @Test
    @DisplayName("Incorrect tested type of field Policy Document")
    void checkPolicyDocumentTypeFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyDocumentCorrectType("src/test/resources/YAML_FILE/file_Wrong_Type_PolicyDocument.yaml"));
    }
    @Test
    @DisplayName("Tested field Policy Name that passed the restriction(pattern,minimum,maximum)")
    void checkPolicyNameRestriction()
    {
        assertTrue(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/YAML_FILE/full_yaml_with_Policy_Name_Document.yaml"));
        assertTrue(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/YAML_FILE/file_Correct_String_PolicyName.yaml"));
    }

    @Test
    @DisplayName("Tested field Policy Name that doesn't pass the restriction(pattern,minimum,maximum)")
    void checkPolicyNameRestrictionFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/YAML_FILE/file_Wrong_String_PolicyName.yaml"));
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/YAML_FILE/file_Wrong_String_PolicyName_TooLong.yaml"));
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/YAML_FILE/file_Wrong_String_PolicyName_BadCharacters.yaml"));
    }
}
