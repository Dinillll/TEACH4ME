package edu.sliit.Teach4me.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "edu.sliit.Teach4me.repository")
public class MongoConfig {
    // Additional MongoDB configurations (if needed)
}
