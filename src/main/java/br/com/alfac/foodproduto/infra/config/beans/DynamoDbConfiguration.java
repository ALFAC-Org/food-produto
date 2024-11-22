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

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.dynamodb.endpoint}")
    private String awsDynamoEndpoint;

    @Value("${aws.region}")
    private String awsRegion;

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
                awsDynamoEndpoint,
                awsRegion
        );
    }

    private AWSStaticCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey)
        );
    }

    // TODO: Segundo o GPT, o código abaixo é um exemplo de como acessar o DynamoDB com as credenciais configuradas acima.
    // public class DynamoDBExample {
    //     public static void main(String[] args) {
    //         // Configurar as credenciais da AWS
    //         BasicAWSCredentials awsCreds = new BasicAWSCredentials("your_access_key_id", "your_secret_access_key");

    //         // Configurar o cliente do DynamoDB
    //         AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
    //                 .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
    //                 .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://dynamodb.us-east-1.amazonaws.com", "us-east-1"))
    //                 .build();

    //         // Criar uma instância do DynamoDB
    //         DynamoDB dynamoDB = new DynamoDB(client);

    //         // Obter a tabela
    //         Table table = dynamoDB.getTable("food_produto");

    //         // Exemplo de operação: obter um item da tabela
    //         // (Substitua "your_primary_key" e "your_primary_key_value" pelos valores reais)
    //         Item item = table.getItem("your_primary_key", "your_primary_key_value");
    //         System.out.println(item.toJSONPretty());
    //     }
    // }
}