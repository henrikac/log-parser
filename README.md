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
This project was developed with IntelliJ IDEA, but you can also build it from the command line:

```
javac -d out $(find src -name "*.java")
```

### Run
Run the program by pointing to the main class:

```
java -cp out com.github.henrikac.logparser.App --file samples/spring-boot.log --level ERROR
```

#### Multiple files and levels
You can provide multiple log files and levels:

```
java -cp out com.github.henrikac.logparser.App \
  --file samples/spring-boot.log \
  --file samples/other.log \
  --level ERROR \
  --level WARN
```

### Optional: Create a JAR
Package as a JAR if you prefer:

```
jar --create --file logparser.jar -C out .
```

Run it like this:

```
java -jar logparser.jar --file samples/spring-boot.log --level INFO
```

## Possible Future Improvements
- Support for multiple log formats (Apache, JSON logs, etc.)
- Better CLI handling with a library like Picocli
- Filter logs by level, date, or thread

## License
This project is licensed under the MIT License.