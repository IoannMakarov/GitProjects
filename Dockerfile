# Use the official OpenJDK image as the base image
FROM openjdk:21-jdk-slim

# Install Maven and Git
RUN apt-get update && apt-get install -y maven git

# Set the working directory in the container
WORKDIR /app

# Clone the repository from GitHub
RUN git clone https://github.com/IoannMakarov/GitProjects.git .

# Change to the directory containing the pom.xml file
WORKDIR /app/Springwork

# Build the project using Maven
RUN mvn clean package


# Expose the port the app runs on
EXPOSE 8081

# Run the JAR file with spring.config.location pointing to application.yaml
ENTRYPOINT ["java", "-jar", "/app/Springwork/target/Springwork-1.0-SNAPSHOT.jar"]
