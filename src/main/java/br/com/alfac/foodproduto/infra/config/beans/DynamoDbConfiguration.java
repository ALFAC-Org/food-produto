package br.com.alfac.foodproduto.infra.config.beans;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "br.com.alfac.foodproduto.infra.persistence")
public class DynamoDbConfiguration {

    @Value("${aws.secretKey}")
    private String awsAccessKey;

    @Value("${aws.accessKeyId}")
    private String awsAccessKeyId;

    @Value("${aws.dynamodb.endpoint}")
    private String awsDynamoEndpoint;
    
    @Value("${aws.region}")
    private String awsRegion;

    // @Bean
    // public DynamoDBMapper dynamoDBMapper() {
    //     return new DynamoDBMapper(amazonDynamoDB());
    // }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration())
                .withCredentials(credentialsProvider())
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(
                "http://localhost:4566", // Porta padrão do LocalStack - Pegar das configs
                "us-east-1" // Região padrão - Pegar das configs/properties
        );
    }

    private AWSStaticCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        "test", // Chave de acesso fake para LocalStack - Pegar das vars de ambiente
                        "test"  // Chave secreta fake para LocalStack - Pegar das vars de ambiente
                )
        );
    }
}