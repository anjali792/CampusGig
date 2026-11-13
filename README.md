# 🎓 CampusGig — Hyper-Local Micro-Internship & Real Industry Project Marketplace

<p align="center">
  <a href="https://anjali792.github.io/CampusGig/"><img src="https://img.shields.io/badge/🚀_Live_Demo-Click_to_Open-brightgreen?style=for-the-badge&logo=googlechrome&logoColor=white" /></a>
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.5.16-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Security-6.0-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Tailwind_CSS-3.4-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white" />
  <img src="https://img.shields.io/badge/Currency-INR_(₹)-10B981?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" />
</p>

A production-ready, full-stack web application designed to connect ambitious college developers and students with **real top-tier tech internships** (Razorpay, Swiggy, Postman, Zepto, CRED, Sarvam AI / Unstop) as well as **hyper-local campus freelance projects** (local businesses, startups, and college tech clubs) with stipends paid in Indian Rupees (₹).

---

## ✨ Key Features & Capabilities

### 🏢 1. Real Industry Internships + Official Direct Application
- Integrated with **authentic live openings** from India's leading tech product companies:
  - **Razorpay**: SDE Intern - Backend (`Java 17`, `Spring Boot`, `Go`, `Redis`) — **₹45,000 / month**
  - **Swiggy**: Full-Stack & Frontend Intern (`React.js`, `Next.js`, `Tailwind CSS`) — **₹40,000 / month**
  - **Postman**: Developer Experience & API Platform Intern (`Node.js`, `APIs`, `Postman`) — **₹45,000 / month**
  - **Zepto**: Quick-Commerce High-Throughput Routing Intern (`Go`, `Kafka`, `Microservices`) — **₹38,000 / month**
  - **Groww**: Mobile App Engineering Intern (`Flutter`, `Android`, `Kotlin`) — **₹35,000 / month**
  - **CRED**: Product Design & UI/UX Systems Intern (`Figma`, `Prototyping`, `Design Systems`) — **₹35,000 / month**
  - **Sarvam AI / Unstop**: Generative AI & Indic LLM Research Intern (`PyTorch`, `LoRA`, `LangChain`) — **₹30,000 / month**
- **Dual Apply Channels**:
  - ↗️ **Apply on Official Company Portal**: Direct outbound link to corporate careers page.
  - 📝 **1-Click CampusGig Proposal**: Pitch directly with custom bid in ₹, estimated days, and student portfolio links.

### 🛡️ 2. Role-Based Access Control & Security
- **Spring Security 6** with BCrypt password hashing.
- Role-specific dashboards & workflows:
  - `ROLE_STUDENT`: Showcase tech skills, GitHub/portfolio links, browse gigs, track proposal states (`PENDING`, `ACCEPTED`, `REJECTED`).
  - `ROLE_CLIENT`: Post micro-gigs, view incoming proposals, inspect student portfolios, and 1-click hire.

### ⚡ 3. Zero-Config Dual Database Architecture
- **Dev Profile (`application-dev.properties`)**: In-memory H2 database running in MySQL-compatibility mode with automated seed data for instant testing.
- **Production Profile (`application-mysql.properties`)**: Full relational MySQL 8.0 schema with foreign key cascades, unique constraints, and B-Tree indexes.

---

## 📁 Project Architecture & Structure

```
CampusGig/
├── pom.xml                                     # Maven build definition & dependencies
├── README.md                                   # Comprehensive documentation & setup
├── CampusGig_Interactive_Preview.html          # Standalone client simulator (zero server needed)
├── database/
│   ├── schema.sql                              # MySQL DDL (tables, indexes, foreign keys)
│   └── data.sql                                # Production seed data with Indian Rupee stipends
├── .vscode/                                    # 1-Click VS Code configurations
│   ├── launch.json                             # F5 Run/Debug configuration
│   ├── tasks.json                              # Ctrl+Shift+B Build & Run tasks
│   └── settings.json                           # Java 25 & environment paths
└── src/
    └── main/
        ├── java/com/campusgig/
        │   ├── CampusGigApplication.java       # Main entry point
        │   ├── config/                         # Security & UserDetails service
        │   ├── model/                          # JPA Entities (User, Gig, Application, etc.)
        │   ├── repository/                     # Spring Data JPA Repositories
        │   ├── service/                        # Business Logic layer
        │   ├── dto/                            # Data Transfer Objects
        │   └── controller/                     # Spring MVC Web Controllers
        └── resources/
            ├── application-dev.properties      # Zero-config H2 dev profile
            ├── application-mysql.properties    # MySQL production profile
            ├── database/data-dev.sql           # H2 seed script (12 gigs, 15 users)
            ├── static/css/custom.css           # Glassmorphism & custom utility styles
            └── templates/                      # Thymeleaf HTML5 + Tailwind UI templates
```

---

## 🚀 Quick Start (Running Locally)

### Prerequisites
- **JDK 25** (Temurin / OpenJDK)
- **Maven 3.8+** (or use included `mvnw.cmd`)
- **VS Code** (Optional, recommended)

### 1. Clone & Run with Maven Wrapper
```bash
# Clone the repository
git clone https://github.com/<your-username>/CampusGig.git
cd CampusGig

# Run locally in development mode (H2 zero-config DB)
.\mvnw.cmd spring-boot:run
```

App starts in seconds at: **`http://localhost:8080`**

### 2. Demo Login Credentials (Pre-Seeded)
| Role | Email | Password | Details |
| :--- | :--- | :--- | :--- |
| **Student** | `aryan.student@campus.edu` | `password123` | Final-year CS at IIT Delhi (Spring Boot, React) |
| **Student (AI)** | `ananya.ai@campus.edu` | `password123` | AI Researcher at BITS Pilani (LangChain, PyTorch) |
| **Client (Partner)**| `kunal@innovatex.in` | `password123` | Deep-Tech AI Accelerator Lead |
| **Client (Corporate)**| `recruiter@razorpay.com`| `password123` | Razorpay University Talent Lead |

---

## 🛠️ Tech Stack Details

- **Backend**: Java 25, Spring Boot 3.5.16 (Spring MVC, Spring Data JPA, Hibernate 6, Spring Security 6)
- **Frontend**: Thymeleaf, Tailwind CSS (CDN), FontAwesome 6
- **Database**: H2 (In-Memory Dev) / MySQL 8.0 (Production)
- **Build Tool**: Apache Maven 3.9

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
