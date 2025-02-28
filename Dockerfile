FROM mcr.microsoft.com/playwright/java:v1.49.0-noble

WORKDIR /e2eTests

COPY . /e2eTests/

RUN mvn -B install -D skipTests --no-transfer-progress

#ENTRYPOINT [ "/bin/bash", "-c","mvn test" ]