Barn Management System
----------------------

*by Richard Lewan*

A sample Play application that allows a user to enter Barns and add Animals to their Barns. Connects to a node.js webservice that makes a valuation appraisal of the user's collection of Barns. Also uses Jasmine testing framework to unit test Javascript code.

You must setup the DB and run the node.js http server in order to use the app.
JUnit also assumes the node http server is running on 127.0.0.1:9055

Set up the MySQL DB:
------------------------
 - CREATE DATABASE barns;
 - CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
 - CREATE USER 'test'@'%' IDENTIFIED BY 'test';
 - GRANT ALL PRIVILEGES ON barns.* TO 'test'@'localhost' WITH GRANT OPTION;
 - GRANT ALL PRIVILEGES ON barns.* TO 'test'@'%' WITH GRANT OPTION;

Running the node.js http server
-------------------------------
1. Make sure you have node.js installed on your computer.
 - $ brew install node
    (OR download from https://nodejs.org/download/)
2. Run the barnHttp.js from the {project.dir}/node folder
 - $ node barnHttp.js



Javascript Unit Testing:

Install & Run Jasmine (for .spec.js test cases)
-----------------------------------------
1. Install
 - $ sudo npm install -g jasmine
2. Install jasmine-node in your home dir
 - $ cd
 - $ sudo npm install jasmine-node
3. Run the tests
 - $ ~/node_modules/jasmine-node/bin/jasmine-node --verbose --junitreport {project.dir}/node/*.spec.js
