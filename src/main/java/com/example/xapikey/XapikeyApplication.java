package com.example.xapikey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XapikeyApplication implements CommandLineRunner {

    @Autowired
    private ApiKeyGenerator apiKeyGenerator;

    public static void main(String[] args) {
        SpringApplication.run(XapikeyApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            String apiKey = apiKeyGenerator.generateApiKey();
            apiKeyGenerator.addApiKey(apiKey, "consumer-x","username-x", "password-x");
            System.out.println("New API Key generated and added to the configuration!");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
