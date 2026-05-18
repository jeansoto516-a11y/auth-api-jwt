# 🔐 Auth API - Spring Boot JWT

API de autenticação desenvolvida com Java + Spring Boot utilizando autenticação JWT (JSON Web Token).

O projeto foi criado com foco em estudos avançados de backend, segurança de APIs REST e arquitetura moderna utilizando Spring Security.

---

# 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- H2 Database
- Maven
- Hibernate

---

# 📁 Estrutura do Projeto

```bash
src
 └── main
     ├── java
     │   └── com.jean.auth_api
     │       ├── controller
     │       ├── dto
     │       ├── model
     │       ├── repository
     │       ├── security
     │       └── config
     │
     └── resources
         └── application.properties
```

---

# 🔐 Funcionalidades

✅ Cadastro de usuários  
✅ Login com autenticação JWT  
✅ Criptografia de senha com BCrypt  
✅ Geração de token JWT  
✅ Validação de token  
✅ Integração com Spring Security  
✅ API RESTful  

---

# ⚙️ Como Executar o Projeto

## 1️⃣ Clonar repositório

```bash
git clone https://github.com/SEU-USUARIO/auth-api.git
```

---

## 2️⃣ Entrar na pasta

```bash
cd auth-api
```

---

## 3️⃣ Executar aplicação

Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Linux/Mac:

```bash
./mvnw spring-boot:run
```

---

# 🌐 Servidor

A aplicação iniciará em:

```bash
http://localhost:8080
```

---

# 🧪 Endpoints

# ✅ Registro de Usuário

## POST

```http
/auth/register
```

### Body

```json
{
  "name": "Jean",
  "email": "jean@email.com",
  "password": "123456"
}
```

---

# 🔑 Login

## POST

```http
/auth/login
```

### Body

```json
{
  "email": "jean@email.com",
  "password": "123456"
}
```

---

# ✅ Resposta esperada

```json
{
  "token": "SEU_TOKEN_JWT"
}
```

---

# 🔒 Segurança

O projeto utiliza:

- Spring Security
- PasswordEncoder BCrypt
- JWT Authentication
- Tokens com expiração

---

# 🗄️ Banco de Dados

Atualmente o projeto utiliza banco em memória H2 para desenvolvimento.

Console H2:

```bash
http://localhost:8080/h2-console
```

---

# 📚 Objetivos do Projeto

Este projeto foi desenvolvido para:

- Aprender autenticação JWT
- Estudar Spring Security
- Praticar arquitetura backend
- Desenvolver APIs REST seguras
- Evoluir conhecimentos em Java Backend

---

# 👨‍💻 Autor

Jean Carlos Soto Barbosa

Software Engineering Student | Full Stack Developer

---

# 📄 Licença

Este projeto está sob licença MIT.