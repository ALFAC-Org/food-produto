package br.com.alfac.foodproduto.infra.config.beans;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.core.env.Environment;

@Configuration
@EnableDynamoDBRepositories(basePackages = "br.com.alfac.foodproduto.infra.persistence")
public class DynamoDbConfiguration {

    @Value("${aws.dynamodb.endpoint}")
    private String awsDynamoEndpoint;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.sessionToken}")
    private String sessionToken;

    @Value("${aws.region}")
    private String awsRegion;

    @Autowired
    private Environment environment;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        String[] activeProfiles = environment.getActiveProfiles();

        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
                accessKey, secretKey, sessionToken);

        if(activeProfiles[0].equals("test")) {
            return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
                .build();
        }

        return AmazonDynamoDBClientBuilder.defaultClient();
    }
}