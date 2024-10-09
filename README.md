# Order Management System

Este projeto é um sistema de gerenciamento de pedidos de uma loja de papelaria, composto por três microsserviços que se comunicam utilizando RabbitMQ. A arquitetura é orientada a eventos, onde os pedidos são criados, processados e notificados através de um sistema distribuído de mensageria. 

### Serviços

1. **OrderService**: Responsável por receber os pedidos dos clientes e publicá-los em uma fila.
2. **InventoryService**: Consome as mensagens do `OrderService`, processa os pedidos e atualiza o status.
3. **NotificationService**: Consome as mensagens do `InventoryService` e notifica os clientes sobre o status dos seus pedidos.

### Tecnologias Utilizadas

- **Spring Boot**: Framework Java utilizado para criar os três serviços.
- **RabbitMQ**: Sistema de mensageria usado para comunicação assíncrona entre os serviços.
- **Lombok**: Biblioteca que simplifica a escrita de código boilerplate no Java.
- **Docker**: Para facilitar o deploy dos microsserviços e do RabbitMQ.

## Arquitetura

O sistema é organizado de forma a garantir o desacoplamento e a escalabilidade. Cada serviço tem uma função clara, e eles se comunicam através de mensagens publicadas em filas do RabbitMQ. 

### Fluxo de Trabalho

1. O cliente faz um pedido através do `OrderService`.
2. O `OrderService` publica uma mensagem contendo os detalhes do pedido em uma fila RabbitMQ.
3. O `InventoryService` consome a mensagem, processa o pedido e publica uma mensagem de status.
4. O `NotificationService` consome a mensagem de status e envia uma notificação ao cliente.

### Estrutura do Projeto

- **OrderService**: 
  - `OrderController`: Controlador responsável por receber pedidos dos clientes e publicá-los na fila.
  - `RabbitMQConfig`: Configuração da fila e troca de mensagens para o RabbitMQ.
  - `Order`: Modelo representando um pedido.

- **InventoryService**: 
  - `InventoryConsumer`: Consome as mensagens da fila de pedidos e processa o pedido.
  - `RabbitMQConfig`: Configuração da fila e troca de mensagens para o RabbitMQ.
  - `Order`: Modelo representando um pedido processado.

- **NotificationService**: 
  - `NotificationService`: Consome as mensagens do `InventoryService` e notifica o cliente.
  - `RabbitMQConfig`: Configuração da fila e troca de mensagens para o RabbitMQ.

## Como Executar

### Pré-requisitos

- **Java 17** ou superior
- **Maven**
- **RabbitMQ** (pode ser executado localmente ou em container Docker)

### Subindo o RabbitMQ com Docker

Se preferir, você pode subir uma instância do RabbitMQ usando Docker:

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Acesse o painel de gerenciamento do RabbitMQ em `http://localhost:15672` com as credenciais padrão: `guest/guest`.

### Executando o Projeto

1. Clone este repositório:
    ```bash
    git clone https://github.com/seu-usuario/order-management-system.git
    cd order-management-system
    ```

2. Compile e rode cada serviço:

   Para **OrderService**:
    ```bash
    cd OrderService
    mvn clean install
    mvn spring-boot:run
    ```

   Para **InventoryService**:
    ```bash
    cd ../InventoryService
    mvn clean install
    mvn spring-boot:run
    ```

   Para **NotificationService**:
    ```bash
    cd ../NotificationService
    mvn clean install
    mvn spring-boot:run
    ```

### Testando a Aplicação

1. Enviar um pedido para o `OrderService`:
    ```bash
    curl -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{
        "orderId": "12345",
        "item": "Caneta",
        "quantity": 10
    }'
    ```

2. O `InventoryService` processará o pedido e publicará um status, que será consumido pelo `NotificationService`, que notificará o cliente.

### Rotas

- **OrderService**:
    - `POST /orders`: Cria um novo pedido.

## Melhorias Futuras

- Implementar autenticação nos serviços.
- Adicionar um sistema de gerenciamento de estoque real no `InventoryService`.
- Incluir notificações via e-mail ou SMS no `NotificationService`.

## Contribuição

1. Faça um fork do repositório.
2. Crie uma branch para a sua feature:
   ```bash
   git checkout -b minha-feature
   ```
3. Faça commit das suas alterações:
   ```bash
   git commit -m 'Minha nova feature'
   ```
4. Faça push para a branch:
   ```bash
   git push origin minha-feature
   ```
5. Abra um pull request.

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## **Contato** 
Leonardo Bernardo - [leonardo.bernardo2658@gmail.com](mailto:leonardo.bernardo2658@gmail.com)
