<img align="right" src="skilloLogo.png" alt="Skilo Academy Logo" />


<div align="center">

# Test automation framework
</div>

## TABLE OF CONTENTS

[I. INTRODUCTION](#i-introduction)

[II. QA TEAM MEMBERS](#ii-qa-team-members)

[III. HOW TO RUN AUTOMATIONS](#v-how-to-run-automations)



## I. INTRODUCTION

__I-skillo__ (http://training.skillo-bg.com:4300/posts/all) is a web application that allows you to connect and communicate with other people. It enables you to:

- connect with other Iskilo platform user by managing connection requests;
- manage your profile information;
- form discussions by creating, commenting and liking posts;
- get a feed of the newest/most relevant posts from people in the network.


## II. QA TEAM MEMBERS

### QA Team:
Diana Ivancheva <br/>
Nikolay Nikolov - supervision

## III. HOW TO RUN AUTOMATIONS

### __1. Pre-requisites for running automations__

To be able to run the project on localhost and run the test automations, the following programs need to be present on your machine:

- Windows 11 operating system / MAC OS
- OPEN JDK 23
- IntelliJ IDEA Ultimate Edition 2023.1.3 or later
- MAVEN Command Line
- Chrome latest version
- Selenium WebDriver
- Apache Maven 3.8.0 or later

### __2. Running the automations__

__NB:__ Prior to running the project and automations make sure your chrome driver executables are properly provided in the project

1. Clone the repo on your computer.
2. Run run_automation.bat.

### __2.1. How to run UI tests automation individually__

1. Run run_ui_automation.bat file at the root location of the repo.
2. Go to terminal and execute mvn -clean test 
 
