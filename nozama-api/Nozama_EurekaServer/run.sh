#!/bin/bash
mvn clean install
#-Dmaven.test.skip=true
cd target
targetFile="$(ls | grep '.jar$')"
cd ..
echo "Starting /target/"${targetFile}
java -DNOZAMA_CERT='/Users/balamt/Workspace/java_ws/nozama/nozama-cert/cert/CA' -jar $PWD'/target/'${targetFile}

