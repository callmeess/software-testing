# Scruber

## Description

`PII-scruber` is a Java 21 Spring Boot project that provides text scrubbing utilities for privacy-sensitive content.

Current scrubbing features:
- Email masking (replaces detected emails with `[EMAIL_HIDDEN]`)
- Digit masking (replaces digits with `X`)
- Combined mode (email masking followed by digit masking)

The core logic is implemented under `src/main/java/com/PrivayChat/scruber` and validated with unit tests under `src/test/java/com/PrivayChat/scruber`.

## Prerequisites

- Java 21
- Maven (or use the included Maven Wrapper)

## How To Run

From this folder (`a1/scruber`), use one of the following:

### Using Maven Wrapper (recommended)

```bash
./mvnw spring-boot:run
```

### Using system Maven

```bash
mvn spring-boot:run
```

## How To Test

From this folder (`a1/scruber`), run:

### Using Maven Wrapper (recommended)

```bash
./mvnw test
```

### Using system Maven

```bash
mvn test
```

## Useful Commands

Build the project:

```bash
./mvnw clean package
```
