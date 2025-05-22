# 📇 CustomerConnect – Sistema de Gerenciamento de Clientes
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

---

Bem-vindo ao **CustomerConnect**, um projeto prático e envolvente desenvolvido para te ajudar a dominar os fundamentos do **Spring Boot** e do **Spring Data JPA**. Neste sistema, você criará uma aplicação backend robusta capaz de realizar operações **CRUD** em uma entidade `Customer`, seguindo boas práticas de desenvolvimento e regras de negócio reais.

---

## 🚀 Objetivo do Projeto

Ao final deste módulo, você terá:

- Uma compreensão sólida sobre como integrar sua aplicação com bancos de dados usando **Spring Data JPA**;
- Experiência prática na implementação de **endpoints RESTful** com suporte a **validação**, **paginação**, **filtros personalizados** e **segurança de dados**;
- Um sistema backend pronto para uso em aplicações reais, com foco em manutenção, escalabilidade e organização.

---

## 📌 Regras de Negócio

### 🧾 Dados Cadastrais

Cada cliente deve possuir os seguintes atributos:

- `fullName` – Nome completo  
- `cpf` – CPF  
- `email` – Email  
- `phoneNumber` – Telefone celular  
- `createdAt` e `updatedAt` – Datas de criação e atualização para fins de auditoria  

### 🛡 Cadastro Único

- Não são permitidos cadastros com **CPF**, **email** ou **ID** duplicados.

### 🔍 Busca Flexível

- Suporte à **paginação** e **ordenação** de resultados.  
- Filtros por **CPF** e/ou **email**.

---

## 📡 Endpoints REST

### 📥 Criar Cliente

`POST /customers`

**Parâmetros:**

```json
{
  "fullName": "Dylan whitaker",
  "cpf": "12345678900",
  "email": "dylanwhitaker@email.com",
  "phoneNumber": "11999999999"
}
```

**Resposta:**

```json
{
  "customerId": 1
}
```

---

### 📄 Listar Clientes

`GET /customers`

**Parâmetros de consulta opcionais:**

- `page`
- `pageSize`
- `orderBy` (por data de criação)
- `cpf`
- `email`

**Resposta:**

```json
{
  "data": [ /* lista de clientes */ ],
  "pagination": { /* dados da paginação */ }
}
```

---

### ✏️ Atualizar Cliente

`PUT /customers/{customerId}`

**Parâmetros:**

```json
{
  "fullName": "João A. Silva",
  "email": "joaoatualizado@email.com",
  "phoneNumber": "11988888888"
}
```

---

### ❌ Deletar Cliente

`DELETE /customers/{customerId}`

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Maven**

---

## 🧠 Aprendizados

Ao concluir este projeto, você terá colocado em prática:

- Criação e organização de um projeto Spring Boot  
- Configuração de banco de dados com Spring Data JPA  
- Definição de entidades e repositórios  
- Implementação de uma camada de serviço com regras de negócio  
- Exposição de endpoints RESTful  
- Aplicação de validações e tratamento de erros

---

## 📂 Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/com/customerconnect/
 │   │   ├── controller/
 │   │   ├── service/
 │   │   ├── repository/
 │   │   └── model/
 │   └── resources/
 │       └── application.properties
```

---

## 🤝 Contribuições

Sinta-se à vontade para clonar, usar e contribuir com melhorias. Pull requests são muito bem-vindos!