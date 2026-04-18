# FasterEnvios

FasterEnvios es un sistema backend para la gestión de envíos y logística, inspirado en plataformas como Servientrega o Envía. El objetivo del proyecto es simular un entorno real donde se gestionan operaciones logísticas, incluyendo creación de envíos, administración de paquetes, manejo de usuarios y control de roles.

Actualmente se encuentra en una etapa inicial de desarrollo, pero desde su concepción ha sido construido aplicando buenas prácticas, con un enfoque claro en mantenibilidad, escalabilidad y separación de responsabilidades.

---

## Descripción del problema

El sistema busca resolver la necesidad de gestionar de forma estructurada el flujo de envíos dentro de una empresa de logística. Se contemplan dos actores principales:

- Cliente: puede consultar sus envíos y estado de pedidos.
- Aliado (operador logístico): gestiona el ciclo completo del envío, incluyendo paquetes, rutas, oficinas y repartidores.

---

## Arquitectura

El proyecto está diseñado como un monolito, pero siguiendo principios de arquitectura limpia, lo que permite una evolución futura hacia microservicios si se requiere.

Se ha implementado separación por capas:

- **Domain**: contiene las entidades del negocio y la lógica central.
- **Application**: contiene los casos de uso y la orquestación de la lógica.
- **Infrastructure**: implementación de persistencia, configuraciones y acceso a servicios externos.

### Principios aplicados

- Separación de responsabilidades
- Bajo acoplamiento entre capas
- Uso de DTOs para intercambio de datos
- Mappers manuales para control explícito de transformaciones
- Uso del patrón Builder para construcción de objetos

---

## Seguridad

El sistema cuenta con autenticación basada en JWT.

Se implementa un sistema de roles que permite definir permisos específicos por endpoint, garantizando control de acceso según el tipo de usuario.

---

## Modelo de dominio

Actualmente el sistema cuenta con las siguientes entidades principales:

- CityDescription
- Employee
- Office
- Package
- PackageMovement
- PaymentMethod
- PaymentTransaction
- Person
- Role
- Shipment
- User

Estas entidades permiten modelar el flujo completo de un envío, desde su creación hasta su entrega, incluyendo pagos y trazabilidad.

---

## Funcionalidad actual

El proyecto se encuentra en desarrollo, pero actualmente incluye:

- Autenticación de usuarios mediante JWT
- Gestión de roles y permisos
- Creación de envíos
- Manejo de estados de envío
- Cálculo de costos de envío
- Integración con API externa para consulta de información de ciudades

La estructura está preparada para incorporar nuevos endpoints y ampliar la lógica del sistema.

---

## Persistencia

Se utiliza MySQL como base de datos relacional.

El acceso a datos se realiza mediante JPA/Hibernate, utilizando anotaciones para definir relaciones entre entidades y garantizar la integridad del modelo.

---

## Integraciones externas

El sistema consume una API externa de mapas para obtener información de ciudades y ubicaciones, lo que permite enriquecer los datos del envío y abrir la posibilidad de integrar geolocalización más precisa en el futuro.

La integración se realiza mediante RestTemplate.

---

## Manejo de errores

Se implementa un manejador global de excepciones (`GlobalExceptionHandler`) que permite centralizar el control de errores y mantener respuestas consistentes en la API.

---

## Estado actual del proyecto

El proyecto aún no se encuentra en producción. Algunas funcionalidades están en desarrollo o planeadas:

- Implementación completa de endpoints del dominio
- Documentación de la API con SpringDoc (Swagger)
- Implementación de pruebas unitarias e integración
- Contenerización con Docker
- Despliegue en AWS

---

## Ejecución del proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/CamiloBermeo/fasterEnvios.git
cd fasterEnvios
