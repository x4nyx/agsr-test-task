#!/bin/bash

mvn -f pom.xml clean package
java -jar ./target/agsrdemoconsole-1.0-SNAPSHOT.jar
