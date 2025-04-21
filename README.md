# BDD-Java-Selenium-TestNG-OpenCV-framework


##  Overview

This is a modular and extensible automation testing framework built using:

- **Java** (Core language)
- **Selenium WebDriver** (Browser automation)
- **Cucumber (BDD)** (Behavior Driven Development)
- **TestNG** (Test execution and reporting)
- **OpenCV with Python** (Visual image comparison integration)

The framework supports:
- BDD-style test development with `.feature` files
- Cross-browser web testing using Selenium
- Image comparison using OpenCV (via Python)
- Easily extendable test utilities, custom drivers, and reusable components

---

##  Project Structure
```text
orange-hrm-test-automation/
│
├── .idea/
│
├── reports/
│   └── extent-report.html
│
├── resources/
│   ├── baselineSS/
│   ├── temporarySS/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.orangehrm/
│   │   ├── python/
│   │   └── resources/
│   │       ├── Drivers/
│   │       └── xml-files/
│
│   └── test/
│       ├── java/
│       │   └── com.orangehrm/
│       │       ├── hooks/
│       │       ├── runners/
│       │       └── stepdefinitions/
│       └── resources/
│           ├── features/
│           └── extent.properties
│
├── target/
│
├── .gitignore
├── pom.xml
└── README.md
