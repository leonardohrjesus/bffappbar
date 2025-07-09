# Etapa de build
FROM gradle:8.7.0-jdk21 AS build
WORKDIR /app

# Copia arquivos do projeto
COPY . .

# Gera o JAR da aplicação
RUN gradle bootJar --no-daemon

# Etapa de runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
