FROM openjdk:11

#RUN apt-get update && apt-get install -y maven

RUN mkdir /project

COPY ./principal/target/principal-1.0-SNAPSHOT.jar /project/principal-1.0-SNAPSHOT.jar

#RUN  cd /project && mvn clean package

#Descomentar a linha abaixo, caso NÃO vá usar o modo debug:
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/project/principal/target/principal-1.0-SNAPSHOT.jar"]

## docker-compose -f /home/cesardraw/projects/arqdev/java-11-project/docker-compose.yml up -d --build
