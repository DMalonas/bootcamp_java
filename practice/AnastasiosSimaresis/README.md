# JSON Matcher Project

## Table of Contents

- [Overview](#overview)
- [Specifications](#specifications)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Running the Project](#running-the-project)
- [Example](#example)
- [Troubleshooting](#troubleshooting)

## Overview

This project reads data from a JSON file (`sample.json`) and a text file (`sample.txt`). It matches each entry's value from the JSON file with the appropriate chunk of text from the text file. The matched results are then written to an output JSON file (`output.json`).


## Specifications

The specifications for the project are described in the `specification.txt` file as follows:


### specification.txt

Please consider the following scenario:
We have a file containing an array of two property JSON entries, index and value.
We also have a text file with text in it.
Each sentence in the text file corresponds to an entry's value in the JSON.
We want to match the text from each value to the appropriate chunk of text from the text file. The final result should look like Sample_final.json's.
Write the final result to an output.json file.


## Project Structure

```lua
exercise/
|-- src/
|   |-- main/
|       |-- java/
|           |-- JsonMatcher.java
|-- sample.json
|-- sample.txt
|-- output.json
|-- pom.xml (if using Maven)
|-- build.gradle (if using Gradle)
```

- **src/main/java/JsonMatcher.java**: Contains the logic to read the JSON and text files, match the entries, and write the output to `output.json`.
- **sample.json**: Contains an array of JSON entries with `index` and `value` properties.
- **sample.txt**: Contains sentences where each sentence corresponds to an entry's value in the JSON file.
- **output.json**: Will be generated as output containing the original JSON entries with an additional `matchedValue` property.
- **pom.xml**: Maven configuration file to manage dependencies and build the project.
- **build.gradle**: Gradle configuration file to manage dependencies and build the project.


## Dependencies

The project uses the Jackson library for JSON processing. Make sure to add the following dependency to your `pom.xml` if you're using Maven:

```xml
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.3</version>
    </dependency>
</dependencies>
```

Or to your build.gradle if you're using Gradle:

```groovy
dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.13.3'
}
```

## Running the Project

1. Ensure you have the `sample.json` and `sample.txt` files in the root directory.
2. Build the project using Maven or Gradle to download dependencies.
3. Run the `JsonMatcher` class to generate the `output.json` file with the matched results.

```sh
# Using Maven
mvn clean install
mvn exec:java -Dexec.mainClass="JsonMatcher"

# Using Gradle
gradle build
gradle run
```

The output will be written to output.json with the matched values from sample.txt.

## Example

Given `sample.json`:

```json
[
    {
        "index": 0,
        "value": "This is a J.P.M. report detailing the latest trends in global financial markets."
    },
    {
        "index": 1,
        "value": "To ensure we are allocating our resources effectively, we must consistently track and analyze our marketing campaigns' ROI."
    },
    {
        "index": 2,
        "value": "Thus we'll adjust our strategies based on these insights."
    },
    {
        "index": 3,
        "value": "U.S. market trends will also be compared to ensure global alignment."
    }
]
```

And `sample.txt`:

```
This is a JP Morgan report detailing the latest trends in global financial markets.2
To ensure we are allocating our resources effectively, we must consistently track and analyze our marketing campaigns' return of investment.
Thus we'll adjust our strate-gies based on these insights.
- United States of America market trends will also be compared to ensure global alignment.'3
```

The `output.json` will be:

```json
[
    {
        "index": 0,
        "value": "This is a J.P.M. report detailing the latest trends in global financial markets.",
        "matchedValue": "This is a JP Morgan report detailing the latest trends in global financial markets.2"
    },
    {
        "index": 1,
        "value": "To ensure we are allocating our resources effectively, we must consistently track and analyze our marketing campaigns' ROI.",
        "matchedValue": "To ensure we are allocating our resources effectively, we must consistently track and analyze our marketing campaigns' return of investment."
    },
    {
        "index": 2,
        "value": "Thus we'll adjust our strategies based on these insights.",
        "matchedValue": "Thus we'll adjust our strate-gies based on these insights."
    },
    {
        "index": 3,
        "value": "U.S. market trends will also be compared to ensure global alignment.",
        "matchedValue": "- United States of America market trends will also be compared to ensure global alignment.'3"
    }
]
```

## Troubleshooting

If you encounter any issues while running the project, consider the following troubleshooting tips:

- **Missing Dependencies**: Ensure that all dependencies are correctly specified in `pom.xml` or `build.gradle` and that your build tool has successfully downloaded them.

- **File Not Found**: Verify that `sample.json` and `sample.txt` exist in the root directory of your project. Double-check the file paths if the files are located in different directories.

- **JSON Parsing Errors**: Ensure that `sample.json` is properly formatted. Malformed JSON can lead to parsing errors. You can use JSON validators available online to check the syntax.

- **Text Matching Issues**: The text in `sample.txt` must be split into sentences or chunks that closely match the values in `sample.json`. Ensure there is a reasonable correspondence between the sentences in `sample.txt` and the entries in `sample.json`.

- **Encoding Issues**: If you experience encoding problems with your text files, ensure that both `sample.json` and `sample.txt` are saved in UTF-8 encoding.

- **Build Errors**: If you encounter build errors, make sure that your Java development environment is correctly configured and that there are no syntax or configuration issues in your build files (`pom.xml` or `build.gradle`).
