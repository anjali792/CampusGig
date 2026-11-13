# 🎓 CampusGig — Hyper-Local Micro-Internship & Student Project Marketplace

A production-ready, full-stack web application designed to connect college students and campus developers with local businesses, startups, and college clubs for paid micro-internships and freelance tasks.

---

## 🌟 1. Project Overview & Architecture

- **Backend Architecture**: Java 17, Spring Boot 3.3.4 with clean MVC architecture (Controllers, Services, JPA Repositories, DTOs, Entities, and Security Filters).
- **Relational Database**: MySQL 8.0+ with full referential integrity (`ON DELETE CASCADE`), indexes on user queries, unique constraint enforcement (preventing duplicate proposals), and timestamps.
- **Frontend & UX**: Responsive, mobile-first UI built with HTML5, Thymeleaf, and Tailwind CSS (via CDN) with a modern Upwork/LinkedIn startup polish, custom typography, badge indicators, and interactive modals.
- **Security & Authorization**: Spring Security 6 with role-based access control (`ROLE_STUDENT`, `ROLE_CLIENT`), BCrypt password hashing, session management, CSRF protection, and custom login routing.

---

## 📁 2. Complete Folder Structure

```
CampusGig/
├── pom.xml                                     # Maven build definition & dependencies
├── README.md                                   # Complete setup & production deployment guide
├── CampusGig_Interactive_Preview.html          # Instant browser preview of all views & workflows
├── database/
│   ├── schema.sql                              # DDL: tables, indexes, constraints
│   └── data.sql                                # Seed data: demo students, clients, gigs, & proposals
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── campusgig/
        │           ├── CampusGigApplication.java
        │           ├── config/
        │           │   ├── SecurityConfig.java
        │           │   ├── CustomUserDetailsService.java
        │           │   └── CustomAuthenticationSuccessHandler.java
        │           ├── model/
        │           │   ├── Role.java               # Enum: ROLE_STUDENT, ROLE_CLIENT, ROLE_ADMIN
        │           │   ├── GigStatus.java          # Enum: OPEN, IN_PROGRESS, CLOSED
        │           │   ├── LocationType.java       # Enum: REMOTE, ON_CAMPUS, HYBRID
        │           │   ├── ApplicationStatus.java  # Enum: PENDING, ACCEPTED, REJECTED, COMPLETED
        │           │   ├── User.java
        │           │   ├── StudentProfile.java
        │           │   ├── ClientProfile.java
        │           │   ├── Gig.java
        │           │   └── Application.java
        │           ├── repository/
        │           │   ├── UserRepository.java
        │           │   ├── StudentProfileRepository.java
        │           │   ├── ClientProfileRepository.java
        │           │   ├── GigRepository.java
        │           │   └── ApplicationRepository.java
        │           ├── service/
        │           │   ├── UserService.java
        │           │   ├── GigService.java
        │           │   └── ApplicationService.java
        │           ├── dto/
        │           │   ├── UserRegistrationDto.java
        │           │   ├── GigDto.java
        │           │   ├── ApplicationDto.java
        │           │   └── StudentProfileDto.java
        │           ├── util/
        │           │   └── SecurityUtils.java
        │           └── controller/
        │               ├── HomeController.java     # Landing page, stats, featured gigs
        │               ├── AuthController.java     # Login & registration with role picker
        │               ├── GigController.java      # Marketplace, search & proposal apply
        │               ├── StudentController.java  # Student dashboard, profile edit
        │               └── ClientController.java   # Client dashboard, post gig, applicant review
        └── resources/
            ├── application.properties
            ├── static/
            │   ├── css/
            │   │   └── custom.css                  # Typography, glassmorphism & gradients
            │   └── js/
            │       └── main.js                     # Mobile navbar, auto alerts, demo filler
            └── templates/
                ├── fragments/
                │   ├── layout.html                 # Master layout template
                │   ├── navbar.html                 # Dynamic authenticated navbar & drawer
                │   └── footer.html                 # Modern footer
                ├── index.html                      # Landing page with search & CTAs
                ├── auth/
                │   ├── login.html                  # Login page with 1-click test pills
                │   └── register.html               # Registration with dynamic role toggle
                ├── gigs/
                │   ├── list.html                   # Public marketplace & category search
                │   └── detail.html                 # Gig details & proposal submission form
                ├── student/
                │   ├── dashboard.html              # Student profile, stats, applied gigs
                │   └── profile-edit.html           # Edit bio, skills tags, portfolio links
                └── client/
                    ├── dashboard.html              # Metrics & posted gigs management
                    ├── post-gig.html               # Post a micro-internship form
                    └── applicants.html             # Applicant review & 1-click hire flow
```

---

## 🗄️ 3. Database SQL Schema & Seed Data

The project uses a structured MySQL relational schema:

1. **`users`**: Email (unique), BCrypt password hash, full name, phone, role (`ROLE_STUDENT` / `ROLE_CLIENT`), avatar URL.
2. **`student_profiles`**: Linked to `users` via 1-to-1 foreign key with `CASCADE`. Stores college, major, graduation year, bio, skills tags, GitHub/LinkedIn/portfolio URLs, hourly rate, and completed gigs count.
3. **`client_profiles`**: Linked to `users`. Stores company/organization name, organization type (Startup, Local Business, College Club), website, location, and gigs posted count.
4. **`gigs`**: Linked to client user. Contains title, category, description, budget, budget type (Fixed/Hourly), deadline date, required skills, work mode (Remote/On-Campus/Hybrid), and open status.
5. **`applications`**: Unique constraint on `(gig_id, student_id)` to prevent duplicate bids. Contains student pitch/cover letter, proposed budget, estimated turnaround days, portfolio link, status (`PENDING`, `ACCEPTED`, `REJECTED`, `COMPLETED`), and client feedback.

### Quick Database Import:
```bash
# In MySQL terminal or MySQL Workbench:
mysql -u root -p < database/schema.sql
mysql -u root -p < database/data.sql
```

---

## 🔑 4. Pre-Configured Demo Test Accounts

All accounts use the password: `password123`

| Role | Name | Email | Focus / Bio |
| :--- | :--- | :--- | :--- |
| **Student** | Alex Rivera | `alex.student@campus.edu` | Full-Stack (Java, Spring Boot, React, MySQL) |
| **Student** | Priya Sharma | `priya.student@campus.edu` | AI & Data (Python, Scikit-Learn, PyTorch, Scrapers) |
| **Student** | Marcus Vance | `marcus.design@campus.edu` | UI/UX & Design (Figma, Design Systems, Tailwind CSS) |
| **Client** | Sarah Jenkins | `sarah.client@techspark.io` | TechSpark Innovations (Early-stage SaaS startup) |
| **Client** | Dr. David Miller | `david.miller@campus.edu` | AI & Robotics Society (College Club Lead) |
| **Client** | Elena Rostova | `elena@apexcoffee.com` | Apex Artisan Coffee (Local Campus Business) |

*(Note: On `/login`, you can simply click the **"Student Demo"** or **"Client Demo"** pills to automatically fill the credentials!)*

---

## 🚀 5. How to Run the Project Live

### Step 1: Verify Prerequisites
Make sure you have installed:
1. **Java Development Kit (JDK) 17 or higher** (`java -version`)
2. **Maven 3.8+** (`mvn -v`)
3. **MySQL Server 8.0+** running locally on port 3306

### Step 2: Configure Database Credentials
Open `src/main/resources/application.properties` and verify your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/campusgig_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```
*(You can also set system environment variables `SPRING_DATASOURCE_USERNAME` and `SPRING_DATASOURCE_PASSWORD` without editing the file).*

### Step 3: Run the Application
Open PowerShell or your command terminal in the `CampusGig` project directory:

```bash
# Build and run with Maven:
mvn clean spring-boot:run
```

Alternatively, open the folder in **IntelliJ IDEA**, **Eclipse**, or **VS Code**, right-click `CampusGigApplication.java`, and select **Run**.

### Step 4: Open in Web Browser
Navigate to:
```
http://localhost:8080
```

---

## 🌐 6. Key Application Routes & Role Access

| URL Path | Permitted Roles | Description |
| :--- | :--- | :--- |
| `/` | Public | Homepage with hero search, stats, category grid, & featured gigs |
| `/gigs` | Public | Real-time marketplace search with category & keyword filters |
| `/gigs/{id}` | Public | Detailed gig specification & scope |
| `/gigs/{id}/apply` | `ROLE_STUDENT` | Proposal submission form (bid, turnaround time, pitch) |
| `/login` | Public | Authentication with 1-click test credential helpers |
| `/register` | Public | Dual registration with dynamic role picker & role fields |
| `/student/dashboard` | `ROLE_STUDENT` | Student portfolio, metrics, and applied gigs status tracker |
| `/student/profile/edit`| `ROLE_STUDENT` | Update bio, GitHub/portfolio links, skills tags, and rates |
| `/client/dashboard` | `ROLE_CLIENT` | Client metrics & posted micro-internship management |
| `/client/gigs/new` | `ROLE_CLIENT` | Post a new micro-gig form with budget, deadline & skills |
| `/client/gigs/{id}/applicants` | `ROLE_CLIENT` | Review candidate proposals, inspect portfolios, & 1-click hire |

---

## ⚡ 7. Zero-Config Instant Browser Preview
If you want to visually test the application before starting your MySQL database:
1. Double-click `CampusGig_Interactive_Preview.html` to open it in Chrome, Edge, or Firefox.
2. Use the top switch bar to cycle between **Home**, **Marketplace**, **Gig Details**, **Student Dashboard**, **Client Dashboard**, and **Hiring Flow**.
