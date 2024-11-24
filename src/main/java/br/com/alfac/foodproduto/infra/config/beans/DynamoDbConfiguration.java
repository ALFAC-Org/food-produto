package br.com.alfac.foodproduto.infra.config.beans;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableDynamoDBRepositories(basePackages = "br.com.alfac.foodproduto.infra.persistence")
public class DynamoDbConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DynamoDbConfiguration.class);

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

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        logger.info("AWS DynamoDB Endpoint: {}", awsDynamoEndpoint);
        logger.info("AWS Access Key: {}", accessKey);
        logger.info("AWS Secret Key: {}", secretKey);
        logger.info("AWS Session Token: {}", sessionToken);
        logger.info("AWS Region: {}", awsRegion);

        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
                accessKey, secretKey, sessionToken);

        return AmazonDynamoDBClientBuilder.defaultClient();
                // .standard()
                // // .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoEndpoint, awsRegion))
                // .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
                // .withRegion(awsRegion)
                // .build();
    }
}