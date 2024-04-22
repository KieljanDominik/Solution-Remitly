import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileJSONTest {

    IAMPolicyValidator IAMPolicyValidator = new IAMPolicyValidator();


    @Test
    @DisplayName("Tested json file which has field Statement")
    void checkStatementField() {
        assertTrue(IAMPolicyValidator.isStatementFile("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"), "File doesn't have field Statement");
    }
    @Test
    @DisplayName("Tested json file which has not field Statement")
    void checkStatementFieldFalse() {
        assertFalse(IAMPolicyValidator.isStatementFile("src/test/resources/JSON_FILE/file_without_Statement.json"), "File has field Statement");
    }
    @Test
    @DisplayName("Tested json file which has field Resource in Statement")
    void checkResourceFieldInStatement()
    {
        assertTrue(IAMPolicyValidator.isResourceFieldInStatement("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"));
    }
    @Test
    @DisplayName("Tested json file which has not field Resource in Statement")
    void checkResourceFieldInStatementFalse()
    {
        assertFalse(IAMPolicyValidator.isResourceFieldInStatement("src/test/resources/JSON_FILE/file_without_Resource.json"));
    }

    @Test
    @DisplayName("Tested json file which has field Resource in Statement and field Resource has value '*'")
    void checkResourceAsterisk()
    {
        assertFalse(IAMPolicyValidator.isResourceHaveAsterisk("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"));
    }

    @Test
    @DisplayName("Tested json file which has field Resource in Statement and field Resource has other value than '*'")
    void checkResourceAsteriskTrue()
    {
        assertTrue(IAMPolicyValidator.isResourceHaveAsterisk("src/test/resources/JSON_FILE/file_Resource_Not_Asterisk.json"));
    }
    @Test
    @DisplayName("Tested json file has fields Policy Name and Policy Document ")
    void checkPolicyNamePolicyDocument()
    {
        assertTrue(IAMPolicyValidator.isPolicyName("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"));
        assertTrue(IAMPolicyValidator.isPolicyDocument("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"));
    }
    @Test
    @DisplayName("Tested json file has not one of fields Policy Name and Policy Document")
    void checkPolicyNamePolicyDocumentFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyName("src/test/resources/JSON_FILE/file_Changed_PolicyName_PolicyDocument.json"));
        assertFalse(IAMPolicyValidator.isPolicyDocument("src/test/resources/JSON_FILE/file_Changed_PolicyName_PolicyDocument.json"));
    }
    @Test
    @DisplayName("Tested type of field Policy Name")
    void checkPolicyNameType()
    {
        assertTrue(IAMPolicyValidator.isPolicyNameCorrectType("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"));
    }

    @Test
    @DisplayName("Incorrect tested type of field Policy Name")
    void checkPolicyNameTypeFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectType("src/test/resources/JSON_FILE/file_Wrong_Type_PolicyName.json"));
    }
    @Test
    @DisplayName("Tested type of field Policy Document")
    void checkPolicyDocumentType()
    {
        assertTrue(IAMPolicyValidator.isPolicyDocumentCorrectType("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"));
    }
    @Test
    @DisplayName("Incorrect tested type of field Policy Document")
    void checkPolicyDocumentTypeFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyDocumentCorrectType("src/test/resources/JSON_FILE/file_Wrong_Type_PolicyDocument.json"));
    }
    @Test
    @DisplayName("Tested field Policy Name that passed the restriction(pattern,minimum,maximum)")
    void checkPolicyNameRestriction()
    {
        assertTrue(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/JSON_FILE/full_json_with_Policy_Name_Document.json"));
        assertTrue(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/JSON_FILE/file_Correct_String_PolicyName.json"));
    }

    @Test
    @DisplayName("Tested field Policy Name that doesn't pass the restriction(pattern,minimum,maximum)")
    void checkPolicyNameRestrictionFalse()
    {
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/JSON_FILE/file_Wrong_String_PolicyName.json"));
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/JSON_FILE/file_Wrong_String_PolicyName_TooLong.json"));
        assertFalse(IAMPolicyValidator.isPolicyNameCorrectString("src/test/resources/JSON_FILE/file_Wrong_String_PolicyName_BadCharacters.json"));
    }

}
