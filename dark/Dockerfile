# Используйте образ OpenJDK 19
FROM openjdk:19-jdk-alpine

# Установите рабочую директорию внутри контейнера
WORKDIR /app

# Скопируйте JAR-файл и файлы ресурсов в контейнер
COPY target/dark-0.0.1-SNAPSHOT.jar /app/dark.jar
COPY src/main/resources/application.properties /app/application.properties

# Открыть порт 8080
EXPOSE 8080

# Указать команду для запуска приложения
CMD ["java", "-jar", "dark.jar"]