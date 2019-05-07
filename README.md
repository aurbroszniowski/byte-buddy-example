# byte-buddy-example

First create the observer jar by doing a 

```
mvn clean package
```

you should have a file :

```
observer/target/observer-1.0-SNAPSHOT.jar
```

then run the class

```
io.rainfall.Example
```

with the option:

```
-javaagent:$PROJECT_PATH/observer/target/observer-1.0-SNAPSHOT.jar
```

where $PROJECT_PATH is the path where you cloned this project

it will instrument the `InstrumentedClass` and output a line `Currently Instrumenting` during the first five times, then will uninstrument the class.
 