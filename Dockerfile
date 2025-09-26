# =================================================================
# STAGE 1: Build - A "fábrica" que compila nosso código
# =================================================================
# Usamos uma imagem que já vem com Maven e JDK 21 para compilar nosso projeto
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia apenas o pom.xml primeiro. Isso aproveita o cache do Docker.
# Se as dependências não mudaram, o Docker não vai baixá-las de novo.
COPY pom.xml .

# Baixa todas as dependências do projeto
RUN mvn dependency:go-offline

# Copia todo o resto do código fonte
COPY src ./src

# Compila o projeto e empacota em um .jar. Pulamos os testes porque já
# rodaremos eles na nossa pipeline de CI no futuro.
RUN mvn clean package -DskipTests


# =================================================================
# STAGE 2: Run - O "produto final", enxuto e pronto para rodar
# =================================================================
# Usamos uma imagem base do Java 21 super leve, sem Maven ou outras ferramentas.
# 'slim' é otimizada para ter o menor tamanho possível.
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia APENAS o .jar que foi gerado no estágio 'builder' para dentro da nossa imagem final.
# Todo o código fonte e as ferramentas de build são descartados.
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot
EXPOSE 8080

# O comando que será executado quando o container iniciar.
# Ele simplesmente executa nosso arquivo .jar.
ENTRYPOINT ["java", "-jar", "app.jar"]