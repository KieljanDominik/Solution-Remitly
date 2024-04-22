import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IAMPolicyValidator {
    ObjectMapper objectMapper;
    public IAMPolicyValidator()
    {

    }
    private void setObjectMapper(String pathToFile)
    {
        if (pathToFile.contains(".yaml")) objectMapper = new ObjectMapper(new YAMLFactory());
        else objectMapper = new ObjectMapper();
    }

    public boolean isStatementFile(String pathToFile)
    {
        setObjectMapper(pathToFile);
        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));
            return root.get("PolicyDocument").has("Statement");
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isResourceFieldInStatement(String pathToFile)
    {
        setObjectMapper(pathToFile);

        if (!isStatementFile(pathToFile)) return false;

        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));

            if (!root.get("PolicyDocument").has("Statement") || root.get("PolicyDocument").get("Statement").isEmpty()) {
                return false;
            }
            for (JsonNode statement : root.get("PolicyDocument").get("Statement")) {
                if (!statement.has("Resource")) {
                    return false;
                }
            }
            return true;

        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isResourceHaveAsterisk(String pathToFile)
    {
        setObjectMapper(pathToFile);
        if (!isResourceFieldInStatement(pathToFile)) return false;
        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));

            JsonNode resources = root.get("PolicyDocument").get("Statement").get(0).get("Resource");

            return !resources.isTextual() || !resources.textValue().equals("*");
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isPolicyName(String pathToFile)
    {
        setObjectMapper(pathToFile);
        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));
            return root.has("PolicyName");
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;

    }

    public boolean isPolicyDocument(String pathToFile)
    {
        setObjectMapper(pathToFile);
        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));
            return root.has("PolicyDocument");
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;

    }

    public boolean isPolicyNameCorrectType(String pathToFile)
    {
        setObjectMapper(pathToFile);
        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));
            return root.get("PolicyName").isTextual();
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean isPolicyDocumentCorrectType(String pathToFile)
    {
        setObjectMapper(pathToFile);
        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));
            return root.get("PolicyDocument").isObject();
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    private static boolean validateString(String input)
    {
        if (input.isEmpty() || input.length() > 128) return false;
        Pattern pattern = Pattern.compile("[\\w+=,.@-]+");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public boolean isPolicyNameCorrectString(String pathToFile)
    {
        setObjectMapper(pathToFile);
        try {
            JsonNode root = objectMapper.readTree(new File(pathToFile));
            String tmp = root.get("PolicyName").textValue();
            if (tmp == null) return false;
            if (IAMPolicyValidator.validateString(tmp)) return true;
            return false;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean checkValidationOfFile(String pathToFile)
    {
        if(!isPolicyName(pathToFile)) return false;
        else {
            if (!isPolicyNameCorrectType(pathToFile)) return false;
            if (!isPolicyNameCorrectString(pathToFile))
                return false;
            if (!isPolicyDocument(pathToFile)) return false;
            else if (!isPolicyDocumentCorrectType(pathToFile))
                return false;
            if (!isStatementFile(pathToFile)) return false;
            else if (!isResourceFieldInStatement(pathToFile))
                return false;
        }
        return true;
    }
    public boolean checkFieldResource(String pathToFile)
    {
        if(!isResourceHaveAsterisk(pathToFile)) return false;
         return true;
    }
}
