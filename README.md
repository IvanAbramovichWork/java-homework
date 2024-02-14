Версия Java: openjdk-21 \
Maven : 3.9.5 \
Зависимости:
```xml
<dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.2.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-runner</artifactId>
            <version>1.2.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-assembly-plugin</artifactId>
            <version>2.3</version>
            <type>maven-plugin</type>
        </dependency>

    </dependencies>
```
## Build:
```shell
cd TzShift
mvn clean compile assembly:single
cp target/TzShift-1.0-SNAPSHOT-jar-with-dependencies.jar tz.jar
```

## Test:
```shell
cp src/test/resources/test_input.txt .
java -cp tz.jar  org/example/Main -f -p sample- test_input.txt
```
