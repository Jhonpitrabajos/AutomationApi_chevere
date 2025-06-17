# 🚀 API Testing Framework - Sistema de Pruebas Integradas

## 📋 Descripción del Proyecto

Framework de testing automatizado desarrollado en Java con Maven, Cucumber y RestAssured para realizar pruebas de integración de sistemas que interactúan con múltiples servicios. El proyecto implementa un patrón de arquitectura en capas con separación de responsabilidades entre servicios de autenticación y gestión de usuarios.

## 🎯 Tests Implementados

### 🔥 System Integration Tests (6 Tests Principales)

**Ubicación**: `src/test/resources/features/system/system_integration_tests.feature`

| # | Test Name | HTTP Methods | Servicios | Descripción |
|---|-----------|--------------|-----------|-------------|
| 1 | **User Registration and Profile Creation Flow** | `POST /api/register`, `POST /api/users` | Auth + User | Flujo completo de registro → almacenamiento → creación de perfil |
| 2 | **Authentication Token Management** | `POST /api/register`, Token Encryption | Auth + Encryption | Gestión de tokens con encriptación y validación |
| 3 | **Multi-Service Data Consistency** | Data Operations | Auth + User Data | Validación de consistencia de datos entre servicios |
| 4 | **Error Handling Across Services** | `POST /api/login`, `POST /api/users` | Auth + User | Manejo graceful de errores en múltiples servicios |
| 5 | **Data Persistence and Service Integration** | Complete Workflow | Auth + User + Data | Persistencia de datos en workflow completo |
| 6 | **Service Layer Separation** | Facade Pattern | All Services | Separación arquitectónica y seguridad de tokens |

### 🧪 Tests de Desarrollo y Exploración

**Ubicación**: `src/test/java/example/`

| Test Class | HTTP Methods | API | Propósito |
|------------|--------------|-----|-----------|
| `SimpleApiTest` | `GET /api/users` | reqres.in | Validar API key y conectividad |
| `DummyJsonTests` | `GET /carts/1`, `GET /pokemon-species/1` | dummyjson.com, pokeapi.co | Pruebas exploratorias durante desarrollo |
| `APIObject` | `POST /objects`, `GET /objects/{id}` | restful-api.dev | Test de creación y recuperación de objetos |

## 🚀 Cómo Ejecutar los Tests

### ✅ Ejecutar Solo los 6 System Tests (Recomendado para Demostración)

\`\`\`bash
mvn test -Dtest=CleanTestRunner
\`\`\`

**Resultado esperado:**
\`\`\`
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
✅ BUILD SUCCESS
\`\`\`

### 🔍 Ejecutar Tests Específicos

\`\`\`bash
# Solo system integration tests
mvn test -Dtest=SystemIntegrationTestRunner

# Todos los tests (incluye tests de desarrollo)
mvn test
\`\`\`

## 🛠️ Requisitos de Instalación

### 💻 En el PC

1. **Java 21** (JDK)
   \`\`\`bash
   java -version
   # Debe mostrar: openjdk version "21.x.x"
   \`\`\`

2. **Apache Maven 3.9+**
   \`\`\`bash
   mvn -version
   # Debe mostrar: Apache Maven 3.9.x
   \`\`\`

3. **Git** (para clonar el proyecto)

### 🎨 En Visual Studio Code

**Extensiones Requeridas:**
- Extension Pack for Java (Microsoft)
- Cucumber (Gherkin) Full Support
- Maven for Java
- Test Runner for Java

**Extensiones Recomendadas:**
- GitLens
- Bracket Pair Colorizer
- Auto Rename Tag

## 🏗️ Arquitectura del Proyecto

\`\`\`
src/
├── main/java/com/api_testing/
│   ├── api/                    # Interfaces y implementaciones de API
│   ├── config/                 # Configuración RestAssured
│   ├── constants/              # Claves estandarizadas
│   ├── context/                # Contexto de pruebas
│   ├── data/                   # Servicios y repositorios de datos
│   ├── facades/                # Patrón Facade para servicios
│   ├── handlers/               # Manejadores de lógica de negocio
│   ├── models/                 # Modelos de datos (POJOs)
│   ├── security/               # Encriptación de tokens
│   └── utils/                  # Utilidades (JSON, Config, Faker)
└── test/
    ├── java/
    │   ├── steps/              # Step definitions de Cucumber
    │   ├── example/            # Tests de desarrollo
    │   └── *TestRunner.java    # Runners de test suites
    └── resources/
        └── features/           # Archivos .feature de Cucumber
\`\`\`

## 🤖 Contribución de IA en el Desarrollo

Durante el desarrollo de este proyecto, utilicé asistencia de IA para resolver problemas específicos y acelerar el desarrollo. A continuación, algunos ejemplos de cómo la IA contribuyó:

### 1. 🔧 Configuración de Maven y Variables de Ambiente

**Problema**: Errores de configuración inicial de Maven y variables de ambiente en Windows

**Prompt usado**: *"Chat, necesito ayuda configurando Maven en mi PC. Tengo errores de JAVA_HOME y Maven no reconoce las dependencias"*

**Contribución de IA**: 
- Configuración correcta de variables de ambiente JAVA_HOME y MAVEN_HOME
- Resolución de conflictos de PATH en Windows

### 2. 🧪 Mejorar Legibilidad de Salida de Tests

**Problema**: No lograba distinguir entre los diferentes tests en la consola, todo se veía confuso

**Prompt usado**: *"Chat, no logro distinguir entre los test cases que hicimos. La salida de consola es muy confusa y no sé qué test está corriendo"*

**Contribución de IA**:

- Implementación de hooks en `CucumberHooks.java` para separar visualmente los tests
- Formato de consola con separadores (`=====` para inicio, `-----` para final)
- Logging personalizado en `RestAssuredClient` para requests/responses más claros
- Configuración de `log4j2.xml` para reducir ruido de RestAssured

### 3. ❌ Lombok vs Java 21 - Incompatibilidad

**Problema**: Intenté usar Lombok pero generaba errores con Java 21

**Prompt usado**: *"Chat, esto me falla. Lombok no funciona con Java 21, ¿qué hago?"*

**Contribución de IA**:
- Confirmación de incompatibilidades conocidas entre Lombok y Java 21
- Sugerencia de implementar getters/setters manualmente
- Patrón Builder implementado sin dependencias externas

### 4. 📦 Implementación de Models Basada en Ejemplos

**Problema**: Tenía ejemplos de la clase pero no entendía cómo adaptarlos a mi API

**Prompt usado**: *"Chat, tengo el ejemplo de la grabación de la clase, ¿podrías explicarme paso a paso esos modelos para poder implementar yo los míos con la API que uso?"*

**Contribución de IA**:
- Análisis paso a paso de modelos de ejemplo de la clase
- Implementación correcta de anotaciones Jackson (@JsonProperty, @JsonIgnoreProperties)
- Patrón Builder para construcción de objetos

### 5. 🔐 TokenEncryption Implementation

**Problema**: Necesitaba implementar encriptación de tokens para el sistema

**Prompt usado**: *"Chat, necesito implementar encriptación de tokens. ¿Cómo hago esto de forma segura?"*

**Contribución de IA**:
- Implementación de clase `TokenEncryption` con AES
- Métodos de encrypt/decrypt seguros
- Validación de tokens encriptados

### 6. 🎭 Configuración de Test Runners

**Problema**: Confusión con múltiples runners y configuraciones de Cucumber

**Prompt usado**: *"Chat, tengo varios runners y no entiendo cuál usar. Los tests se ejecutan raro"*

**Contribución de IA**:
- Configuración correcta de `@ConfigurationParameter` para cada runner
- Explicación de filtros de tags (`@system`, `not @system`)
- Creación del CleanTestRunner para ejecución limpia

### 7. 🐛 Resolución de Errores Comunes

**Errores frecuentes resueltos con ayuda de IA:**

- **Error**: `java.lang.NoSuchMethodError` con RestAssured
  - **Solución**: Actualización de versiones compatibles en pom.xml

- **Error**: `Duplicate step definitions` en Cucumber
  - **Solución**: Reorganización de step definitions y eliminación de duplicados

- **Error**: Tests skipeados inesperadamente
  - **Solución**: Configuración correcta de tags y filtros en runners

- **Error**: `Missing API key` en reqres.in
  - **Solución**: Implementación de manejo graceful de errores de API

- **Error**: Context no compartido entre steps
  - **Solución**: Configuración correcta de PicoContainer y ScenarioContext

### 8. 🔄 Manejo de Contexto y Datos Compartidos

**Problema**: Datos no se compartían correctamente entre diferentes step definitions

**Prompt usado**: *"Chat, los datos no se pasan entre mis steps. El contexto no funciona"*

**Contribución de IA**:
- Implementación de `TestContext` y `ScenarioContext`
- Constantes estandarizadas en `TestContextKeys`
- Configuración correcta de inyección de dependencias

## 📝 Notas Adicionales

### ¿Por qué hay tests "extra"?

Los tests en la carpeta `example/` son parte del proceso de desarrollo:
- **Exploración de APIs**: Probar diferentes endpoints durante desarrollo
- **Validación de conectividad**: Verificar que las APIs funcionan
- **Aprendizaje**: Experimentar con diferentes enfoques antes de la implementación final

### Decisiones Técnicas

- **Java 21**: Versión LTS más reciente con mejores características
- **Maven**: Gestión de dependencias estándar en Java
- **Cucumber**: BDD para tests legibles por stakeholders
- **RestAssured**: DSL fluido para testing de APIs REST
- **Jackson**: Serialización/deserialización JSON robusta

## 🏆 Resultado Final

✅ **6 System Integration Tests** completamente funcionales  
✅ **Arquitectura robusta** con separación de servicios  
✅ **Manejo de errores** graceful y informativo  
✅ **Encriptación de tokens** implementada  
✅ **Documentación completa** del proyecto  
✅ **Código limpio** y mantenible  

---

*Desarrollado con Java 21, Maven, Cucumber, RestAssured y mucha dedicación* 🚀
