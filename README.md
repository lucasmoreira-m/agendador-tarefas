# Agendador de Tarefas 🗓️

Este é o serviço principal responsável pela gestão e agendamento de compromissos ou tarefas. Ele permite criar, atualizar e monitorar o status de cada atividade.

## 🚀 Tecnologias
- Java 17
- Spring Boot (Spring Data JPA, Web)
- Gradle
- Docker & Docker Compose
- PostgreSQL (ou o banco que você usou)

## 🛠️ Funcionalidades
- [x] Criação e edição de tarefas com data e hora.
- [x] Gerenciamento de status (Pendente, Concluído, Cancelado).
- [x] Integração com o serviço de notificações para alertas de prazos.

## 📦 Como rodar
1. Clone o repositório.
2. Com o Docker aberto, execute: `docker-compose up`
3. A API estará disponível em `http://localhost:8080` (ajuste a porta se necessário).
