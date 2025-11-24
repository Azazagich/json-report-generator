# Movie Statistics Analyzer 🎬

A console-based Java utility designed to parse large datasets of movie information from JSON files. 
The application processes data using multithreading to ensure high performance and generates an XML statistical report based on a user-specified attribute.

## Tech Stack
* **Java 21**
* **Jackson** (Core, Databind, XML) for JSON parsing and XML generation.
* **Maven** for build management.
* **JUnit** for unit testing.

## How to Run

### Build the project
Navigate to the project root and run:

```bash
mvn clean package
```
### Execution
The application requires two mandatory command-line arguments:
 * **pathToFile**: The path to the folder (directory) containing your source .json files.
 * **attribute**:  The name of the JSON field you want to analyze.

### Supported attributes:
* **title**
* **director_name**
* **release_year**
* **minutes_duration**
* **comments**

### Command Syntax:

```bash
java -jar target/your-jar-name.jar <path_to_data_folder> <attribute_name>
```

## Data Format Examples
### Input(json)
The application expects an array of objects in JSON files. Example file structure:
```json
[
  {
    "title": "Inception",
    "director_name": "Christopher Nolan",
    "release_year": 2010,
    "description": "A thief who steals corporate secrets through the use of dream-sharing technology...",
    "minutes_duration": 148,
    "comments": ["Masterpiece", "Great visual effects"]
  },
  {
    "title": "Interstellar",
    "director_name": "Christopher Nolan",
    "release_year": 2014,
    "description": "A team of explorers travel through a wormhole in space...",
    "minutes_duration": 169,
    "comments": ["Emotional", "Sci-fi classic"]
  }
]
```
### Output(XML)
The result is saved in the statistics/ folder as statistics_by_{attribute}.xml. Example output for director_name:
```xml
<statistics>
  <item>
    <value>Christopher Nolan</value>
    <count>2</count>
  </item>
  <item>
    <value>Steven Spielberg</value>
    <count>1</count>
  </item>
</statistics>
```
## Performance Testing results
the application automatically runs a benchmark to compare processing time with different thread counts. 
Check the console output for results:
```Plaintext
INFO: Performance testing:
INFO: Threads: 1 | Time: 13 ms
INFO: Threads: 2 | Time: 4 ms
INFO: Threads: 4 | Time: 5 ms
INFO: Threads: 8 | Time: 0 ms
```

