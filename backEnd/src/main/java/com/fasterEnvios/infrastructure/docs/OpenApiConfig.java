package com.fasterEnvios.infrastructure.docs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        OpenAPI openAPI = new OpenAPI().info(apiInfo());
        openAPI.addServersItem(productionServer());
        openAPI.addServersItem(devServer());
        openAPI.components(new Components()
                .addSecuritySchemes("bearer-key", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
        return openAPI;
    }

    private Server productionServer(){
        return new Server().url("https://faster-envios.duckdns.org");
    }
    private Server devServer(){
        return new Server().url("http://localhost:8080");
    }
    private Info apiInfo(){
        return new Info()
                .title("Faster Envios")
                .version("1.0.1")
                .description("""
                        # Faster Envíos API
                        ### *Solución integral para la gestión y optimización logística*
                        
                        **Faster Envíos** es una plataforma robusta diseñada para centralizar y optimizar el ciclo de vida de los envíos de mercancía. Esta API facilita la interconexión estratégica entre **remitentes**, **destinatarios** y **aliados logísticos**, garantizando trazabilidad total desde la admisión del paquete hasta la entrega en la última milla.
                        
                        ---
                        
                        ## Capacidades Principales
                        
                        *   **Gestión de Envíos Inteligente**: Registro detallado de paquetes, incluyendo dimensiones, peso y valor declarado para asegurar un manejo de carga preciso y eficiente.
                        *   **Control de Destinos y Rutas**: Validación de infraestructura basada en ciudades y direcciones parametrizadas para minimizar errores de entrega.
                        *   **Transparencia Financiera**: Administración centralizada de transacciones con soporte para múltiples métodos de recaudo (Efectivo, Contraentrega, Digital), asegurando la trazabilidad del flujo de caja.
                        *   **Seguridad Basada en Roles (RBAC)**: Ecosistema protegido con **Spring Security y JWT**, donde Administradores, Aliados y Clientes acceden exclusivamente a la información de su competencia operativa.
                        *   **Monitoreo de Estados**: Control en tiempo real de la evolución del servicio (Recibido, En Tránsito, Entregado, Cancelado).
                        
                        ##  Especificaciones Técnicas
                        
                        *   **Lenguaje & Framework**: Java 21 con Spring Boot 3.
                        *   **Seguridad**: Autenticación Stateless mediante Bearer Tokens (JWT).
                        *   **Base de Datos**: MySQL para persistencia relacional.
                        *   **Arquitectura**: Diseño basado en capas con enfoque en Clean Architecture y principios SOLID.
                        
                        ---
                        
                        > ** Nota para Desarrolladores:**\s
                        > Para interactuar con los endpoints de escritura, asegúrese de que su usuario cuente con los permisos de `ALIADO` o `ADMIN` y que el token de acceso esté incluido en la cabecera de autorización (`Authorization: Bearer <token>`).
                        """);
    }
}
