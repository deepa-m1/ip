# cupcake.ui.Cupcake Project 🧁

This is a project template for a greenfield Java project.

The chatbot is named Cupcake. Cupcake helps you keep track of tasks (to-dos, deadlines, and events).

## Setting up in IntelliJ 🧑‍💻

Prerequisites: JDK 17, and the latest version of IntelliJ IDEA.

1. Open IntelliJ (if you are not in the welcome screen, click File > Close Project to close any open project first).

2. Open the project in IntelliJ:

    - Click Open.

    - Select the project directory, and click OK.

    - If there are any further prompts, accept the defaults.

3. Configure the project to use JDK 17 (not other versions) as explained here
.
4. In the same dialog, set the Project language level field to the SDK default option.

## Running Cupcake 🏃🏃‍♀️

1. In IntelliJ, expand the ip module in the Project Explorer.

2. Right-click the module and select Run 'ip [run]', or press the Run button on the top menu.

3. In the console, type any message — Cupcake will greet you.

4. After that, you can enter commands to manage your tasks (to-dos, deadlines, or events).

⚠️ Note: On startup you can type any message to activate Cupcake.

Example Interaction:

Here’s what using Cupcake looks like in the console:

> hi
Hello! I'm Cupcake :) Welcome back! What can I do for you?

> todo read book

>Okay I have added: [T][ ] read book. 
> You now have 5 tasks! Let's go!!!         
.***************************
Anything else I may help you with?


Cupcake can remember your previous tasks and help you manage new ones as you go. 🎂✨

### Supported Commands:

Cupcake supports the following commands:

`todo`
`deadline`

`event`
`list`

`mark`
`unmark`

`delete`
`find`

### ⚠️ Important Project Structure:

Keep the **src/main/java** folder as the root folder for Java files.
Do not rename these folders or move Java files outside of this path, as tools like Gradle expect Java files to be there.