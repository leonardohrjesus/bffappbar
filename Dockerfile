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

# --- CORREÇÃO PARA CONEXÃO EXTERNA (SUPABASE) ---
# Força o Java a usar IPv4 para evitar o erro "Network is unreachable" no Railway
ENV JAVA_TOOL_OPTIONS="-Djava.net.preferIPv4Stack=true"
# -----------------------------------------------

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]