# 🏧  ATM-System

This project simulates an ATM (Automated Teller Machine). It allows users to:
- Validate a PIN code
- Withdraw money
- Check their account balance

---

## 📌 Main features 
- **🔐 PIN code validation** 
- **💵Cash Withdrawal** 
- **📊 Balance Inquiry**

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

```  
ATM-System/
├── src/                        
│   ├── co/
│   │  └── simplon/
│   │       └── atm_system/ 
│   │           ├── business                           # Business logic for the ATM
│   │           │   ├── ATMService.java                # Logic for managing the ATM and transactions
│   │           │   ├── ATMStatusService.java          # Manages account identification and PIN validation
│   │           │   ├── AuthentificationService.java   # Provides account-related information
│   │           │   ├── Transactions.java              # Manages banking operations
│   │           │   ├── BankService.java               # Manages the logic for the bank
│   │           │   ├── CSVService.java                # Logic for importing and exporting data to/from CSV
│   │           │   ├── CustumerAccountService.java    # Handles transactions in greater detail
│   │           │   ├── TransactionService.java        # Handles transactions in greater detail
│   │           ├── config
│   │           │   ├── Configuration.java             # Manages the application's configuration                
│   │           ├── data/ # Manages data files
│   │           │    ├── CSVWriter.java                # Writes data to CSV files
│   │           │    ├── CSVReader.java                # Reads data from CSV files
│   │           │    ├── Accounts.csv                  # CSV file to store account information
│   │           │    ├── ATMFunds.csv                  # CSV file to store information related to ATM funds
│   │           │    ├── Cards.csv                     # CSV file to store card information
│   │           ├── model                              # Contains the entities for the ATM's domain logic
│   │           │   ├── Bank.java                      # Entity representing the bank
│   │           │   ├── Card.java                      # Entity representing a bank card
│   │           │   ├── CustomerAccount.java           # Entity representing a customer account
│   │           │   ├── Transaction.java               # Entity representing a transaction 
│   │           ├── ui/                                # User interface
│   │           │     └── ATMUI.java                   # Manages user interaction via the terminal
│   │           └── Main.java                          # Entry point of the application
├── resources/
│    └── config.properties                             # Contains configuration properties like file paths
└── README.md                                          # Project documentation
```

---

## ‼️ Troubleshooting
If you encounter any issues, make sure that:
- ✅ Java 8 or higher is installed
- ✅ You're running the commands from the project root directory

---


## 📖 More Details
For in-depth information on design choices, diagrams, and technical explanations, check out my [GitHub Wiki](https://github.com/Sabrina-Dubois/ATM-System/wiki) 📚
