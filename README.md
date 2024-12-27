<img src="./assets/luacoffe.png" alt="LuaCoffe v1.0 Alpha logo">
<h1>OppoSys - LuaCoffe</h1>
<p>LuaCoffe é um framework desenvolvido pelo OppoSys, criado em JAVA que visa permitir a criação de aplicações Lua de forma rápida e eficiente.</p>
<h2>Como funciona?</h2>
<p>O LuaCoffe é uma plataforma que integra a simplicidade e flexibilidade da linguagem Lua com a robustez e performance do Java. Ele usa a API de interpretação LuaJ, que permite que scripts Lua sejam processados na JVM (Java Virtual Machine), criando um ambiente híbrido onde ambos os mundos coexistem de forma eficiente.
</p>
</br>
<h2>Arquitetura do LuaCoffe</h2>
<p>
No coração do LuaCoffe está o o LuaJ e o SpringBoot, LuaJ possibilita a execução de script Lua dentro de um servidor Java criado com Tomcat através do SpringBoot. O servidor desenvolvido com SpringBoot fornece a infraestrutura necessária para a criação de Endpoints e gerênciamento de chamadas HTTP.
</p>
</br>
<h2>Funcionamento do LuaCoffe</h2>
<p>
A arquitetura do LuaCoffe permite que você crie um servidor Java (baseado no Spring Boot) e, dentro desse servidor, execute scripts Lua através da integração com o LuaJ. O processo se desenrola da seguinte forma:
</p>
<ol>
<li><b>Execução de Scripts Lua:</b>
O LuaCoffe utiliza o LuaJ, uma biblioteca que implementa a execução de código Lua dentro da JVM. Isso significa que os scripts Lua podem ser enviados e executados dentro do servidor Java, retornando os resultados de forma síncrona ou assíncrona, conforme a necessidade.
</li>
<li><b>Criação de APIs com Spring Boot: </b>
O servidor é estruturado com Spring Boot, que gerencia toda a parte de criação de APIs RESTful. O LuaCoffe facilita a criação de endpoints no servidor Java para expor as funcionalidades, como GET, POST, entre outros, e permite que esses endpoints executem funções definidas em scripts Lua.
</li>
<li><b>Integração de Módulos Java com Lua: </b>
A integração entre Lua e Java no LuaCoffe é feita através da função require(), que permite a utilização de bibliotecas Java diretamente nos scripts Lua. Isso possibilita que o desenvolvedor escreva um código Lua simples, mas com acesso a toda a potência das bibliotecas e frameworks Java.
</li>
<li><b>Facilitação da Integração das Bibliotecas Java para Lua</b>
A adição das bibliotecas Java no LuaCoffe também podem ser feita através da tabela de libs do L.C (LuaCoffe), que permite a utilização de bibliotecas Java diretamente nos scripts Lua. Você pode fazer isso através do método de acesso a libs rápidas <code>luaCoffe.libs</code>
</li>
</ol>
</br>
<h2>Configuração do LuaCoffe</h2>
<p>
Assim que clonar o repositório do LuaCoffe, você já pode ir para o desenvolvimento, ou pode configurar os paths de desenvolvimento no seu <code>application.yaml</code>.
</p>
<h3>Modificando os Path</h3>
<p>
Para modificar os Paths de desenvolvimento no L.C, accesse o arquivo de configuração do LuaCoffe na sequência: <code>src/main/resources/application.yaml</code>. Esse arquivo é responsável pela configuração da aplicação SpringBoot saiba maisa em: <a href="https://www.baeldung.com/spring-boot-yaml-vs-properties">Using application.yml vs application.properties in Spring Boot</a>.
Ao acessar a pasta você terá algo parecido com:
</br>
<img src="./assets/application_yaml_example.png" alt=""/>
</br>
<p>
Para conseguir mudar as pastas de desenvolvimento você só precisa modificar a o path em 
<code>
file: 
    path: {path de desenvolvimento}
</code>
Dentro de file também temos o atributo 
<code>
package:
    path: {path de scripts para serem importados}
</code>
 onde também é possivel modificar o path de scripts que serão usados como suporte para seus endpoits.
</p>
</p>
</br>
<h2>Criação de Endpoints</h2>
<p>
A criação de endpoints no LuaCoffe é extremamente simplificada, tudo o que você precisa e adicionar a tag de mapeamento em cima do seu script. Contudo, vale lembrar que seu script tem que estar dentro do path
</p>