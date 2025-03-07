# 🔹 ATM-System

This project simulates an ATM (Automated Teller Machine). It allows users to:
- Validate a PIN code
- Withdraw money
- Check their account balance

---

## 📌 Main features 
- **PIN code validation** 🔐
- **Cash Withdrawal** 💸
- **Balance Inquiry** 💰

---

## 🛠 Installation and operation

### ✅ Prerequisites 
- **Java 8 or higher** installed

### 📥 Installation steps 
- 1️⃣ **Clone the repository**:
    ```zsh
    git clone https://github.com/Sabrina-Dubois/ATM-System.git
    ```

- 2️⃣ **Navigate into the project directory**:
    ```zsh
    cd ATM-System
    ```

- 3️⃣ **Compile the project**:
    This will compile all Java files into `.class` files (bytecode).
    ```zsh
    javac -d out $(find src/main/java -name "*.java")
    ```

- 4️⃣ **Run the project**:
    Start the ATM system by running the `Main` class.
    ```zsh
    java -cp out co.simplon.atm_system.Main
    ```

---

## 📂 Project structure 
- `src/` : Contains the Java source code.
- `out/` : Output folder for compiled `.class` files.

---
## Troubleshooting
If you encounter any issues, make sure that:
- ✅ Java 8 or higher is installed
- ✅ You're running the commands from the project root directory

---


## 📖 More Details
For in-depth information on design choices, diagrams, and technical explanations, check out my [GitHub Wiki](https://github.com/Sabrina-Dubois/ATM-System/wiki) 📚
