# List of completed tasks
A program for processing payment documents and providing financial statements has been created.
Access to the program is provided by login and password.
Two-factor authentication has been implemented using OTP and QR code using Microsoft Authenticator applications.
The program receives the path to the folder with financial documents, reads information from the documents and compiles statistics.
Three types of documents: invoices, orders, checks.
All documents are in TXT format.
Each type of document has its own structure and its own name template.
Files are processed only for the current year.
Technical documentation of the program has been made: solution diagram, class diagram, sequence diagram.
Various checks have been implemented.
Logs are saved to a separate file.
Logs are separated: for storing general information and for storing error information.
Upon completion of the program, all invalid files are moved to a separate folder.
The final statistics are uploaded to a separate file. The statistics file should be in Amazon S3 cloud storage.
The settings for the program, such as keys for S3 and session lifetime, are located in the properties file.
