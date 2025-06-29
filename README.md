# 💻  Java Technical Tests – Test 1 & Test 2

This repository contains two pure Java console-based projects developed as part of the **Skypay technical assessment**:

1. 💳 **Banking Service** – Test 1  
2. 🏨 **Hotel Reservation System** – Test 2

Both projects are built using **Java 17**, **Apache Maven**, and rely only on in-memory data structures (`ArrayList`) without any external storage frameworks.

---

# 💳 Test 1 – Banking Service

## 📝 Overview

This is a minimal **core banking system** built in pure Java.

It simulates:
- Depositing money
- Withdrawing money
- Printing transaction statements (latest to oldest)

### Key Features
- In-memory transaction storage
- Interface-driven design
- Exception handling for invalid operations
- Statement printed from latest to oldest using `Collections.reverse`

---

## 🚀 How to Run Test 1 (banking)

Navigate into the test 1 project directory and run:

### 1. Build the Project

```bash
mvn package
```
2. Run the Application
```bash
java -cp target/classes skypay.bank.Main
```
📌 Note: The Main class includes the sample transaction scenario provided in the test.

### 🔧 Technologies Used
Language: Java 17

Build Tool: Apache Maven

Dependencies: None (uses only core Java classes)

# 🏨 Test 2 – Hotel Reservation System
## 📝 Overview
This project is a minimal in-memory hotel booking system that supports:

Creating/updating rooms (type and price)

Creating/updating users (ID and balance)

Booking rooms for date ranges with:

Room availability validation

User balance validation

Snapshotting room/user data at booking time

Displaying all rooms, users, and bookings (latest to oldest)

## 🚀 How to Run Test 2
Navigate into the test 2 project directory and run:

1. Build the Project using maven
```bash
mvn clean compile
```
2. Run the Application
```bash
mvn exec:java
```
📌 Make sure Maven is installed on your system. The Main method runs several test cases, including success and failure scenarios.

### 🔧 Technologies Used
Language: Java 17

Build Tool: Apache Maven

Dependencies:

Jansi – for colored console output