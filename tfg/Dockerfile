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

# ————— Etapa 2: runtime con JRE 21 + Docker CLI + Compose plugin v2 —————
FROM eclipse-temurin:21-jre
WORKDIR /app

# 1) Instala Docker CLI y curl
RUN apt-get update \
 && apt-get install -y --no-install-recommends \
      docker.io \
      curl \
 && rm -rf /var/lib/apt/lists/*

# 2) Descarga Docker Compose v2 como plugin
RUN mkdir -p /usr/local/lib/docker/cli-plugins \
 && curl -SL https://github.com/docker/compose/releases/download/v2.17.3/docker-compose-linux-x86_64 \
    -o /usr/local/lib/docker/cli-plugins/docker-compose \
 && chmod +x /usr/local/lib/docker/cli-plugins/docker-compose

# 3) Copia tu JAR compilado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081 8443
ENTRYPOINT ["java","-jar","app.jar"]
