# Financial Statements Processing System  

## Project Description  

This project is a **Financial Document Processing and Financial Reporting Application**. It provides functionality for processing financial documents, such as invoices, orders, and checks, which can only be in `.txt` format. The program calculates key statistics from these documents and outputs the results both locally and to the AWS S3 cloud storage. In addition to financial document processing, the program includes secure access with login/password authentication and a two-factor authentication (TFA) mechanism using OTP codes and QR codes, compatible with applications like Google Authenticator.  

The program ensures thorough validation of input data, logging of both general information and errors, and clear separation of logs into separate files. It also performs cleanup by moving invalid files into a designated folder and securely stores keys and configuration in a properties file.  

---

## Features  

1. **Authentication**  
   - Login and password access.  
   - Two-factor authentication (TFA) using OTP and QR codes compatible with Google Authenticator.  

2. **File Processing**  
   - Reads and processes financial documents located in a specified folder.  
   - Supports three types of documents:  
       - **Invoices**  
       - **Orders**  
       - **Checks**  
   - The system processes only documents for the current year and ignores outdated files.  
   - Each file type has its own structure and filename template.  

3. **Logging**  
   - Logs both general information and errors into separate log files:  
       - `info.log`: For normal operations.  
       - `error.log`: For capturing issues and exceptions.  

4. **Statistics Calculation**  
   - Computes the following metrics and stores them in a local statistics file (`statistics.txt`):  
       - Total turnover for all invoices.  
       - Total turnover for all orders.  
       - Total turnover for all checks.  
   - Example content of `statistics.txt`:  
     ```text  
     Statistics:  
       - Total turnover for all invoices: 8912,13  
       - Total turnover for all orders: 13837,38  
       - Total turnover for all checks: 19638,94  
     ```  

5. **Error Handling**  
   - Invalid files are moved to a specific folder after validation.  

6. **Cloud Integration**  
   - Final statistics are uploaded to the Amazon S3 cloud storage.  

7. **Configuration Management**  
   - Application settings, such as AWS S3 keys and session lifespan, are stored in a `config.properties` file for easy customization.  

---

## Task Requirements  

### Task Objective  

The main goal is to create an application to process financial documents and provide financial reports. The application must support authentication with login and password, implement two-factor authentication (TFA) with OTP and QR codes, and process financial documents in `.txt` format while generating statistics based on their content. Files must be validated and only processed if they belong to the current year. Invalid files should be moved to a separate directory. Final results must be written to a file and uploaded to AWS S3 storage.  

---

### Technical Requirements  

1. **Core Functionalities**:  
   - User authentication with login/password.  
   - Provide TFA (two-factor authentication) using OTP codes and QR codes generated for Authenticator apps.  
   - Read input files, validate their structure, and process only valid files for the current year.  
   - Process files of the following types:  
       - **Invoices**  
       - **Orders**  
       - **Checks**  
     Each type has its own format and filename template.  

2. **File System**:  
   - Accept a folder path from the user and read files from it.  
   - Invalid files should be moved to a separate folder for error tracking.  

3. **Statistics**:  
   - Calculate and store the following in a statistics file:  
      - Total turnover for all invoices.  
      - Total turnover for all orders.  
      - Total turnover for all checks.  

4. **Logging**:  
   - Log the application's workflow and errors into separate log files (`info.log` and `error.log`).  

5. **AWS Integration**:  
   - Upload the final statistics file to AWS S3 storage.  

6. **Properties File**:  
   - Store all necessary configurations in `config.properties`, such as:  
     - AWS keys.  
     - Session timeout.  
     - Application settings for authentication.  

---

### Acceptance Criteria  

- The program must be fully operational.  
- Clean, readable, and maintainable code following naming conventions.  
- Include Javadoc comments for all service classes and methods.  
- Comments in English only.  
- A clear and concise README file.  
- The working code must reside in the `master` branch, and unused files (e.g., `out`, `target`) should not be included in the repository.  

---

## Verification Scenario  

1. Launch the program.  
2. The program prompts for credentials → Enter login and password.  
3. The program generates a QR code → Scan the QR code and retrieve an OTP password for program access.  
4. The program prompts for the folder path → Provide the absolute path to the folder containing documents.  
5. The program processes the documents, saves results to a file, and uploads the statistics file to AWS S3 storage.  
