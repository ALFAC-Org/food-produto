#!/bin/bash

# Carrega as variáveis do arquivo .env
if [ -f .env ]; then
    export $(grep -v '^#' .env | xargs)
else
    echo "[terraform] Erro: Arquivo .env não encontrado."
    exit 1
fi

# Verifica se o método foi passado como argumento
if [ -z "$1" ]; then
    echo "[terraform] Erro: Nenhum método especificado (plan, apply, etc.)."
    exit 1
fi

METHOD=$1
shift

PARAMS="$@"

terraform $METHOD $PARAMS \
-var "environment=$ENVIRONMENT" \
-var "image_username=$DOCKERHUB_USERNAME" \
-var "app_port=$APP_PORT" \
-var "aws_region=$AWS_REGION" \
-var "node_role_arn=$ARN_AWS_LAB_ROLE" \
-var "k8s_host=$K8S_HOST" \
-var "kubernetes_namespace=$CLUSTER_NAMESPACE" \
-var "cluster_name=$CLUSTER_NAME" \
-var "food_produto_image_name=$FOOD_PRODUTO_IMAGE_NAME" \
-var "food_produto_image_version=$FOOD_PRODUTO_IMAGE_VERSION" \
-var "food_produto_service_port=$FOOD_PRODUTO_SERVICE_PORT" \
-var "food_produto_db_table_name=$FOOD_PRODUTO_DB_TABLE_NAME"
