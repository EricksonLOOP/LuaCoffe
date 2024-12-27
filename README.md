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
<pre>
<code>
file: 
    path: {path de desenvolvimento}

// Dentro de file também temos o atributo 

    package:
        path: {path de scripts para serem importados}
</code>
</pre>
 onde também é possivel modificar o path de scripts que serão usados como suporte para seus endpoits.
 Esses atributos são responsáveis por dizer onde sem sua pasta <code>.../App/..</code> haverá a varredura pelo endpoint em questão.
</p>
</p>
</br>
<h2>Criação de Endpoints</h2>
<p>
A criação de endpoints no LuaCoffe é extremamente simplificada, tudo o que você precisa e adicionar é a tag de mapeamento em cima do seu script. Contudo, vale lembrar que seu script tem que estar dentro do path especificado dentro do seu application.yaml
</br>
<pre>
<code>
file: 
    path: {path de desenvolvimento}
    package:
        path: {path de scripts para serem importados}
</code>
</pre>
</br>
E então você pode adicionar a tag <code>luaCoffe.mapping("**método**/qualquer/rota/**nomeDoSeuArquivo**")</code>
</br>
Ex: <b>HelloWorld.lua</br>
<pre>
<code>
    luaCoffe.mapping(get/minha/rota/HelloWord)
    return {code = 200, response = "Hello, World!"}
</code>s
</pre>
</br>
Vale lembrar que sem a tag especificando a rota de chamada do seu arquivo, ele estará fechado para chamadas, sendo impossibilitado usar ele como Endpoint.
</p>
<h2>Trabalhando com vários scripts <code>import()</code></h2>
<p>Ao desenvolver aplicações web, nos deparamos com a necessidade de reutilizar código com frequência. O <code>import()</code> no LuaCoffe facilita essa tarefa ao permitir que múltiplos scripts Lua sejam utilizados em conjunto, promovendo a modularidade e reutilização de funções e lógicas em diferentes partes da aplicação.</p>

<p>Com o LuaCoffe, você pode importar qualquer script que esteja dentro do path configurado no <code>application.yaml</code>. Isso possibilita que um script possa acessar funções e variáveis de outro, mantendo o código organizado e modularizado. Ao invés de repetir trechos de código, você simplesmente os importa nos locais necessários.</p>

<h3>Exemplo de Importação de Scripts</h3>
<p>Suponha que você tenha um script <b>mathUtils.lua</b> com funções matemáticas que deseja reutilizar em várias partes da aplicação:</p>

<pre><code>-- mathUtils.lua
MathUtils = {}
MathUtils.__index = Mathtils
function MathUtils:soma(a, b)
    return a + b
end

function MathUtils:multiplicar(a, b)
    return a * b
end
return MathUtils
</code></pre>

<p>Agora, em outro script Lua, você pode importar <code>mathUtils.lua</code> e utilizar suas funções:</p>

<pre><code>-- main.lua
local mathUtils_import = import("mathUtils")
local mathUtils_Class = mathUtils_import();
local mathUtils_Instance = setmetatable({}, mathUtils_Class)

local resultadoSoma = mathUtils:soma(10, 20)
local resultadoMultiplicacao = mathUtils:multiplicar(5, 4)

return {
    code = 200,
    response = "Resultado: " .. resultadoSoma .. " e " .. resultadoMultiplicacao
}
</code></pre>

<p>Com isso, você consegue facilmente dividir a lógica da aplicação em diferentes arquivos e reutilizar essas funções em diversos endpoints, mantendo o código mais limpo e modular.</p>

<h2>Facilitando a Integração de Bibliotecas Externas</h2>
<p>O LuaCoffe também permite a integração de bibliotecas externas tanto em Lua quanto em Java. Para o lado Lua, basta que as bibliotecas estejam no diretório correto, conforme especificado no <code>application.yaml</code>, e você poderá importá-las diretamente nos seus scripts. Para bibliotecas Java, o framework fornece um mecanismo de acesso facilitado através da função <code>luaCoffe.libs</code>, permitindo que as funcionalidades Java sejam expostas e utilizadas diretamente nos scripts Lua.</p>

<h3>Exemplo de Integração com Bibliotecas Java</h3>
<p>Suponha que você queira utilizar a biblioteca <code>OkHttp</code> para realizar requisições HTTP a partir de um script Lua. No lado Java, a biblioteca já deve estar configurada no classpath, e então você pode utilizá-la da seguinte forma no LuaCoffe:</p>

<pre><code>-- httpClient.lua

function fazerRequisicao(url)
    local req = luaCoffe.lib.luaOkHttp.get(url)
    local response = req
    -- Retorna uma tabela lua com os valores do Json
    return luaCoffe.libs.luaJson.jsonToLua(req)
end
</code></pre>

<p>Esse exemplo demonstra como é possível usar a força das bibliotecas Java diretamente nos scripts Lua, aumentando o potencial do desenvolvimento e mantendo a simplicidade de Lua no nível da lógica de aplicação.</p>
