# EncryptionApp
# Autocomplete
An [Encrption] , [Decryption] and [Autocomplete] using java [SpringBoot], [ElasticSearch] to demonstrate the solid principles 


Core Functiionality are-
1.`Encryption`
Encrypt the given string.

2.`Decryption`
Decrypting the given string.

3.`Autocomplete`
Search User based on phrase 

------------------------------------------------------------------------------------------------------------------------------------------------

`Solid Priciple Used`

Single Responsibility Principle:


Here Each Class Responsible for an single Isolated Responsiblity and  have responsibility over a single part of the functionality provided by the application, 
whole application is built on different layers and broken into smaller classes and modules . Features can be added easily for example if someone wants to use [HibernateSearch] insted of [ElasticSearch]  then insted of changing the whole application one need to just make couple of changes in [DAO] layer of application.


Interface Segregation Principle:

The application follows the above principle by not providing a single interface insted multiple interfaces are built so,that it should be only limited to client specific  for example application has basically two interfaces both have their own responsibility and independent of each other



Dependency Inversion Principle:

As principle states High-level modules/classes should not depend on low-level modules/classes. Both should depend upon abstractions
in this application when we see [CustomerService] the return type of method is an interface not a concrete class so that  is loosely coupled with return type of its implementation classes .

----------------------------------------------------------------------------------------------------------------------------------------------------

`Programming Principle`

Dry Principle:

All the behavior are implemented only once in the app. and used as a reusable component 















