# JavaFX App Creation and Packaging Guide

This guide provides step-by-step instructions on how to create and package a JavaFX application.

The executable files for admin and coursemanagement are located in `jar_apps` folder.

## Prerequisites

Before you begin, make sure you have the following installed on your system:

- Java Development Kit (JDK)
- JavaFX SDK

## Step 1: Set Up Your Development Environment

1. Install the JDK and set up the `JAVA_HOME` environment variable.
2. Download the JavaFX SDK and extract it to a directory of your choice.
3. Set up the `PATH_TO_FX` environment variable, pointing to the `lib` directory inside the JavaFX SDK.

## Step 2: Create a JavaFX Project

1. Open your preferred Integrated Development Environment (IDE).
2. Create a new Java project.
3. Add the JavaFX libraries to your project's build path.

## Step 3: Write Your JavaFX Code

1. Create a new Java class that extends the `javafx.application.Application` class.
2. Override the `start` method and write your JavaFX code inside it.

## Step 4: Build and Package Your JavaFX App

1. Configure the database setting to your host machine, modify in two file: db.properties and META-INF/persistence.xml. 
2. In `gradle.build` add the requirements for JavaFX, JavaFX packaging (shadowJar)
```gradle.build
plugins {
	id 'java'
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.1.0'
    id 'org.beryx.jlink' version '2.24.4'
    id 'com.github.johnrengelman.shadow' version '7.1.2'
}
javafx {
    version = "21"
    modules = [ 'javafx.controls', 'javafx.fxml']
}
shadowJar {
    archiveClassifier.set('')
    manifest {
        attributes 'Main-Class': 'edu.duke.ece651.project.team5.admin.Launcher'
    }
    configurations = [project.configurations.runtimeClasspath]
}
```

3. Export `.jar` file:
    In the admin or coursemanagement directory, build the jar file using this Command Line:
    ```zsh
    gradle clean shadowJar
    ```

4. Locate the export .jar file in /build/libs/.
5. This jar can be moved and executed directly, or from command line interface:
    ```zsh
    java -jar filename.jar
    ```

## Conclusion

The JavaFX application is created and packaged. Refer to the official documentation for more advanced topics and customization options.
