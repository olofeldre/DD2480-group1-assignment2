# DD2480-group1-assignment2

## Statement of contributions

**Olof:**
Olof has set up the build system, and contributed in several parts of the back end of the server. He has set up the webhook, and has done code to extract data from the webhook. He has also co-written the code that sends emails, and pushing data to the database. 

**Caroline:** Together with Anton, Caroline set up the Firebase backend and Firestore database that is used to display information on past builds on a website. They wrote code for fetching information in the database so that it could be displayed on the website. She also contributed in writing code for the CI server that clones and compiles the repo (this was also done together with Anton). 

**Robin:** Robin has contributed with implementing the frontend that displays the content of the Firestore database. Furthermore, Robin together with Joakim implemented the first version of the email notification system.

**Anton:** Anton has contributed with setting up the Firebase backend and Firestore database that is used to display information on past builds on a website (together with Caroline), cloning the repo from the CI server (together with Caroline), compiling the repo from the CI server (together with Caroline), running tests from the CI server (together with Caroline), triggering the server to send notification email when receiving a post request from the webhook (together with Olof) and updating the database from the backend with build results.

**Joakim:** Furthermore, Joakim together with Robin implemented the first version of the email notification system. Joakim together with Olof updated the email system. Joakim together with Robin also implemented the updated frontend so each push event gets a separate url. Joakim together with Olof has set up the webhook, and has done code to extract data from the webhook. 

## Application
In this assignment we have implemented a continuous integration (CI) server. This server is hosted locally and tunneled through ngrok so that it can be accessed publicly. We have used Maven to handle dependencies. The CI server is triggered upon push events on the Github repo using a webhook. After being notified of the event, the CI server will first clone and compile the repo. Then it will run all tests present in the test directory of the repo (path: DD2480-group1-assignment2/CI/src/test/). After that the CI server will notify all repo contributors via email whether or not the cloning, compiling and testing was successful. Furthermore, information of every single push event (successful or not) is stored in a Firestore database. Finally, the content of the database is displayed on a website hosted on Firebase. That website displays a summary of all push events (builds) in a table. For each build present in the table, there is a unique URL linking to a page with more information of that particular build.

The target repository is cloned with JGit, the code is then compiled by creating a new process that executes the bash command "mvn clean compile assembly:single" in the newly cloned repository. The tests of the cloned repository are then run by creating a new process that executes the bash command "mvn test". We then look at the output to determine whether the tests passed or not.

## Testing
Throughout the development of this project, the different parts of the program have been thoroughly tested. Junit have been used to write unit tests. The Junit tests are located at /CI/src/test/java/group1. The file called ValidateRepoTest contains tests of the core functionality of the CI server. Those tests validate whether or not cloning, compilation and running tests are successful. In order to run those tests, we have created several branches with both valid and invalid code. The other test files contain tests of the email notification functionality and the functionality of interpreting data from the webhook.

Furthermore, we have also manually tested the functionality of the program. Some files have main functions that have been used to manually verify the expected behavior. We have also looked at the Firestore database from the Firestore web interface to manually verify that the database contains the correct data. Finally, we have also checked that the group members have received emails when expected.

## Running the program

First, make sure that you have Maven and Java installed on your computer.

In order to run the program you need to have a key for the Firebase admin SDK (generated from the Firebase console) in a file called firebase-adminsdk-serviceAccountKey.json in the CI/ folder. You will also need a file called password.txt containing the password of the CI server email account, in the /CI directory.

**To start CI server, use the following commands from the CI/ directory:**

    $ mvn clean compile assembly:single

    $ java -cp target/CI-1.0-SNAPSHOT-jar-with-dependencies.jar group1.Main

Then open a new terminal and run:

    $ ./ngrok http 8080

Then add the ngrok URL to the webhook of the repository to run the CI on.

**To only compile and run the tests in the repo locally, use the following commands from the CI/ directory:**

    $ mvn clean compile assembly:single

    $ mvn tests

## Git work flow

This is the Git work flow that we have followed during this project:

**Issues**
- For each task to be done, an issue is created. This includes both implementation of code, as well as more project related tasks such as updating the README or reorganizing the repository.
- Each issue should have an appropriate label, such as *enhancement*, *refactor*, or *documentation*.
- After having decided which group member that should be responsible for a specific task, that group member should be assigned to to that task on GitHub.

**Branches**
- The default branch is called *main*.
- When individually working on an issue, a new branch is created from the main branch. After a branch has been merged with the main branch, and when it is no longer needed, it should be deleted.
- When creating a new branch, it should be named as follows:
 - issueX-\<description>
 - For example, a valid branch name would be: *issue4-LIC4*


**Commits and commit messages**
- After having completed the work of an issue, the changes should be committed to the local working branch.
- The commit message should start with one of the following words: *feature*, *fix*, *refactor*, *doc*. That should be followed by the issue number and the issue name on the same line. This should be followed by a more detailed description on a new line. For example, the following is a valid commit message:

      feature #4 issue4-LIC4

      This implements the LIC4 rule as well as some tests.
- After committing, the changes should be pushed to the local working branch. Then, a pull request should be made.

**Pull requests**
- After having pushed the changes to a local working branch, a pull request to merge the changes to the main branch should be made. The pull request is done on GitHub.
- The pull request message should start with "*Closes #X*", where X is an issue number. This makes sure that the corresponding issue is closed when the pull request is merged to the main branch. The pull request message should also explain which changes that has been implemented.
- Before merging any changes to the main branch, one or several group members has to approve of the changes. In order to be able to approve a pull request, the reviewer must both look at the code and successfully run the tests of the code locally. The changes can only be approved if the code seems to solve the correct problem and passes the tests. If the reviewer has some comments on the proposed changes, the person who wrote the code is responsible for updating the code and then request a new review. It is very important that the review is done by another person than the one who did the pull request.
- If a merge conflict occurs, the person who wrote the code is responsible for fixing it. This applies even if a merge conflict appears a while after the pull request was made (e.g. if it was not present when the pull request was first made).


