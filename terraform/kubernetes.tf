resource "kubernetes_secret" "secret_food" {
  metadata {
    name = "secret-food-produto"
  }

  type = "Opaque"

  data = {
    APPLICATION_DATABASE_VERSION = "latest"
    APPLICATION_PORT             = var.app_port
    FOOD_PRODUTO_IMAGE_VERSION   = var.food_produto_image_version
    FOOD_PRODUTO_PORT            = var.food_produto_service_port
    FOOD_PRODUTO_TABLE_NAME      = var.food_produto_db_table_name

    # TODO: Quando tivermos as configurações de banco, precisamos adaptar aqui
    SPRING_FOOD_PRODUTO_DATASOURCE_USERNAME = "TODO"
    SPRING_FOOD_PRODUTO_DATASOURCE_PASSWORD = "TODO"
  }

  lifecycle {
    prevent_destroy = false
  }
}

resource "kubernetes_config_map" "cm_food" {
  metadata {
    name = "cm-food-produto"
  }

  # TODO: Quando tivermos as configurações de banco, precisamos adaptar aqui
  data = {
    SPRING_FOOD_PRODUTO_DATASOURCE_URL = "TODO"
  }

  lifecycle {
    prevent_destroy = false
  }
}

resource "kubernetes_deployment" "deployment_food_produto" {
  metadata {
    name      = "deployment-food-produto"
    namespace = var.kubernetes_namespace
  }

  spec {
    selector {
      match_labels = {
        app = "deployment-food-produto"
      }
    }

    template {
      metadata {
        labels = {
          app = "deployment-food-produto"
        }
      }

      spec {
        toleration {
          key      = "key"
          operator = "Equal"
          value    = "value"
          effect   = "NoSchedule"
        }

        container {
          name  = "deployment-food-produto-container"
          image = "${var.image_username}/${var.food_produto_image_name}:${var.food_produto_image_version}"

          resources {
            requests = {
              memory : "512Mi"
              cpu : "500m"
            }
            limits = {
              memory = "1Gi"
              cpu    = "1"
            }
          }

          env_from {
            config_map_ref {
              name = kubernetes_config_map.cm_food.metadata[0].name
            }
          }

          env_from {
            secret_ref {
              name = kubernetes_secret.secret_food.metadata[0].name
            }
          }

          port {
            container_port = var.app_port
          }
        }
      }
    }
  }

  # depends_on = [aws_eks_node_group.food_node_group]
}

resource "kubernetes_service" "food_produto_service" {
  metadata {
    name      = "service-food-produto"
    namespace = var.kubernetes_namespace
    annotations = {
      "service.beta.kubernetes.io/aws-load-balancer-type" : "nlb",
      "service.beta.kubernetes.io/aws-load-balancer-scheme" : "internal",
      "service.beta.kubernetes.io/aws-load-balancer-cross-zone-load-balancing-enabled" : "true"
    }
  }
  spec {
    selector = {
      app = "deployment-food-produto"
    }
    port {
      # LoadBalancer -> :8080
      port = var.app_port
      # Porta do Container -> :8080
      target_port = var.app_port
      # Porta do Serviço -> :30003
      node_port = var.food_produto_service_port
    }
    type = "LoadBalancer"
  }
}

resource "kubernetes_ingress_v1" "food_produto_ingress" {
  metadata {
    name      = "ingress-food-produto"
    namespace = var.kubernetes_namespace
  }

  spec {
    default_backend {
      service {
        name = kubernetes_service.food_produto_service.metadata[0].name
        port {
          number = kubernetes_service.food_produto_service.spec[0].port[0].port
        }
      }
    }
  }
}

data "kubernetes_service" "food_produto_service_data" {
  metadata {
    name      = kubernetes_service.food_produto_service.metadata[0].name
    namespace = kubernetes_service.food_produto_service.metadata[0].namespace
  }
}
