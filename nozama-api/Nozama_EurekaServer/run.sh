#!/bin/bash
mvn clean install
#-Dmaven.test.skip=true
cd target
targetFile="$(ls | grep '.jar$')"
cd ..
echo "Starting /target/"${targetFile}
java -jar $PWD'/target/'${targetFile}

