# 🏦 CORBA-Bank

A distributed banking system built using **CORBA (Common Object Request Broker Architecture)** in Java.  
Implements client-server communication via IDL interfaces for core banking operations.

> 📚 BCD 1 — Practical Project | University of Vocational Technology

---

## 📌 What is CORBA?

**CORBA (Common Object Request Broker Architecture)** is a standard defined by the Object Management Group (OMG) that enables communication between software written in different languages running on different computers. In this project, CORBA is used to connect the **ATM Client** to the **Bank Server** over a network using Java IDL (ORB).

---

## ✨ Features

- ✅ Check Account Balance
- ✅ Deposit Money
- ✅ Withdraw Money
- ✅ Insufficient Balance Exception Handling
- ✅ Server-side transaction logging
- ✅ CORBA ORB-based client-server communication

---

## 🛠️ Tech Stack

| Technology | Details |
|---|---|
| Language | Java |
| CORBA Implementation | Java IDL (built-in JDK) |
| JDK Version | JDK 1.8 (Java 8) |
| Build Tool | Maven |
| Interface Definition | IDL (Interface Definition Language) |

---

## 📁 Project Structure

```
corba-bank/
├── src/
│   └── main/
│       └── java/
│           └── com/hnys/bcd/
│               ├── client/
│               │   └── ATMClient.java        # Client-side ATM interface
│               ├── server/
│               │   ├── BankServer.java       # CORBA Server
│               │   └── AccountImpl.java      # Bank operations implementation
│               └── BankingApp/               # IDL generated stubs & skeletons
│                   ├── Account.java
│                   ├── _AccountStub.java
│                   ├── InsufficientBalance.java
│                   └── InsufficientBalanceHelper.java
├── Bank.idl                                  # IDL Interface Definition
└── pom.xml
```

---

## ⚙️ Setup & Run

### Prerequisites
- Java JDK 1.8
- Maven (optional)
- Windows / Linux / macOS

---

### Step 1 — Set Java Path (Windows PowerShell)
```powershell
$env:Path="C:\Program Files\Java\jdk1.8.0_111\bin;$env:Path"
```

---

### Step 2 — Compile the IDL File
```bash
idlj -fall Bank.idl
```

---

### Step 3 — Compile All Java Files
```bash
cd src/main/java
javac com/hnys/bcd/server/*.java com/hnys/bcd/client/*.java BankingApp/*.java
```

---

### Step 4 — Start the CORBA Name Server (ORB)
```bash
start orbd -ORBInitialPort 1050
```

---

### Step 5 — Start the Bank Server
```bash
cd src/main/java
java com/hnys/bcd/server/BankServer -ORBInitialPort 1050
```

---

### Step 6 — Run the ATM Client
```bash
cd src/main/java
java com/hnys/bcd/client/ATMClient -ORBInitialPort 1050
```

---

## 💻 Sample Usage

```
Enter Your Account No:
ACC001

1. Check Balance  | 2. Deposit | 3. Withdrawal | 4. Exit
1
Current Balance: 5000.0

2
Enter Amount to Deposit:
8500
Amount Deposited : 8500.0
Current Balance  : 13500.0

3
Enter Amount to Withdraw:
10000
Amount Withdrawn : 10000.0
Current Balance  : 3500.0
```

---

## 🚨 Exception Handling

| Exception | Trigger | Handling |
|---|---|---|
| `InsufficientBalance` | Withdraw > Current Balance | CORBA User Exception thrown & caught |
| `AccountNotFoundException` | Invalid Account Number | Exception thrown from server |

---

## 📄 IDL Interface (Bank.idl)

```idl
module BankingApp {
    exception InsufficientBalance { string message; };

    interface Account {
        double getBalance(in string accNo);
        void deposit(in string accNo, in double amount);
        void withdraw(in string accNo, in double amount)
            raises (InsufficientBalance);
    };
};
```

---

## 👨‍💻 Author

**Yohan Silva**  
GitHub: [@Yohan3128](https://github.com/Yohan3128)

---

## 📃 License

This project is for **educational purposes** only — BCD Practical Assignment.
