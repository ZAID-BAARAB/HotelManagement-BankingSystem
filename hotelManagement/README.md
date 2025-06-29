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



## 🔧 Technologies Used
- **Language:** Java 17

- **Build Tool:** Apache Maven

- **Dependencies:**

    - Jansi (for colored console output)

- Core Java libraries only; no external data stores or repositories

