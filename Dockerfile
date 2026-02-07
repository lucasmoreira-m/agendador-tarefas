# Estágio de Build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copia apenas os arquivos de configuração primeiro (otimiza cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Dá permissão de execução e baixa as dependências
RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# Copia o código fonte e faz o build
COPY src src
RUN ./gradlew build -x test --no-daemon

# Estágio Final
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]