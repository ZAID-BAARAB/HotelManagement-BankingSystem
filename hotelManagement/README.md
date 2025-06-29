# 🏨 Hotel Reservation System – Test 2

## 📝 Project Overview

This project implements a minimal **in-memory hotel reservation system** using **pure Java (no external frameworks or repositories)** as per the specifications provided in **Technical Test 2**.

It supports:
- Defining and updating **rooms** (type & nightly price)
- Defining and updating **users** (ID & balance)
- **Booking** a room for a date range (with availability, balance checks, and snapshotting of room/user details)
- Printing all **rooms**, **users**, and **bookings** (latest → oldest)

All data is stored in-memory using `ArrayList`. This is a console-based application designed to demonstrate core Java concepts, OOP, and simple date handling.

---

## 🚀 How to Run

1. **Package the project**  
   navigate to the root directory (where `pom.xml` is located).

   ```bash
   cd HotelManagement-BankingSystem\hotelManagement
   ```
   run :
   ```bash
   mvn clean compile
   ```
   ```bash
    mvn exec:java
    ```
**Note :**  please not the Maven sould be installed in ypur system to run mvn commands
## 🔧 Technologies Used
- **Language:** Java 17

- **Build Tool:** Apache Maven

- **Dependencies:**

    - Jansi (for colored console output)

- Core Java libraries only; no external data stores or repositories

