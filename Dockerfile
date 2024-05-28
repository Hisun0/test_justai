FROM openjdk:21-jdk

RUN microdnf install findutils

COPY . /app
WORKDIR /app

RUN ./gradlew installDist

ENTRYPOINT ["./build/install/test_JustAI/bin/test_JustAI"]