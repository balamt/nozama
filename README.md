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

# Ports Used in Nozama

|    Service/Applications       |     Port      |
|-------------------------------|---------------|
|   Nozama_EurekaServer         |   8761        |
|   Nozama_ApiGareway           |   8080        |
|   Nozama_UserService          |   8081        |
|   Nozama_ProductService       |   8082        |
|   Nozama_ShipmentService      |   8083        |
|   Nozama_OrderService         |   8084        |
|   Nozama_DeliveryScheduler**  |   8085        |
|   Nozama_WallerService        |   8086        |
|   Nozama_OrderInfoUpdater**   |   8087        |
|   Nozama_CartService          |   7072        |
|   Nozama_CheckoutService      |               |
|   Nozama_PaymentService       |               |

<sup>Note: Make sure you are having these ports available in your machine to run the services without docker</sup>

