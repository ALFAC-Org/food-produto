#!/bin/sh

# Define AWS credentials
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test

# Create S3 bucket
aws --endpoint-url=http://localhost:4566 s3api create-bucket --bucket health --region us-east-1

echo "Bucket S3 'health' criado com sucesso."

# Cria a tabela DynamoDB
aws --endpoint-url=http://localhost:4566 dynamodb create-table \
    --table-name food_produto \
    --attribute-definitions AttributeName=id,AttributeType=N \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --tags Key=Name,Value=food_produto_dynamodb \
    --region us-east-1

echo "Tabela DynamoDB criada com sucesso."

# Cria os itens na tabela DynamoDB
cd /etc/localstack/init/ready.d/

aws dynamodb batch-write-item --request-items file://batch-write-items.json --endpoint-url=http://localhost:4566 --region=us-east-1

echo "Itens criados com sucesso."