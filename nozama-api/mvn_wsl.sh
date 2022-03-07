#!/bin/bash
mvn -s $HOME/.m2/settings-wsl.xml -DskipTests=true clean install
