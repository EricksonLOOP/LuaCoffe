<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<h1>LuaCoffee</h1>

<p>
    LuaCoffee é uma aplicação que permite criar, gerenciar e executar scripts Lua via API. O projeto fornece uma interface RESTful que suporta a execução de scripts, criação de APIs personalizadas e rotas para várias operações, como <code>GET</code>, <code>POST</code>, <code>PUT</code> e <code>DELETE</code>.
</p>

<h2>Índice</h2>
<ul>
    <li><a href="#sobre-o-projeto">Sobre o Projeto</a></li>
    <li><a href="#arquitetura">Arquitetura</a></li>
    <li><a href="#tecnologias-utilizadas">Tecnologias Utilizadas</a></li>
    <li><a href="#instalacao-e-configuracao">Instalação e Configuração</a></li>
    <li><a href="#uso">Uso</a></li>
    <ul>
        <li><a href="#execucao-de-scripts-lua">Execução de Scripts Lua</a></li>
        <li><a href="#gerenciamento-de-apis">Gerenciamento de APIs</a></li>
    </ul>
    <li><a href="#contribuicao">Contribuição</a></li>
    <li><a href="#licenca">Licença</a></li>
</ul>

<h2 id="sobre-o-projeto">Sobre o Projeto</h2>
<p>
    LuaCoffee é uma plataforma baseada em <strong>LuaJ</strong> que facilita a execução e o gerenciamento de scripts Lua em um ambiente Java. Ele permite que os desenvolvedores criem APIs dinâmicas que podem ser chamadas via HTTP, o que possibilita interações flexíveis com sistemas externos através de scripts personalizados.
</p>

<h3>Principais Funcionalidades</h3>
<ul>
    <li><strong>Execução de scripts Lua</strong>: Integração com o interpretador Lua para carregar e executar scripts personalizados.</li>
    <li><strong>Gerenciamento de APIs</strong>: Permite criar, listar, atualizar e deletar APIs com base em rotas e métodos HTTP.</li>
    <li><strong>Suporte a múltiplos métodos HTTP</strong>: Suporta rotas <code>GET</code>, <code>POST</code>, <code>PUT</code> e <code>DELETE</code>.</li>
    <li><strong>Armazenamento de Scripts</strong>: Os scripts são armazenados em um banco de dados e podem ser recuperados e executados dinamicamente.</li>
</ul>

<h2 id="arquitetura">Arquitetura</h2>
<p>
    O projeto segue uma arquitetura <strong>RESTful</strong> com separação clara entre controladores, serviços, entidades e repositórios.
</p>
<ul>
    <li><strong>Controllers</strong>: Responsáveis por gerenciar as requisições HTTP e encaminhá-las para os serviços.</li>
    <li><strong>Services</strong>: Contêm a lógica de negócios e tratam as operações com scripts Lua e APIs.</li>
    <li><strong>Repositories</strong>: Fazem a comunicação com o banco de dados, utilizando JPA para manipulação de entidades.</li>
    <li><strong>Entities</strong>: Representam as entidades do banco de dados, como APIs e Scripts Lua.</li>
</ul>

<h2 id="tecnologias-utilizadas">Tecnologias Utilizadas</h2>
<ul>
    <li><strong>Java 17</strong>: Linguagem principal do projeto.</li>
    <li><strong>Spring Boot 3</strong>: Framework para criação de APIs e gerenciamento de dependências.</li>
    <li><strong>LuaJ</strong>: Biblioteca para execução de scripts Lua em Java.</li>
    <li><strong>PostgreSQL</strong>: Banco de dados utilizado para armazenar APIs e scripts.</li>
    <li><strong>Maven</strong>: Gerenciador de dependências e automação de builds.</li>
</ul>

<h2 id="instalacao-e-configuracao">Instalação e Configuração</h2>

<h3>Pré-requisitos</h3>
<ul>
    <li><strong>Java 17</strong> ou superior instalado</li>
    <li><strong>Maven</strong> instalado</li>
    <li><strong>PostgreSQL</strong> configurado</li>
</ul>

<h3>Passos para Instalação</h3>
<ol>
    <li><strong>Clone o repositório</strong>:
        <pre><code>git clone https://github.com/username/LuaCoffee.git
cd LuaCoffee</code></pre>
    </li>
    <li><strong>Configure o banco de dados</strong>:
        <p>Crie um banco de dados PostgreSQL e atualize o arquivo <code>application.properties</code> com suas credenciais de banco de dados:</p>
        <pre><code>spring.datasource.url=jdbc:postgresql://localhost:5432/luacoffee_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update</code></pre>
    </li>
    <li><strong>Instale as dependências e compile o projeto</strong>:
        <pre><code>mvn clean install</code></pre>
    </li>
    <li><strong>Execute a aplicação</strong>:
        <pre><code>mvn spring-boot:run</code></pre>
    </li>
</ol>

<p>A aplicação estará rodando em <code>http://localhost:8080</code>.</p>

<h2 id="uso">Uso</h2>

<h3 id="execucao-de-scripts-lua">Execução de Scripts Lua</h3>
<p>Para executar scripts Lua, use o seguinte endpoint:</p>

<h4><strong>POST</strong> <code>/luabridge/post/api</code></h4>
<ul>
    <li><strong>Body</strong>:</li>
</ul>
<pre><code>{
    "route": "/meuScript",
    "params": {
      "nome": "Mundo"
    }
}</code></pre>

<ul>
    <li><strong>Resposta</strong>:</li>
</ul>
<pre><code>{
    "response": "Olá Mundo!"
}</code></pre>

<h3 id="gerenciamento-de-apis">Gerenciamento de APIs</h3>

<h4><strong>Criar uma nova API</strong></h4>
<p><strong>POST</strong> <code>/api/actions/create/api</code></p>
<ul>
    <li><strong>Body</strong>:</li>
</ul>
<pre><code>{
    "name": "minhaAPI",
    "description": "API para testes",
    "route": "/test",
    "method": "POST"
}</code></pre>
<ul>
    <li><strong>Resposta</strong>:</li>
</ul>
<pre><code>{
    "message": "API criada com sucesso!"
}</code></pre>

<h4><strong>Adicionar uma rota à API</strong></h4>
<p><strong>POST</strong> <code>/api/actions/add/rota</code></p>
<ul>
    <li><strong>Body</strong>:</li>
</ul>
<pre><code>{
    "apiName": "minhaAPI",
    "route": "/minhaRota",
    "method": "GET"
}</code></pre>
<ul>
    <li><strong>Resposta</strong>:</li>
</ul>
<pre><code>{
    "message": "Rota adicionada com sucesso!"
}</code></pre>

<h4><strong>Listar todas as APIs</strong></h4>
<p><strong>GET</strong> <code>/api/actions/lista/api</code></p>
<ul>
    <li><strong>Resposta</strong>:</li>
</ul>
<pre><code>[
    {
        "name": "minhaAPI",
        "description": "API para testes",
        "route": "/test",
        "method": "POST"
    }
]</code></pre>

<h2 id="contribuicao">Contribuição</h2>
<p>Contribuições são bem-vindas! Siga os passos abaixo para contribuir:</p>
<ol>
    <li>Faça um fork do projeto.</li>
    <li>Crie uma nova branch (<code>git checkout -b feature/nova-funcionalidade</code>).</li>
    <li>Commit suas mudanças (<code>git commit -m 'Adiciona nova funcionalidade'</code>).</li>
    <li>Envie sua branch para o GitHub (<code>git push origin feature/nova-funcionalidade</code>).</li>
    <li>Abra um Pull Request.</li>
</ol>

<h2 id="licenca">Licença</h2>
<p>Este projeto está sob a licença MIT - veja o arquivo <code>LICENSE</code> para mais detalhes.</p>

</body>
</html>
