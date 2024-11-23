# Application configuration
variable "environment" {
  description = "The environment of the application"
  type        = string
  default     = "development"
}

variable "app_port" {
  description = "The port where the application will be listening"
  type        = string
  default     = "8080"
}

variable "image_username" {
  description = "The username of the image to deploy"
  type        = string
  default     = "carlohcs"
}

# Kubernetes configuration
variable "cluster_name" {
  description = "Name of the EKS Cluster"
  type        = string
  default     = "food-cluster"
}

variable "kubernetes_namespace" {
  description = "The Kubernetes namespace where the resources will be provisioned"
  type        = string
  default     = "default"
}

variable "k8s_host" {
  description = "The Kubernetes host"
  default     = ""
}

# AWS provider configuration
variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "node_role_arn" {
  description = "ARN of the IAM Role that will be associated with the Node Group"
  type        = string
  sensitive   = true
}

variable "food_produto_service_port" {
  description = "The port where the application will be listening"
  type        = string
  default     = "30003"
}

variable "food_produto_image_name" {
  description = "The name of the image to deploy"
  type        = string
  default     = "food-produto"
}

variable "food_produto_image_version" {
  description = "The version of the image to deploy"
  type        = string
  default     = "latest"
}

variable "food_produto_db_endpoint" {
  description = "The endpoint of the database"
  type        = string
  default     = "http://food-produto-db:8080" // fake value
}

variable "food_produto_db_table_name" {
  description = "Name of the database"
   type        = string
  default     = "food_produto"
}

variable "aws_secret_access_key" {
  description = "AWS Secret Access Key"
  type        = string
}

variable "aws_access_key_id" {
  description = "AWS Access Key ID"
  type        = string
}

variable "aws_session_token" {
  description = "AWS Session Token"
  type        = string
}