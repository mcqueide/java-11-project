# Java 11 Project

Esse projeto tem como objetivo montar uma arquitetura de projeto java utilizando a versão 11 juntamente com o novo 
sistema de módulos java (jigsaw), módulos maven e a stack do spring-boot.

# Stack do projeto
- Docker
- Java 11
- Jigsaw
- Maven
- Spring-boot
- Spring-data
- Flyway
- Dozer
- Swagger (SpringFox) com adaptações para funcionar em projetos Jigsaw + Spring

# Como executar o projeto
1. Para executar o projeto é necessário que o container do mysql e do java estejam rodando, para isso basta ter o docker instalado
na sua máquina e rodar os seguintes comandos na raiz do projeto: 

1.1 - Apenas na primeira vez, ou quando necessário fazer build da imagem alpine-java13:  
`docker build -f docker-files/alpine/Dockerfile` .

1.2 - Dentro da pasta docker-files: `docker-compose up`. Se preferir pode executar o mesmo comando com o argumento 
`-d` no final ( `docker-compose up -d` ), para o terminal não ficar preso a execução do container
e acompanhar o log. de inicialização do container.


2. Agora basta executar a classe Aplicação como qualquer outra classe com método main na sua IDE preferida, ou executar
o comando `mvn spring-boot:run` na pasta raiz do projeto.

3. Para rodar direto do IntelliJ e integrado com o Docker: `TODO - link do tutorial`

# Documentação Swagger automática:

Por padrão a integração do Swagger está mapeando todos os serviços (inclusive os do Spring).
Para documantar a api dos serviços da aplicação apenas, consultar documentação oficial do SpringFox: 
`https://springfox.github.io/springfox/docs/current/#springfox-configuration-and-demo-applications`

Url da API:
`http://localhost:8080/v2/api-docs`

Url da Documentação Swagger:
`http://localhost:8080/swagger-ui.html`



# Observações
1. As configurações de banco estão presentes no arquivo 
`java-11-project/principal/src/main/resources/application.properties`, nele há 2 spring.datasource.url um comentado e 
o outro não, um é para máquinas com o docker toolbox que roda o docker dentro de uma VM no virtual box e o outro é 
para o sistemas operacionais que rodam o docker nativamente.

1. Para adicionar um novo módulo na aplicação basta executar o comando na raiz do projeto:
```
mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
```