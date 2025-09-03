# LogParser

A simple log parser that for now only parses Spring Boot log files.  

This repository contains my first Java project.  
It was created purely as a learning exercise to explore how Java works in practice,
not as a production-ready tool.

## What I Learned
- Setting up and structuring a Java project (packages, source folders, naming conventions)
- Working with command line arguments and validating input
- Reading and processing files
- Using exceptions to handle errors (custom exceptions, checked vs. unchecked)
- Using enums for log levels
- Writing small utility classes (`FileReader`, `CliParser`)
- Basic regex handling in Java

## Limitations
- Only supports Spring Boot log format
- Not optimized for performance
- Not intended for real-world usage

## Installation

### Build
This project uses [Maven](https://maven.apache.org/). From the project root, run:

```
mvn clean package
```

This will compile the code and package it as a JAR under `target/`, for example:

```
target/log-parser-0.1.0-SNAPSHOT.jar
```

### Run
Run the program with `java -jar`:

```
java -jar target/log-parser-0.1.0-SNAPSHOT.jar \
  --file samples/spring-boot.log \
  --level ERROR
```

#### Multiple files and levels
You can provide multiple log files and levels:

```
java -jar target/log-parser-0.1.0-SNAPSHOT.jar \
  --file samples/spring-boot.log \
  --file samples/other.log \
  --level ERROR \
  --level WARN
```

## Possible Future Improvements
- Support for multiple log formats (Apache, JSON logs, etc.)
- Better CLI handling with a library like Picocli
- Filter logs by level, date, or thread

## License
This project is licensed under the MIT License.