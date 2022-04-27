# Points to follow while commiting the files

* Commit should have prefixed with your name first 4 letters followed by version number and a dot and followed by the date in DDMMYY and hyphen and then your commit message.
    * Example : **"BALA01.020419-Commit Message"**
    * BALA - First 4 letters
    * 01 - Version Number
    * . - Dot
    * 020419 - Date in DDMMYY Format
    * \- Hyphen to split the message from the above identifier
    * Commit Message - Your commit message
* Code Standards
    * Proper casing
    * Proper Naming
    * Junit Should be covered with at most 80-90% code coverage (Use Mockito or Powermockito)
    * Exception handling with Custom Exception
    * Use LOGGERs instead of System.out.print
    * Add Java Doc or comments for the required place.
    * Update the Readme.md when required
    * Use branch name appropriately (Proper naming for the branch name) - we can discuss on the naming convension.

--------------------------------------------------------------

# Prerequisite(s)

- JDK16+ 
- Maven
- nodejs, npm, react for UI
- Lombok plugin, SonarLint plugins
- Docker if you are using Windows/Linux (Required for learning Docker)
- Git 
- import CA.pem into the windows certificate "Trust CA"
- Host File Entry in Local Development machine
```
127.0.0.1 nozama.in sd.nozama.in apigw.nozama.in user.nozama.in auth.nozama.in product.nozama.in zipkin.nozama.in grafana.nozama.in order.nozama.in cart.nozama.in shop.nozama.in address.nozama.in config.nozama.in

```
<sup>Note: Make sure you are having these ports available in your machine to run the services without docker</sup>

