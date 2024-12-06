package com.example.xapikey;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Service
public class ApiKeyGenerator {

    public String generateApiKey() {
        String apiKey = java.util.UUID.randomUUID().toString();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < apiKey.length(); i+=4) {
            builder.append(apiKey.substring(i, Math.min(apiKey.length(), i + 4)));
            if(i+4 < apiKey.length()){
                builder.append("-");
            }
        }
        return builder.toString();
    }

    public void addApiKey(String apiKey, String consumer, String username, String password) throws IOException {
        Path path = Paths.get("src/main/resources/application.yml");
        Yaml yaml = new Yaml();

        // Load the existing YAML content into a Map
        Map<String, Object> data = yaml.load(Files.newInputStream(path));

        // Navigate to the `credentials` section
        Map<String, Object> credentials = (Map<String, Object>) data.get("credentials");
        if (credentials == null) {
            credentials = new HashMap<>();
            data.put("credentials", credentials);
        }

        List<Map<String, String>> client = (List<Map<String, String>>) credentials.get("client");
        if (client == null) {
            client = new ArrayList<>();
            credentials.put("client", client);
        }

        // Add the new API key details
        Map<String, String> newCredential = new LinkedHashMap<>();
        newCredential.put("api-key", apiKey);
        newCredential.put("consumer", consumer);
        newCredential.put("username", username);
        newCredential.put("password", password);
        client.add(newCredential);

        // Write the updated content back to the YAML file
        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yamlWriter = new Yaml(options);
        try (Writer writer = Files.newBufferedWriter(path)) {
            yamlWriter.dump(data, writer);
        }

        System.out.println("API Key: " + apiKey);
        System.out.println("File update successful");
    }
}
