##################### DOCKERFILE diagnosis_patient ####################

#################### STAGE 1 : Construction du projet ##########################

# Definition de l'image de base
FROM maven AS build

# Definition du fichier de travail dans le container
WORKDIR /diagnosis

# Copie du pom et du code source dans le fichier de travail
COPY pom.xml /diagnosis
COPY src /diagnosis/src

# Copier le fichier application.properties dans l'image
COPY src/main/resources/application.properties /diagnosis

# Package de l'appli (sans execution des tests - DskipTests)
RUN mvn clean package -DskipTests



#################### STAGE 2 : Execution de l'appli ####################
FROM openjdk:21-jdk-slim

WORKDIR /diagnosis

# Copie du fichier packagé (jar) vers le fichier de travail
COPY --from=build /diagnosis/target/diagnosis-0.0.1-SNAPSHOT.jar diagnosis.jar

# Exposition du port d'accès à l'appli
EXPOSE 8081

#RUN de l'appli (par defaut au demarrage du container)
ENTRYPOINT ["java", "-Dspring.config.location=classpath:/application.properties", "-jar", "diagnosis.jar"]






