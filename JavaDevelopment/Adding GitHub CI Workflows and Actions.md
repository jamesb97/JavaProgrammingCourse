# Adding GitHub CI Workflows and Actions Lab

## Overview

In this lab, will implement automated checks using GitHub Actions, a popular CI/CD tool built into GitHub. These actions will automatically lint and compile code whenever you push or submit a pull request. This ensures code quality, helps catch syntax issues early, and prepares the app for production.

Will set up four GitHub Action workflows to:

- Lint HTML, CSS, and JavaScript files in the frontend.
- Lint Java code in Spring Boot back end using Checkstyle.
- Compile the backend with Maven to check for syntax or dependency issues.
- Lint Dockerfile for best practices and security issues.

These checks run automatically and are visible from the Actions tab in the repository.

## Step 0: Clone the repository

Clone the repository in the lab environment if not done already.

1. Open the terminal using the `Terminal` -> `New Terminal` menu.
2. Use Git to clone the repository

`git clone https://github.com/{git-username}/java-database-capstone`

Replace the `{git-username}` with your GitHub username.

## Step 1: Lint HTML, CSS, and JavaScript

Will create a GitHub Actions workflow to automatically check the quality of the front-end code, HTML, CSS, and JavaScript every time a developer pushes code or submits a pull request.

1. Create a new file named `.github/workflows/lint-frontend.yml`. This will define the CI job for linting front-end files.

Open the `lint-frontend.yml` file.

2. Use these hints to create the action code.

- Need to set up Node.js in the GitHub runner, since the linting tools (`htmlhint`, `stylehint`, `eslint`) are require Node to run.
- Use `npm install -g` to install these linters globally.
- The relevant front-end files are located in:
- HTML: `app/src/main/resources/static/assets/pages//*.html`
- CSS: `app/src/main/resources/static/assets/css//*.css`
- JS: `app/src/main/resources/static/assets/js//*.js`

- Use glob patterns (`/*.ext`) to match all files recursively.
- It's okay to allow the job to continue on warnings for now - can suppress build-breaking behavior with `|| true`.
- Trigger the workflow on both `push` and `pull_request` events.

### Solution

Here is a complete solution:

```yml
name: Lint Frontend
on: [push, pull_request]
jobs:
  lint-frontend:
    runs-on: ubuntu-latest
    name: Lint HTML, CSS, and JS
    steps:
      - uses: actions/checkout@v3
      - name: Set up Node.js
        uses: actions/setup-node@v4
        with:
          node-version: "18"
      - name: Install linters
        run: |
          npm install -g htmlhint stylelint eslint
      - name: Lint HTML
        run: htmlhint app/src/main/resources/static/assets/pages//*.html || true
      - name: Lint CSS
        run: stylelint "app/src/main/resources/static/assets/css//*.css" || true
      - name: Lint JS
        run: eslint app/src/main/resources/static/assets/js//*.js || true
```

## Step 2: Lint Java code using Checkstyle

This workflow runs checkstyle on the back-end Java code to catch formatting and style violations early in the development process.

1. Create a file named `.github/workflows/lint-backend.yml`. This will define the CI job for linting back-end files.

Open the `lint-backend.yml` file.

2. Use these hints to create the action code.

- Create a new workflow file named `.github/workflows/lint-backend.yml`.
- Use `actions/setup-java` to install and configure Java 17 in the GitHub Actions runner.
- Download the Checkstyle tool from GitHub using `curl` this is a standalone `.jar` file.
- Use the default `google_checks.xml` ruleset for now (can customize it later).
- Target the Java source folder: `app/src/main/java/com/project/back_end`
- Checkstyle will exit with non-zero status on warnings or errors. Add `|| true` if you want to avoid breaking the build for now.
- Again, trigger this job on both `push` and `pull_request`.

### Solution

Here is a complete solution:

```yml
name: Lint Java Backend
on: [push, pull_request]
jobs:
  lint-java:
    runs-on: ubuntu-latest
    name: Checkstyle Java Linting
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          distribution: "temurin"
          java-version: "17"
      - name: Download Checkstyle
        run: curl -L -o checkstyle.jar https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.12.1/checkstyle-10.12.1-all.jar
      - name: Run Checkstyle
        run: |
          java -jar checkstyle.jar -c /google_checks.xml app/src/main/java/com/project/back_end || true
```

## Step 3: Compile the Java back end with Maven

In this step, will create a GitHub Actions workflow that compiles the back end using Maven. This ensures that the project builds correctly and all dependencies resolve without issues.

1. Create a new file named `.github/workflows/compile-backend.yml`. This will define the CI job for compiling the code. If this step fails, developers will need to fix the code before submitting to GitHub.

Open the `compile-backend.yml` file.

2. Use the following hints to create the action code.

- Use `actions/setup-java` to install and configure Java 17 in the GitHub Actions runner.
- Your Maven project is inside the `app/` folder. Ensure that you change into the app directory before running the Maven commands.
- Use `mvn clean compile` to compile the project and resolve dependencies using the system's Maven.
- Should trigger the workflow on both `push` and `pull_request`.

### Solution

Here is a complete solution:

```yml
name: Compile Java Backend
on: [push, pull_request]
jobs:
  compile-backend:
    runs-on: ubuntu-latest
    name: Compile Backend Code
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          distribution: "temurin"
          java-version: "17"
      - name: Compile with Maven
        run: |
          cd app
          mvn clean compile
```

## Step 4: Lint the Dockerfile

This step sets up a workflow to analyze `Dockerfile` using **Hadolint**, a linter that helps catch syntax issues and suggests Docker best practices.

1. Create a new file named `.github/workflows/lint-docker.yml`. This will define the CI job for compiling the code. If this step fails, developers will need to fix the code before submitting to GitHub.

Open up the `lint-docker.yml` file.

2. Use the following hints to create the action code.

- Use the official **Hadolint GitHub Action**.
- The Dockerfile is located at `app/Dockerfile`.
- This workflow should also run on `push` and `pull_request`.
- The `with:` section of the `hadolint/hadolint-action` lets you specify the path to the Dockerfile you want to lint.

### Solution

Here is a complete solution:

```yml
name: Lint Dockerfiles
on: [push, pull_request]
jobs:
  dockerlint:
    runs-on: ubuntu-latest
    name: Lint Dockerfiles
    steps:
      - uses: actions/checkout@v3
      - name: Run hadolint
        uses: hadolint/hadolint-action@v3.1.0
        with:
          dockerfile: ./app/Dockerfile
```

### Conclusion

Continuous Integration (CI) is a foundational part of modern software development. In this lab, have implemented workflows using GitHub Actions to lint and compile the code. These pipelines help ensure that the application remains clean, functional, and production-ready-even as the team grows or code changes become more frequent.

By setting up these CI checks, have adopted a mindset of **quality-first development**. Linting ensures the code follows style guidelines, compiling caches build issues early, and automation keeps everything consistent. These are the same practices used by professional teams delivering real-world software.

You are not just writing code, you are building resilient, maintainable systems like an industry engineer.

### Next Steps

- Push all changes to the GitHub.
- Go to the **Actions** tab in the repository to verify that each workflow runs successfully.
- Review the logs and check for linting or compile errors.
- Fix any issues shown in the CI logs and re-commit the changes.
- In the next lab, will package the application using Docker and learn how to run Spring Boot app in a containerized environment.
