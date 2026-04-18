# Proyecto de Automatización - Selenium WebDriver
## Autor
**David Urbano**

## 🎯 Objetivo del Proyecto
Este proyecto contiene una suite completa de automatización de pruebas UI implementada con **Java, Selenium WebDriver y TestNG**. El marco de trabajo utiliza el patrón **Page Object Model (POM)** para garantizar un código limpio, mantenible y escalable.

## 🌐 Aplicación bajo Prueba (AUT)
Las pruebas automatizadas de este repositorio están diseñadas para interactuar con la siguiente aplicación de comercio electrónico:
* **URL:** [https://ecommerce-playground.lambdatest.io/](https://ecommerce-playground.lambdatest.io/)

*(Nota: En etapas anteriores también se contempló la plataforma [Demo Web Shop](https://demowebshop.tricentis.com))*

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 21
* **Core Automático:** Selenium WebDriver
* **Framework de Pruebas:** TestNG
* **Gestor de Dependencias:** Maven
* **Reportes:** Allure Reports
* **Integración Continua:** GitHub Actions
* **Infraestructura Virtualizada:** Docker / Docker Compose (Selenium Grid)

## 🚀 Cómo Ejecutar las Pruebas

### 1. Ejecución Local (Vía Consola)
Asegúrate de tener Maven instalado. Ejecuta el siguiente comando en la raíz del proyecto para iniciar la ejecución utilizando el archivo maestro `testng.xml`:

```bash
mvn clean test
```

### 2. Ejecución con Allure Reports
Para generar y visualizar el reporte final después de completar las pruebas:
```bash
mvn allure:serve
```
*Si tienes Allure instalado globalmente, puedes usar: `allure serve target/allure-results`*

## 🔄 Integración Continua (CI/CD)
El proyecto cuenta con un flujo completo de Integración Continua administrado mediante **GitHub Actions** (`.github/workflows/selenium.yml`).
Cada vez que se hace un `push` a la rama principal:
1. El pipeline configura Java y Maven.
2. Inicia una arquitectura de **Selenium Grid** optimizada a través de Docker.
3. Ejecuta la suite de pruebas completa en modo `headless`.
4. Si ocurren errores, la acción `always()` garantiza que **Allure Reports** siempre compile los resultados.
5. El reporte gráfico detallado se publica automáticamente mediante **GitHub Pages**.

## 📁 Estructura Principal
* `src/test/java/com/store/tests`: Casos de prueba lógicos (TestNG).
* `src/test/java/com/store/pages`: Mapeo de elementos y acciones utilizando Page Object Model.
* `src/test/java/com/store/utils`: Utilidades, validaciones de Base de Datos y BaseTest de configuración genérica.
* `.github/workflows`: Definición de pipelines para ejecución remota en la nube.
* `testng.xml`: Archivo maestro estructurador de la suite de validación.
