# Containerize your Java Application with Docker Lab

## Overview

In this lab, package a simple Java application using Docker. Explore multi-stage builds, layer caching strategies, and best practices for creating an efficient Docker container.

### Objectives

At the end of this lab:

- Package and optimize a Java application using Docker
- Reduce image size using multi-stage builds
- Improve build caching with layer optimization
- Speed up builds using dependency caching strategies

### Scenario

A DevOps engineer optimizing a Java microservice for production deployment. The current Docker container image is 800MB, takes 5 minutes to build, and occassionally crashes due to memory issues.

The goal is to create a production-ready Docker container that is smaller, builds faster, and runs more reliably.

## Verify the environment and command-line tools

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. Verify that `java` is installed.
   `java --version`

3. Verify that Maven is installed.
   `mvn --version`

## Step 1: Create the application

1. Open a new terminal window from the menu: `Terminal > New Terminal`.

2. Navigate to the project folder if not already inside with the following command:
   `cd /home/project`

3. If the repository has not been cloned yet, run the following command to fetch the starter code:
   `git clone https://github.com/ibm-developer-skills-network/ceeqw-containerize-java-application dockerlab`

4. Move into the cloned project directory to begin working on the lab:
   `cd dockerlab`

5. List the contents of the directory to verify the project files:
   `ls`

## Step 2: Check the pom.xml file

Let's review the contents of the `pom.xml` file

This is a `Maven POM (Project Object Model)` file, a configuration file that tells Maven how to build and manage the Java project.

### Here are the key highlights

- **Modern Java Spring Boot**: Uses current LTS versions with container support, JVM optimizations, and native compilation features compatible with Docker's container-aware JVM settings.
- **JAR layering for Docker optimization**: The `<layers><enabled>true</enabled></layers>` setting splits the JAR into layers (dependencies, Spring Boot loader, application code), allowing Docker to cache unchanged layers and reduce rebuild times.
- **Built-in health monitoring**: Includes Spring Boot Actuator, which provides `/actuator/health` endpoints compatible with Docker's `HEALTHCHECK` instruction for container monitoring.
- **Predictable build output**: Produces `order-service-1.0.0.jar` with consistent naming, UTF-8 encoding, and proper plugin versions to support reliable Docker builds across environments.

## Step 3: Review the OrderService class

Let's review the contents of the `OrderService` class

### What is this file?

- **Container-focused microservices**: A Spring Boot REST API that simulates order processing with in-memory storage using a thread-safe. `ConcurrentHashMap`. It is designed to demonstrate containerization concepts, not persistent data storage.
- **Memory monitoring endpoint**: The `/memory` endpoint exposes JVM memory metrics (heaps, total, used, free) in a readable format. This helps validate container memory limits and JVM tuning during lab exercises.
- **Built-in health check**: Provides a `/health` endpoint that returns service status.It integrates with Docker's `HEALTHCHECK` instruction and works well with container orchestration platforms like Kubernetes.

## Step 4: Run and analyze the problematic Dockerfile

Let's review the contents of the `Dockerfile`

This Dockerfile creates an oversized image by including the full Maven build environment in the final container. It copies all files at once, which limits layer caching, and runs the build inside the final image. It also runs as the root user and leaves behind unnecessary tools and source code.

### Issues with the current Dockerfile

- **Oversized production image**: Uses the full Maven build image (~600MB+) for runtime instead of a lightweight JRE, resulting in a bloated container that includes unnecessary build tools and dependencies.
- **Poor layer caching strategy**: Uses `COPY . /app`, which causes any file change, including documentation updates, to invalidate the build cache and trigger a full rebuild, including dependency downloads.
- **Build tools in production**: Runs the Maven build inside the final image, leaving behind build artifacts, cache, and source code, increasing image size and security risks.
- **Security and efficiency issues**: Runs the application as the root user and creates a monolithic image that builds slowly and is harder to deploy and maintain.

#### Build this dockerfile and analyze

`docker build -t problematic-service .`

This process may take some time. Once the image has been built, run

`docker images | grep problematic-service`

The image will be significantly large

## Step 5: Implement Multi-Stage Build with Layer Optimization

Let's review the contents of the `Dockerfile.optimized` file

### Improvements in the optimized Dockerfile

- **Multi-stage build separation**: Uses two separate stages: one for building with Maven and one for running the application. This approach reduces the final image size from around 600MB to about 150MB by excluding unnecessary build tools.
- **Improved layer caching**: Copies `pom.xml` separately before the source code. This allows Docker to cache dependency downloads when only the code changes, resulting in faster rebuilds.
- **Container-aware JVM configuration**: Includes JVM flags such as `- XX:+UseContainerSupport` and `-XX:MaxRAMPercentage=75.0`, which ensure that the application respects container memory limits and avoids crashes.
- **Minimal Alpine runtime**: Uses the `eclipse-temurin:17-jre-alpine` base image with only essential packages like `curl` for health checks. This keeps the container small and secure.

#### Build the optimized image

`docker build -f Dockerfile.optimized -t optimized-service .`

This process may take some time to complete.

`docker images | grep -E "(problematic|optimized)-service"`

The optimized image is much smaller now

## Step 6: Implement advanced layer caching with .dockerignore

This step improves caching using a `.dockerignore` file

Let's review the contents of the `.dockerignore` file

### Benefits of using .dockerignore

- **Faster build context transfer**: Excludes unnecessary files such as logs, `.git`, `target/`, and IDE settings from the build context. This reduces the size sent to the Docker daemon from about 500MB to 50MB, making builds start faster.
- **Improved layer cache efficiency**: Prevents unrelated file changes like README updates or git commits from invalidating Docker's cache. This ensures that `COPY . /app` only triggers a rebuild when the actual source code changes.
- **Enhanced security**: Excludes sensitive files such as `.env`, credentials, and personal logs from being copied into the image. This reduces security risks in production environments.
- **JAR layer optimization**: When combined with Spring Boot's JAR layering, it separates the application into distinct Docker layers. This allows only the smallest layer (around 1MB) to rebuild during minor code changes instead of the full JAR (about 50MB).

#### Review Dockerfile.layered for final optimization

Let's review the contents of the `Dockerfile.layered` file

#### Highlights of the layered Dockerfile

- **Layered dependency handling**: Separates dependency resolution (`mvn dependency:go-offline`) from code compilation. Dependencies only rebuild when `pom.xml` changes, not when the source code is updated.
- **Improved production security**: Runs the application as a non-root user (`appuser`) with proper file ownership. Uses `dumb-init` to handle signals correctly and follow container security best practices.
- **JVM tuning for containers**: Configures the JVM with options like `UseContainerSupport`, `MaxRAMPercentage=75.0`, and `UseG1GC` to optimize performance and memory usage inside containers.
- **Lightweight runtime base**: Uses a minimal Alpine Linux base (about 15MB) with only essential packages such as `curl` and `dumb-init`, producing a secure and fast-starting container under 200MB.

#### Build the layered image

`docker build -f Dockerfile.layered -t layered-service .`

#### Check the image

`docker images | grep "layered"`

#### Run the command again

```
# Running this command again will be quicker as no changes have been made
docker images | grep "layered"
```

##### Example impact

- Without `.dockerignore`: 500MB build context (includes target/, .git/, /logs/)
- With `.dockerignore`: 50MB build context (only source code)
- Result: 10x faster uploads to Docker daemon

## Challenge

### Add health checks

**Challenge**: In this challenge, add a health check to the optimized container so that it integrates well with container orchestration platforms.

#### The task

1. Add a `HEALTHCHECK` instruction to the Dockerfile.optimized
2. Create a simple endpoint that returns application status
3. Test whether the health check works as expected

#### Hints

1. **Health check syntax**: Use `HEALTHCHECK --interval=30s --timeout=3s CMD ...`
2. **Health endpoint**: Already have `/health` endpoint in the application
3. **Testing**: Use `docker ps` to see the health status, or `docker inspect` for details
4. **Curl command**: Health check should use `curl -f` to fail on HTTP errors

##### Key points about health checks

- `--start-period=30s`: Gives the app time to start before health checks begin
- `--interval=30s`: Checks health every 30 seconds
- `--timeout=3s`: Each health check must complete within 3 seconds
- `--retries=3`: Mark unhealthy after 3 consecutive failures
- `curl -f`: Fails on HTTP error codes (4xx, 5xx)

## Conclusion

Have successfully

- Learned how to optimize Docker images for size
- Used **Multi-stage builds** reduced final image size
- Implemented **Layer optimization** improved build cache efficiency
- Optimized image through **Build optimization** dependency caching strategies
