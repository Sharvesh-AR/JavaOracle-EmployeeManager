# Employee Management Web App (Java + Oracle + JSP + Servlet)

This is a simple Java EE web application developed using NetBeans. The project enables basic CRUD operations (Create, Read, Update, Delete) for employee records in an Oracle database. The application uses HTML forms, JSP pages, and Java Servlets.

---

## 💻 Features

- **Insert** a new employee (ID, name, salary) into the database
- **Delete** an employee record using their ID
- **Update** an employee's salary
- **Find** an employee's details by ID

---

## 🌐 Technologies Used

- HTML + CSS
- JSP (JavaServer Pages)
- Servlets (Java)
- JDBC (Java Database Connectivity)
- Oracle Database
- NetBeans IDE

---

## 🧾 SQL Commands (Run in Oracle SQL Developer)

```sql
CREATE TABLE emp (
  eno INT PRIMARY KEY,
  ename VARCHAR2(20),
  esal INT
);

INSERT INTO emp VALUES (1001, 'heisenberg', 20000);
INSERT INTO emp VALUES (1002, 'James McGill', 30000);

SELECT * FROM emp;


