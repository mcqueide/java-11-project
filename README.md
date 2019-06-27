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

# Como executar o projeto
1. Para executar o projeto é necessário que o container do mysql esteja rodando, para isso basta ter o docker instalado
na sua máquina e rodar o seguinte comando na raiz do projeto, `docker-compose up`. Se preferir pode executar o mesmo
comando com o argumento `-d` no final, `docker-compose up -d`, para o terminal não ficar preso a execução do container 
e acompanhar o log de acomponhar o log de inicialização do container com o comando 
`docker logs mysql_arq_dev_java_11_project`.

1. Agora basta executar a classe Aplicação como qualquer outra classe com método main na sua IDE preferida, ou executar
o comando `mvn spring-boot:run` na pasta raiz do projeto.

# Observações
1. As configurações de banco estão presentes no arquivo 
`java-11-project/principal/src/main/resources/application.properties`, nele há 2 spring.datasource.url um comentado e 
o outro não, um é para máquinas com o docker toolbox que roda o docker dentro de uma VM no virtual box e o outro é 
para o sistemas operacionais que rodam o docker nativamente.