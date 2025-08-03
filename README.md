# 📧 Envio de E-mails com Clean Architecture (Java + Spring + AWS SES)

Este documento explica de forma simples como funciona o envio de e-mails usando uma **arquitetura limpa** em um projeto Java com **Spring Boot** e **AWS SES**.

Você vai entender o **papel de cada camada**, **interface** e **classe**, além do **fluxo completo**, desde a requisição até o envio real do e-mail.

---

## 🧠 O que é Clean Architecture?

A **Clean Architecture** (Arquitetura Limpa) organiza o projeto em camadas bem definidas, separando responsabilidades.  
Seu principal objetivo é permitir que:

- As regras de negócio **não dependam de detalhes técnicos** como frameworks ou serviços.
- Seja fácil **testar**, **manter** e **evoluir** o sistema.

---

## 🗂️ Camadas do Projeto

- **Controller:** Recebe requisições HTTP da API.
- **Application:** Executa os casos de uso (regras de negócio).
- **Core:** Define os contratos das regras de negócio (interfaces).
- **Adapters:** Define contratos de integração com serviços externos.
- **Infra:** Implementação concreta da tecnologia (ex: AWS SES).

---

## 🔄 Fluxo Completo da Requisição

1. O cliente faz uma requisição HTTP para `POST /api/email`.
2. O **Controller** recebe a requisição e repassa os dados para o Service.
3. O **Service** (na Application) executa o caso de uso.
4. O Service usa o **EmailSenderGateway** (interface) como contrato.
5. Uma implementação concreta como `SesEmailSender` executa o envio.
6. A **AWS SES** envia o e-mail para o destinatário.

---

## 🎯 Importância das Interfaces

As interfaces têm um papel fundamental:

- **Desacoplam** o sistema da tecnologia.
- Permitem **trocar o provedor** de e-mails (ex: SMTP, SendGrid, etc) sem mudar o resto do código.
- Facilitam **testes unitários** usando mocks.
- **Mantêm a lógica de negócio pura** e independente de implementações externas.

---

## 🧩 Papel de Cada Componente

| Componente             | Papel                                                                 |
|------------------------|-----------------------------------------------------------------------|
| `EmailSenderController` | Recebe a requisição HTTP da API e delega o envio                    |
| `EmailSenderService`    | Implementa o caso de uso: enviar e-mail                             |
| `EmailSenderUseCase`    | Interface que define o contrato do caso de uso                      |
| `EmailSenderGateway`    | Interface que define como serviços externos devem enviar e-mails     |
| `SesEmailSender`        | Implementação real que usa o SDK da AWS para enviar o e-mail         |

---

## ✅ Exemplo Visual do Fluxo

