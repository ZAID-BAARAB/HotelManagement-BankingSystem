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

## 💡 Design Questions (Bonus) – Answers

### 1️⃣ Is it recommended to put all functions inside the same service class?

Placing all functions inside a single `Service` class may be acceptable for small-scale tests or prototypes. However, **this approach is not recommended for larger or production-level systems**, as it leads to a _God class_ — a class that takes on too many responsibilities.

This violates the **Single Responsibility Principle (SRP)** of the SOLID design principles, which states that a class should have only one reason to change.

✅ **Recommended Alternative: Separate Responsibilities**

Break the responsibilities into dedicated services:
- `RoomService` – for managing room creation and updates
- `UserService` – for handling user creation and balance management
- `BookingService` – for booking logic and availability checks

This structure improves:
- **Maintainability** (code is easier to update)
- **Testability** (you can unit test components in isolation)
- **Scalability** (easier to extend in the future)

---

### 2️⃣ Why does `setRoom(...)` not affect previous bookings? What alternatives exist?

In this implementation, the method `setRoom(...)` creates or updates a room **without affecting existing bookings**. This design ensures that a booking maintains a snapshot of room details (like price and type) _at the time of booking_.

🔁 **Alternative Design Approaches:**
- **Immutable or Versioned Room Entities**:  
  Instead of modifying a room in-place, create a new version of the room when updates occur. Bookings reference the specific version they were made against.

- **Separate Pricing Entity (e.g., `RoomRate`)**:  
  Decouple pricing from the room itself. Bookings store the rate they were made with, while the room entity can evolve independently.

🚫 **What to Avoid:**  
Avoid updating room type or pricing directly in a way that retroactively affects previous bookings. This could corrupt historical data, billing records, and user expectations.

✅ **Recommended Strategy:**  
Maintain historical consistency by ensuring bookings remain tied to the original room data at the time of booking. You can achieve this by:
- Using immutable room objects
- Storing a snapshot of room data inside the `Booking` object

This is essential for **auditability, billing accuracy, and business correctness**.

---


### 🔧 Technologies Used
Language: Java 17

Build Tool: Apache Maven

Dependencies:

Jansi – for colored console output