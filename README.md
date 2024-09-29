# Task Tracker CLI
Task Tracker CLI is a simple command-line application designed to help organize and manage daily tasks efficiently.

It is a solution for the Task Tracker challenge from [roadmap.sh](https://roadmap.sh/projects/task-tracker).

## Features
- Add, Update, and Delete tasks.
- Mark the status of each task, such as Todo, In progress, or Done.
- List tasks either generally or filtered by their status.
- Task data is stored persistently to ensure that information is always available.

## How to Run It
1. Clone the GitHub repository:
   ```bash
   git clone https://github.com/KRFM202/task-tracker-cli.git
   cd task-tracker-cli
   ```
   
2. Compile and package the application:
   ```bash
   mvn clean package
   ```
   
3. Run the JAR file:
   ```bash
   java -jar task-tracker-cli-1.0.jar
   ```

## How to Use It
- **To add a new task**:
  ```bash
  add "Buy groceries"
  ```
  - You can also add a task with a status:
    
  ```bash
  add "Buy groceries" inprogress
  ```
  **Output**: `Task added successfully (ID: 1)`

- **To update a task** (you can also update the status optionally):
  ```bash
  update 1 "Buy groceries and cook dinner" done
  ```
  **Output**: `Task successfully modified (ID: 1)`

- **To delete a task**:
  ```bash
  delete 1
  ```
  **Output**: `Task deleted successfully (ID: 1)`

- **To mark the status of a task** (todo, inprogress, done):
  ```bash
  mark 1 done
  ```
  **Output**: `Task status successfully changed`

- **To list all tasks**:
  ```bash
  list
  ```
  **Output**: List of all tasks

  **Filter by status**:
  ```bash
  list todo
  list inprogress
  list done
  ```
  **Output**: Lists tasks with the selected status.

- **To exit the application**:
  ```bash
  exit
  ```
