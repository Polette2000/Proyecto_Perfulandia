# 🏪 Proyecto Perfulandia - Plataforma E-Commerce

Plataforma de comercio electrónico integrada por múltiples microservicios independientes. Cada servicio es responsable de un dominio específico del negocio.

## 🏗️ Arquitectura de Microservicios

```
services/
├── product/          ✅ Ruth     - Gestión de catálogo de productos
├── inventory/        ✅ Ruth     - Control de stock e inventario
├── supplier/         ⏳ Ruth     - Gestión de proveedores
├── branch/          ⏳ Polette   - Gestión de sucursales
├── user/            ⏳ Polette   - Gestión de usuarios y perfiles
├── auth/            ⏳ Polette   - Autenticación y autorización
├── cart/            ⏳ Darling   - Carrito de compras
├── order/           ⏳ Darling   - Procesamiento de órdenes
├── billing/         ⏳ Darling   - Facturación y pagos
└── shipping/        ⏳ Darling   - Envíos y logística
```

**Leyenda:**
- ✅ Completado
- ⏳ En desarrollo

## 👥 Equipo de Desarrollo

| Integrante | Servicios | Repositorio |
|-----------|-----------|-----------|
| **Ruth** | product, inventory, supplier | ruth-90 |
| **Polette** | branch, user, auth | Polette2000 |
| **Darling** | cart, order, billing, shipping | Darling_user |

## 🚀 Inicio Rápido

### Clonar el Repositorio con Submódulos
```bash
git clone --recurse-submodules https://github.com/Polette2000/Proyecto_Perfulandia.git
cd Proyecto_Perfulandia
```

Si ya clonaste sin submódulos:
```bash
git submodule update --init --recursive
```

### Actualizar Submódulos a su Última Versión
```bash
git submodule update --remote --recursive
```

### Estructura del Proyecto
Cada submódulo es un microservicio independiente con su propia:
- Código fuente (`src/`)
- Dependencias (`pom.xml`)
- Configuración (`application.properties`)
- Tests (`src/test/`)

## 🔧 Requisitos Técnicos

- **Java 21+**
- **Maven 3.8+**
- **MySQL 8.0+**
- **Git 2.20+** (para manejo de submódulos)
- **Docker** (opcional, para contenerización)

## 📦 Tecnología Stack

| Capa | Tecnología |
|------|-----------|
| Backend | Java 21, Spring Boot 4.0.6 |
| Base de Datos | MySQL 8.0 |
| Autenticación | JWT (Auth0) |
| ORM | Spring Data JPA, Hibernate |
| Migraciones BD | Flyway |
| Build | Maven |

## 📡 Comunicación Entre Servicios

Los servicios se comunican a través de **API REST** con autenticación JWT:

```
┌─────────────┐      HTTP/REST       ┌─────────────┐
│ Order       │ ────────────────────▶ │ Product     │
│ Service     │                       │ Service     │
└─────────────┘                       └─────────────┘

┌─────────────┐      JWT Token        ┌─────────────┐
│ Cart        │ ────────────────────▶ │ Auth        │
│ Service     │                       │ Service     │
└─────────────┘                       └─────────────┘
```

## 🗄️ Base de Datos

Cada servicio tiene su propia base de datos independiente:
- `perfulandia_product`
- `perfulandia_inventory`
- `perfulandia_user`
- `perfulandia_order`
- `perfulandia_billing`
- etc.

### Crear Bases de Datos
```bash
mysql -u root -p < setup/create_databases.sql
```

## 🔐 Configuración de Seguridad

### Variables de Entorno Requeridas
```bash
export DB_HOST=localhost
export DB_PORT=3306
export DB_USER=root
export DB_PASSWORD=tu_password
export JWT_SECRET=tu_clave_secreta_muy_segura
export JWT_EXPIRATION=86400000  # 24 horas
```

### JWT Configuration
Todos los servicios validan tokens JWT emitidos por `auth-service` con:
- **Issuer:** `auth-service`
- **Algorithm:** HMAC256
- **Secret:** Variable de entorno `JWT_SECRET`

## 🚀 Ejecución de Servicios

### Opción 1: Ejecutar Cada Servicio Individualmente
```bash
cd services/product
mvn spring-boot:run

# En otra terminal
cd services/inventory
mvn spring-boot:run

# ... continuar con otros servicios
```

### Opción 2: Docker Compose (Próximamente)
```bash
docker-compose up -d
```

## 📚 Documentación

Cada servicio tiene su propio README con:
- Endpoints disponibles
- Modelos de datos
- Configuración específica
- Ejemplos de uso

Ver documentación individual en cada carpeta `services/*/README.md`

## 🧪 Testing

### Tests Unitarios
```bash
cd services/product
mvn test
```

### Tests de Integración
```bash
mvn verify
```

## 🔄 Flujo de Trabajo de Desarrollo

1. **Crear rama feature**
   ```bash
   git checkout -b feature/nombre-feature
   ```

2. **Hacer cambios en tu servicio**
   ```bash
   cd services/your-service
   # Hacer cambios...
   ```

3. **Commit en tu repositorio de servicio**
   ```bash
   git add .
   git commit -m "feat: descripción del cambio"
   git push origin feature/nombre-feature
   ```

4. **Crear Pull Request** en tu repositorio de servicio

5. **Actualizar en el repo principal**
   ```bash
   cd /ruta/proyecto
   git submodule update --remote services/your-service
   git add services/your-service
   git commit -m "Update service reference"
   git push origin main
   ```

## 🚨 Troubleshooting

### Submódulos no se actualizan
```bash
git submodule update --init --recursive
git submodule update --remote --merge
```

### Error: "fatal: clone of 'https://github.com/...' failed"
Verifica que:
1. El repositorio existe
2. Tienes acceso al repositorio
3. Tu conexión a internet funciona

### Puerto en uso
Si un puerto está ocupado, cambiar en `application.properties`:
```properties
server.port=8081  # Cambiar de 8080
```

## 📞 Soporte

Para dudas o problemas:
1. Consulta la documentación del servicio específico
2. Abre un issue en el repositorio del servicio
3. Contacta con el integrante responsable del servicio

## 📝 Changelog

### v0.1.0 (17 de Mayo, 2026)
- ✅ Estructura base de proyecto
- ✅ Product Service completado
- ✅ Inventory Service completado
- ⏳ Otros servicios en desarrollo

## 📄 Licencia

Propiedad del Proyecto Perfulandia - Todos los derechos reservados

## 👨‍💻 Contribuciones

Este es un proyecto de equipo. Para contribuir:
1. Solo en tu repositorio de servicio asignado
2. Sigue las convenciones de código
3. Incluye tests para nuevas features
4. Solicita revisión de código antes de merge

---

**Última actualización:** 17 de Mayo de 2026
**Responsable:** Ruth (ruth-90)
