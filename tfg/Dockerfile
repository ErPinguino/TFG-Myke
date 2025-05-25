# ————— Etapa 1: compilación con Maven —————
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

ENV MAVEN_OPTS="-Dfile.encoding=UTF-8" \
    LANG=C.UTF-8

COPY pom.xml mvnw ./
COPY .mvn .mvn

# instala tu JAR local de Cubiomes
COPY libs/cubiomes-java-0.0.6.jar /tmp/
RUN mvn install:install-file \
      -Dfile=/tmp/cubiomes-java-0.0.6.jar \
      -DgroupId=com.rasmusantons \
      -DartifactId=cubiomes-java \
      -Dversion=0.0.6 \
      -Dpackaging=jar \
      -DgeneratePom=true

RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B
