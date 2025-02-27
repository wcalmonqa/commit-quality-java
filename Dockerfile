FROM mcr.microsoft.com/playwright/java:v1.49.0-noble

WORKDIR /e2eTests

COPY . /e2eTests/

RUN mvn -B install -D skipTests --no-transfer-progress

RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install --with-deps"

ENTRYPOINT [ "/bin/bash", "-c","mvn test" ]