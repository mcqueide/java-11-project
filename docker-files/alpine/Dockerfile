FROM alpine:latest as alpine_container

ADD https://download.java.net/java/early_access/alpine/16/binaries/openjdk-13-ea+16_linux-x64-musl_bin.tar.gz /opt/jdk/
RUN tar -xzvf /opt/jdk/openjdk-13-ea+16_linux-x64-musl_bin.tar.gz -C /opt/jdk/

RUN /opt/jdk/jdk-13/bin/jlink \
     --module-path /opt/jdk/jdk-13/jmods/ \
     --compress=2 \
     --add-modules jdk.jfr,jdk.management.agent,java.base,java.logging,java.xml,jdk.unsupported,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,jdk.management,jdk.jdwp.agent \
     --no-header-files \
     --no-man-pages \
     --verbose \
     --strip-debug \
     --output /jlinked



FROM alpine:latest
COPY --from=alpine_container /jlinked /opt/jdk/

ENV JAVA_HOME=/opt/jdk/
ENV PATH="$PATH:$JAVA_HOME/bin"

RUN java --version

RUN mkdir /project

COPY ./principal/target/principal-1.0-SNAPSHOT.jar /project/principal-1.0-SNAPSHOT.jar
