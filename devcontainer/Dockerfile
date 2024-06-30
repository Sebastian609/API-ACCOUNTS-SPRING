# Usar una imagen base de OpenJDK
FROM openjdk:11-jdk-slim

# Crear un directorio de trabajo
WORKDIR /app

# Copiar los archivos de configuración de Gradle y los scripts de Gradle
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY gradlew /app/
COPY gradlew.bat /app/

# Instalar las dependencias de Gradle
RUN ./gradlew build || return 0

# Copiar el código fuente del proyecto
COPY src /app/src

# Construir la aplicación
RUN ./gradlew build

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 3000

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "build/libs/api-accounts-spring.jar"]

# Etiquetas de metadatos
LABEL Name=apiaccountsspring Version=0.0.1
