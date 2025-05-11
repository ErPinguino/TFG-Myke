# Dockerfile

# —————— Etapa 1: build con Maven (Java 21) ——————
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"
ENV LANG=C.UTF-8

# 1) Cache de dependencias de tu POM
COPY pom.xml mvnw ./
COPY .mvn .mvn

# 2) Instala tu JAR local de Cubiomes en repo Maven
COPY libs/cubiomes-java-0.0.6.jar /tmp/
RUN mvn install:install-file \
      "-Dfile=/tmp/cubiomes-java-0.0.6.jar" \
      "-DgroupId=com.rasmusantons" \
      "-DartifactId=cubiomes-java" \
      "-Dversion=0.0.6" \
      "-Dpackaging=jar" \
      "-DgeneratePom=true"

# 3) Baja el resto de deps
RUN mvn dependency:go-offline -B

# 4) Compila y empaqueta
COPY src ./src
RUN mvn clean package -DskipTests -B

# —————— Etapa 2: runtime con JRE 21 ——————
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
EXPOSE 8443
ENTRYPOINT ["java","-jar","app.jar"]
