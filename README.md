# HopeConnect

![HopeConnect Logo](image.png)

HopeConnect is the RESTful API backend meant to assist orphan children in Gaza to connect with potential donors, sponsors, volunteers and verified orphanages. It allows users to aid orphans through sponsorships, donations under various areas like general assistance, education, medical, and volunteer registration while maintaining credibility and real time monitoring. The system has controlled access to the levels of role, tracing of donations, support campaigns in case of emergencies, and linkage with other APIs for improved precision calculative data and cooperative information. Designed for scalability and security, HopeConnect aims to facilitate the precision of humanitarian efforts and have a lasting impact.


## Table of Contents
- [Core Features](#core-features)
- [Technologies Used](#technologies-used-)
- [Getting Started](#-getting-started)
- [Roles](#roles)
- [API Documentation](#api-doc)
- [Demo](#demo)
- [Contact](#contact)


## 🚀 Core Features

### 🧒 Orphan Profile Management  
Maintain detailed profiles for each orphan, including:
- Name  
- Age  
- Education status  
- Health condition  

### 🤝 Sponsorship and Donation System  
Support orphans through:
- Monthly sponsorships  
- One-time donations  
- Targeted aid  

Donation categories:
- 🏠 General Needs  
- 📚 Educational Support  
- 🏥 Medical Assistance  
- 🚨 Emergency Relief  

Includes secure online payments and transparent donation tracking.

### 🙋‍♂️ Volunteer and Service Matching  
Volunteers register their skills (e.g., teaching, healthcare, mentorship), and the system matches them with orphanage needs based on expertise and availability.

### 🔍 Transparency and Trust Mechanisms  
- Donor dashboards with detailed reports  
- NGO verification for legitimacy  
- Donor reviews and ratings  

### 🚨 Emergency Response System  
- Create urgent campaigns for crises  
- Notify users via email and in-app alerts  

### 📦 Shipment Tracking and Logistic Requests  
Track donation shipments in real-time using:
- Shipment status  
- Location updates  
- Linked logistics requests  

### 🔐 User Roles and Permissions  
Role-based access for:
- Admins  
- Donors  
- Sponsors  
- Volunteers  

Includes secure authentication and authorization.

### 🛡️ Secure and Scalable Architecture  
- High-level data privacy  
- Scalable infrastructure for growing operations  

### 📘 API Documentation and Testing  
- REST/GraphQL APIs documented with Postman  
- Unit and integration testing supported  

### 🌐 External API Integration  
- **Stripe**: Secure credit card donations  
- **Email Alerts**: Send urgent news to all users automatically



## Technologies Used 💡

- <span style="color:#b07219">**Java ☕**</span>: Powerful and popular programming language  
- <span style="color:#6db33f">**Spring Boot 🌱**</span>: Framework for building web apps  
- <span style="color:#00758f">**MySQL 🐬**</span>: Trusted relational database  
- <span style="color:#ff6c37">**Postman 📬**</span>: Tool to test APIs effortlessly  
- <span style="color:#f9a825">**Lombok ✨**</span>: Library to reduce boilerplate code  


## 🚀 Getting Started

1- First, clone the project repository from GitHub using the following command:

-git clone https://github.com/SamyaHamed/HopeConnect2.git

2- Make sure Maven is installed on your system. Then, install the necessary dependencies by running the following command in your terminal:
mvn clean install

3- Create The Database:
Make sure MySQL is installed and running on your local machine.

4- Create a new database for the project:
CREATE DATABASE advanced_software;

5- Run The Application:
mvn spring-boot:run


## 👥 Roles

### 👮‍♂️ Admin  
Users who manage and oversee the platform operations.

### 👤 User  
Regular users of the system with specific roles:

- 🤝 **Volunteer**  
  People who offer their time to help.

- 🏢 **Organization**  
  Groups that coordinate activities or services.

- 🏠 **Orphanage**  
  Institutions caring for orphaned children.

- 🎗️ **Sponsor**  
  Individuals or entities providing financial support.

- 🎁 **Donor**  
  Users who donate resources or items.



## 📘 API Documentation

Our API is comprehensively documented and available via Postman, offering a detailed reference for all endpoints. Once the backend is deployed, you will be able to access the latest API documentation which includes:

- Clear descriptions of each endpoint  
- Request parameters and expected formats  
- Response structures and status codes  
- Practical examples to facilitate easy integration
    
You can view the documentation [here](https://682e40392ee7d0f07dfebe11--visionary-cendol-fa6b86.netlify.app).


This documentation ensures developers can efficiently understand and interact with the API.


## 📸 Demo

See HopeConnect in action!  
Check out our quick demo to explore all the amazing features and how the platform works seamlessly.  

🚀 [Watch the Demo](https://drive.google.com/file/d/1YPcV_HiQ5-5ESNJDKPBrx5s3jetYlD5c/view?usp=share_link)


## 📱 Contact

If you have any questions or want to get in touch, feel free to reach out to us:

- [**Shahd Almasri**](mailto:shahd.227.almasri@gmail.com)  
- [**Samia Asmar**](mailto:asmarsamia2003@gmail.com)  
- [**Eman Abd Alaziz**](mailto:abdalazizeman9224@gmail.com)  
- [**Samya Hamed**](mailto:samyahamed22@gmail.com)





