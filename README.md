visiblehand-cli
===============
This is a simple command line application which uses the [visiblehand-core](https://github.com/potash/visiblehand-core) library to connect to an email account, download receipts and calculate carbon emissions data.

To use you need Java (tested with OpenJDK 1.7) and Maven. First clone the repositories

    git clone https://github.com/potash/visiblehand-core.git
    git clone https://github.com/potash/visiblehand-cli.git
    
then build and install visiblehand-core

    cd visiblehand-core && mvn clean install -DskipTests
    
then build visiblehand-cli

    cd ../visiblehand-cli && mvn clean compile
    
and finally execute

    mvn exec:java
    
When prompted enter you username and password. The default mail store is gmail but can be configured using a mail.properties file (see [here](https://javamail.java.net/nonav/docs/api/javax/mail/Session.html#getInstance(java.util.Properties%29)). The program will search your Inbox for flight receipts and output information about fuel consumption and carbon emissions.
