FROM maven:3.8.5-openjdk-17

WORKDIR /backend

COPY . .

EXPOSE 8080

RUN mvn install -DskipTests

CMD mvn spring-boot:run