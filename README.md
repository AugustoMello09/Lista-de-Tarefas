<h1> Lista de Tarefas </h1>

<h3 align="center">
    Projeto Lista de Tarefas 📝
    <br>
    <br><br>
    <p align="center">
      <a href="#-sobre">Sobre</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="#-back">Backend</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="#-execB">Execução do projeto Backend</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="#-execF">Execução do projeto Frontend</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="#-execD">Execução o Docker-compose</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="#-tex">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
      <a href="#-contato">Entre em contato</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  </p>
</h3>

<hr>

<h3 id="#-sobre"> Sobre </h3>

Este projeto é uma aplicação Full Stack para uma Lista de Tarefas, desenvolvida utilizando Java com Spring Boot 3+ no backend e Angular no frontend. O objetivo é oferecer uma interface prática e moderna, permitindo aos usuários criar, editar, excluir e reorganizar tarefas de forma dinâmica, usando funcionalidades de "drag and drop".

https://github.com/user-attachments/assets/065c139e-fec5-42ee-9fa3-9819f26f9920

<hr>

<h3 id="#-back"> Backend 🔧 </h3>

<h2> Projeto lista de tarefas 🧱 </h2>

![Screenshot_53](https://github.com/user-attachments/assets/c1f79c9e-6ac4-433f-b73f-7ad61da086dc)

#### Diagrama de Classe

![Screenshot_54](https://github.com/user-attachments/assets/bb1f017a-d4cf-46bb-bc71-3c35ab61dfad)

### Funcionalidades Principais

__Criando Tarefas__: Os usuários podem criar novas tarefas com facilidade.

> __Observação: Não é possível criar uma tarefa com um nome já existente.__

__Organizando suas tarefas__: Após criar as tarefas, os usuários podem organizá-las de duas maneiras:

 - Arrastando e soltando a tarefa na posição desejada (funcionalidade de "drag and drop").

 - Utilizando setas para mover as tarefas para cima ou para baixo na lista.

__Editando tarefas__: As tarefas criadas podem ser editadas pelos usuários para atualizar informações.

> __Observação: Não é possível editar uma tarefa para utilizar um nome já existente.__

__Excluindo tarefas__: Os usuários têm a opção de excluir tarefas criadas, mantendo a lista organizada e relevante.


<hr>

<h3> Documentação com Swagger 📗 </h3>

>  A documentação do nosso sistema usando Swagger, proporcionando uma visão unificada e acessível de todos os nossos serviços

![Frame_1](https://github.com/user-attachments/assets/8e30e432-aa10-49b4-83fa-4e35b0ae39cd)

https://github.com/user-attachments/assets/711810fe-4332-4e3a-9fbd-d1ae67ab91a1

__Acesse a documentação do projeto:__

 - __Acesse:__ https://deploylista.onrender.com/swagger-ui/index.html#/

> Quando subir a aplicação acesse usando esse link

 - __Acesse:__ http://localhost:8080/swagger-ui.html para visualizar os endpoints.

<hr>

<h3> Migration com Flyway ️‍️✈️ </h3>

> Utilizei o Flyway para gerenciar e versionar as migrações do banco de dados de forma automática e eficiente. Isso garante que o esquema do banco esteja sempre atualizado, permitindo controle de versão e facilidade na aplicação de novas alterações.

![flyway](https://github.com/user-attachments/assets/0cffa7a8-48f2-4b0c-b53e-0ecbbb19bf0b)

<hr>

<h3 id="#-execB"> Execução do projeto Backend 🤓 </h3>

## Execute o projeto 👁‍🗨

__Pré-requisitos:__ Java 17  & (Docker opcional)

__Clone o repositório do projeto__

~~~~~~Bash
git clone https://github.com/AugustoMello09/Lista-de-Tarefas.git
~~~~~~

### Configurando o projeto local 🏠

__Configurando o ambiente:__

- Navegue até o diretório do projeto

~~~~~~Bash
cd ListTasksBackend
~~~~~~

- Acesse o diretório do projeto, utilize o comando `cd` e o nome do diretório para instalar todas as dependências necessárias:

~~~~~~Bash
# exemplo
cd ListTasksBackend

mvn clean package -DskipTest=true
~~~~~~

- Agora execute o seguinte comando para executar o projeto

~~~~Bash

cd ListTasksBackend

mvn spring-boot:run
~~~~

### Configurando o projeto para usar Docker 🐳

__com o terminal aberto use o seguinte comando:__

~~~~~~Bash
docker run -d -p 8080:8080 --name backend augustomello09/list-task-backend:latest
~~~~~~


<h3 id="#-execF"> Execução do projeto Frontend 🤪 </h3>

## Execute o projeto 👁

__Pré-requisitos:__ Angular & Node (20) (Docker opcional)

__Executar__

- Certifique-se de ter o Node.js e o Angular CLI instalados em seu ambiente.
- Navegue até a pasta do projeto front-end:

~~~~~~Bash
cd ListTasksFrontend
~~~~~~

__Instale as dependências do projeto:__

~~~~~~Bash
npm install
~~~~~~

- Suba o Frontend

~~~~~~Bash
ng serve
~~~~~~

### Configurando o projeto para usar Docker 🐳

__com o terminal aberto use o seguinte comando:__

__AVISO ⚠️__:

> Antes de iniciar o frontend, certifique-se de que o backend está em execução para que as requisições funcionem corretamente.

~~~~~~Bash
docker run -d -p 4200:4200 --name backend augustomello09/augustomello09/list-task-frontend:latest
~~~~~~

<hr>

<h3 id="#-execD"> Execução do com docker-compose  🐳</h3>

### Execute o projeto 👁

__Clone o repositório do projeto__

~~~~~~Bash
git clone https://github.com/AugustoMello09/Lista-de-Tarefas.git
~~~~~~

- Com o docker aberto, suba o docker-compose

~~~~~~Bash
docker-compose up -d
~~~~~~

<h3 id="#-tec"> Tecnologias </h3>

<div style="display: inline_block"><br>

<img align="center" alt="Augusto-Java" height="70" width="70" src="https://github.com/devicons/devicon/blob/master/icons/java/java-original.svg">
<img align="center" alt="Augusto-SpringBoot" height="70" width="70" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/spring/spring-original-wordmark.svg">
<img align="center" alt="Augusto-MYSQL" height="60" width="60"
src= https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/mysql/mysql-original.svg>
<img align="center" alt="Augusto-Docker" height="70" width="70" src="https://raw.githubusercontent.com/devicons/devicon/1119b9f84c0290e0f0b38982099a2bd027a48bf1/icons/docker/docker-original.svg">
<img align="center" alt="Augusto-Swagger" height="40" width="40" src="https://github.com/AugustoMello09/Locadora/assets/101072311/a895137a-8126-4eed-8a5c-9934ed30401b">
<img align="center" alt="Augusto-ANGULAR" height="50" width="50" src="https://raw.githubusercontent.com/get-icon/geticon/fc0f660daee147afb4a56c64e12bde6486b73e39/icons/angular-icon.svg">

</div>

<hr>

<h3 id="#-contato"> Entre em contato </h3>

### contato

Para mais informações sobre o projeto ou para entrar em contato, você pode me encontrar através dos canais abaixo:

<div style="display: inline_block">

  <a href="https://www.linkedin.com/in/augusto-mello-794a94234" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
 <a href="mailto:joseaugusto.mello01@gmail.com" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>

</div>

