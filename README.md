Universal Chat TCP/UDP

Sistema de chat desenvolvido em Java com suporte a comunicação TCP e UDP utilizando sockets e interface gráfica moderna em Swing.

Sobre o Projeto

O Universal Chat TCP/UDP é uma aplicação desktop desenvolvida para demonstrar conceitos de comunicação em redes utilizando a API de sockets do Java.

O sistema permite a troca de mensagens entre máquinas através dos protocolos:

TCP (orientado à conexão)
UDP (não orientado à conexão)

A aplicação possui uma interface gráfica moderna construída com Java Swing e arquitetura baseada em separação de responsabilidades, utilizando interfaces e fábrica de criação de objetos.

Funcionalidades
Comunicação via TCP
Comunicação via UDP
Interface gráfica moderna em Swing
Seleção dinâmica do protocolo
Envio e recebimento de mensagens em tempo real
Comunicação entre computadores na mesma rede local
Uso de Threads para processamento paralelo
Arquitetura desacoplada utilizando interfaces
Implementação do padrão Factory
Tecnologias Utilizadas
Java
Java Swing
Sockets TCP
Sockets UDP
Multithreading
Eclipse IDE
## Estrutura do Projeto

```
src/
│
├── chat.api
│   ├── ChatException.java
│   ├── ChatFactory.java
│   ├── MessageContainer.java
│   ├── Receiver.java
│   ├── Sender.java
│   ├── TCPReceiver.java
│   ├── TCPSender.java
│   ├── UDPReceiver.java
│   └── UDPSender.java
│
├── chat.ui
│   ├── ChatView.java
│   └── Main.java
```

## Arquitetura

O projeto foi estruturado utilizando separação de responsabilidades:

API do Chat

Responsável pela comunicação em rede:

envio de mensagens
recebimento de mensagens
controle de sockets
tratamento de exceções
Interface Gráfica

Responsável pela interação com o usuário:

exibição de mensagens
entrada de dados
seleção do protocolo
configuração de conexão
Funcionamento

O usuário informa:

Porta Local
IP Remoto
Porta Remota
Protocolo (TCP ou UDP)

Após isso, o sistema cria automaticamente o ambiente de comunicação através da ChatFactory.

Protocolos
TCP
Orientado à conexão
Mais confiável
Garante entrega das mensagens
UDP
Não orientado à conexão
Mais rápido
Menor overhead
Não garante entrega
Execução
1. Clone o repositório
git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
2. Abra no Eclipse

Importe o projeto Java normalmente.

3. Execute a classe Main
chat.ui.Main
Testando Localmente
Mesmo computador

Use portas diferentes:

Janela 1
Protocolo: UDP
Porta Local: 5000
IP Remoto: 127.0.0.1
Porta Remota: 6000
Janela 2
Protocolo: UDP
Porta Local: 6000
IP Remoto: 127.0.0.1
Porta Remota: 5000
Comunicação em Rede Local

O sistema também suporta comunicação entre computadores na mesma rede.

Basta utilizar o IPv4 da máquina remota.

Exemplo:

192.168.0.10
Conceitos Aplicados
Programação orientada a objetos
Interfaces
Factory Pattern
Multithreading
Sockets TCP/UDP
Comunicação cliente-servidor
Interface gráfica desktop
Separação de responsabilidades
Objetivo Acadêmico

Este projeto foi desenvolvido com fins educacionais para aplicação prática de conceitos de:

Sistemas Distribuídos
Redes de Computadores
Programação Concorrente
Comunicação via Sockets
Autor

Luís Henrique Freire de Lima

Estudante de Sistemas de Informação e desenvolvedor de software.
