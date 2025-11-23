# 🐾 Mascotmercio – Plataforma de ocio pet-friendly

Mascotmercio es una plataforma web diseñada para conectar **establecimientos pet-friendly** con usuarios que buscan lugares donde disfrutar de su tiempo libre junto a sus mascotas.  
Ofrece una experiencia completa para clientes y dueños de locales, permitiendo **descubrir, valorar, reservar y gestionar** establecimientos aptos para mascotas.

---

## 🚀 Funcionalidades principales

### 👤 Roles de usuario
#### **Cliente**
- Buscar establecimientos en lista y mapa interactivo  
- Filtrar locales por zona / código postal  
- Reservar en los establecimientos  
- Ver información detallada del local  
- Valorar y comentar establecimientos  
- Subir fotos de su experiencia  
- Editar su perfil: foto, nombre, contraseña, eliminar cuenta  

#### **Dueño de local**
- Registrar nuevos establecimientos  
- Editar información de sus locales  
- Responder comentarios de clientes  
- Gestionar perfil propio y de sus locales  

---

## 🗺️ Mapa interactivo
- Mapa integrado para explorar la oferta pet-friendly  
- Acceso directo a cada establecimiento clicando en el mapa  
- Filtros por zona  
- Visualización rápida de información

---

## 🏪 Gestión de establecimientos
- Publicación de locales pet-friendly  
- Edición de información: descripción, fotos, servicios, horarios  
- Sistema de reseñas con puntuación y comentarios  
- Respuestas del propietario

---

## 📝 Autenticación y gestión de cuentas
- Inicio de sesión seguro  
- Registro con selección de rol  
- Gestión completa del perfil personal  

---

## 🛠️ Tecnologías utilizadas

### **Backend (API REST) – `mascotmercioapiback`**
- **Java 17**
- **Spring Boot**
- Spring MVC  
- Spring Data JPA  
- Controladores + Servicios + Repositorios  
- SLF4J  
- Maven  
- Estructura modular y escalable  

### **Frontend (Web) – `mascotmercioapifront`**
- **Java 17**
- **Spring Boot** (renderización de plantillas HTML)
- **HTML5, CSS3, JavaScript**
- Plantillas HTML renderizadas en servidor
- Bootstrap  
- FontAwesome  
- AOS  
- Swiper  
- Glightbox  

### **Otras tecnologías**
- Maven wrapper (`mvnw`)  
- pom.xml con gestión de dependencias  
- Arquitectura cliente-servidor en tres niveles  
- Patrón MVC  

---

## 📦 Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

### ✔ Java JDK 17  
Recomendado: **Adoptium Temurin**  
https://adoptium.net/temurin/releases?version=17

### ✔ Git  
https://git-scm.com/

### ✔ Visual Studio Code (opcional)  
Extensiones recomendadas:  
- **Extension Pack for Java**  
- **Spring Boot Extension Pack**  
- **Lombok Support** (si se añadiera más adelante)  

📌 No es necesario instalar Maven, el proyecto incluye `mvnw`.

---

## 🔧 Instalación y ejecución

El proyecto está dividido en dos módulos independientes:

```
mascotmercioapiback    → Backend (API REST)
mascotmercioapifront   → Frontend (Web)
```

---

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/jcobosp/Mascotmercio
cd Mascotmercio
```

---

## 2️⃣ Arrancar el backend

```bash
cd mascotmercioapiback
./mvnw spring-boot:run
```

Cuando arranque verás:

```
Tomcat started on port(s): 8080
```

➡ Backend disponible en  
**http://localhost:8080**

---

## 3️⃣ Arrancar el frontend

En otra terminal:

```bash
cd mascotmercioapifront
./mvnw spring-boot:run
```

Verás algo como:

```
Tomcat started on port(s): 8083
```

➡ Interfaz web disponible en  
**http://localhost:8083**

---

## 4️⃣ Acceder a la plataforma

Una vez arrancado el frontend:

👉 **http://localhost:8083**

---

## 🗂️ Estructura del proyecto

<details>
<summary><strong>📁 Ver estructura completa del repositorio</strong></summary>

```
├── .vscode
├── mascotmercioapiback
│   ├── .mvn
│   ├── src
│   │   ├── main
│   │   │   ├── java/es/upm/dit/isst/mascotmercioapiback
│   │   │   │   ├── controller
│   │   │   │   ├── model
│   │   │   │   └── repository
│   │   │   └── resources/static/img
│   │   └── test
│   └── target
│
└── mascotmercioapifront
    ├── .mvn
    ├── src
    │   ├── main
    │   │   ├── java/es/upm/dit/isst/mascotmercioapifront
    │   │   │   ├── config
    │   │   │   ├── controller
    │   │   │   └── model
    │   │   └── resources/static/assets
    │   └── test
    └── target
```

</details>

---

## 💡 Notas para desarrolladores

- El backend y el frontend se ejecutan como **aplicaciones Spring Boot independientes**.  
- El frontend se comunica con el backend mediante **servicios REST**.  
- El proyecto puede migrarse fácilmente a:
  - MySQL  
  - PostgreSQL  
  - MongoDB  
- Estructura limpia y modular basada en MVC.  
- Compatible con cualquier editor de código profesional.  

---

## 📸 Capturas recomendadas

Crea la carpeta:

```
/docs/screenshots
```

y añade imágenes de:

- Página de inicio  
- Mapa interactivo  
- Lista de establecimientos  
- Vista de cliente  
- Vista de dueño  
- Ficha de establecimiento  
- Perfil de usuario  

Ejemplo en README:

```md
<img src="docs/screenshots/home.png" width="750"/>
```

---

## 📄 Licencia

Proyecto publicado con fines académicos y demostrativos.

---

## 👨‍💻 Autor

**Javier Cobos**  
GitHub: https://github.com/jcobosp  
Proyecto: https://github.com/jcobosp/Mascotmercio
