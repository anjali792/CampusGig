-- =========================================================================
-- CampusGig Real-World Seed Data (Indian Rupees ₹ + Live Company Internships)
-- Password for all users: password123 
-- Hash: $2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6
-- =========================================================================

-- 1. Insert Users
INSERT INTO users (id, email, password_hash, full_name, phone, role, avatar_url, is_active, created_at, updated_at) VALUES
(1, 'aryan.student@campus.edu', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Aryan Sharma', '+91 98765 43210', 'ROLE_STUDENT', 'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'ananya.ai@campus.edu', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Ananya Verma', '+91 98123 45678', 'ROLE_STUDENT', 'https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'rohan.design@campus.edu', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Rohan Mehta', '+91 97234 56789', 'ROLE_STUDENT', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'priya.cyber@campus.edu', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Priya Patel', '+91 99887 76655', 'ROLE_STUDENT', 'https://images.unsplash.com/photo-1517841905240-472988babdf9?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
-- Corporate & Startup Clients
(5, 'kunal@innovatex.in', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Kunal Shah', '+91 99001 12233', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1556157382-97eda2d62296?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'iyer@campus.edu', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Dr. R. K. Iyer', '+91 94440 12345', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'meera@chaihouse.co.in', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Meera Nair', '+91 98450 98765', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'vikram@eduvibe.tech', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Vikram Singhania', '+91 91234 56780', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'recruiter@razorpay.com', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Razorpay University Talent', '+91 80678 12345', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1570295999919-56ceb5ecca61?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'careers@swiggy.in', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Swiggy Tech Hiring', '+91 80456 78901', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 'earlycareers@postman.com', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Postman DevRel & Engineering', '+91 80332 54321', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1560250097-0b93528c311a?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 'hiring@zeptonow.com', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Zepto Quick-Tech Team', '+91 22690 11223', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 'campus@groww.in', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Groww Campus Connect', '+91 80998 87766', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1580489944761-15a19d654956?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, 'design@cred.club', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'CRED Design Studio', '+91 80499 90011', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, 'talent@sarvam.ai', '$2a$10$Nya7dogSoCS0v09MndajYOJ6hCrKvuGuoE7F0hpynZb1q50X671u6', 'Sarvam AI Research Lab', '+91 80234 56789', 'ROLE_CLIENT', 'https://images.unsplash.com/photo-1507679799987-c73779587ccf?w=150', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 2. Insert Student Profiles
INSERT INTO student_profiles (id, user_id, college, major, graduation_year, bio, skills_tags, github_url, linkedin_url, portfolio_url, hourly_rate, completed_gigs_count, average_rating, created_at, updated_at) VALUES
(1, 1, 'IIT Delhi', 'B.Tech Computer Science & Eng.', 2025, 
 'Full-stack software engineer specializing in high-performance Spring Boot backends, Redis caching, React, and MySQL database optimization. Smart India Hackathon finalist.', 
 'Java, Spring Boot, React, MySQL, Redis, Docker, REST APIs, Git', 
 'https://github.com/aryansharma-dev', 'https://linkedin.com/in/aryan-sharma-tech', 'https://aryansharma.dev', 800.00, 5, 4.96, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(2, 2, 'BITS Pilani', 'M.Sc Data Science & AI', 2026, 
 'Generative AI researcher & Python engineer. Experienced in fine-tuning Llama/Mistral with LoRA, building RAG pipelines, and automated dataset curation.', 
 'Python, PyTorch, HuggingFace, LoRA, LangChain, FastAPI, Pandas, Scikit-Learn', 
 'https://github.com/ananya-ai', 'https://linkedin.com/in/ananya-verma-ai', 'https://ananyaverma.io', 950.00, 4, 5.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(3, 3, 'IIT Bombay (IDC)', 'Interaction Design & HCI', 2025, 
 'Product & UI/UX designer. I create user-centric mobile apps, interactive 3D landing pages in Three.js, and clean Tailwind design systems for campus startups.', 
 'UI/UX Design, Figma, Three.js, Design Systems, Wireframing, Tailwind CSS, Prototyping', 
 'https://github.com/rohanmehta-design', 'https://linkedin.com/in/rohanmehta-ux', 'https://behance.net/rohanmehta', 700.00, 3, 4.90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(4, 4, 'DTU Delhi', 'Information Security & Cloud', 2026, 
 'Cybersecurity enthusiast and ethical hacker. Recognized on bug bounty hall of fames. Passionate about web application pen-testing and cloud audits.', 
 'Cybersecurity, Burp Suite, OWASP Top 10, Kali Linux, Python, Network Security, Wireshark', 
 'https://github.com/priyapatel-sec', 'https://linkedin.com/in/priya-patel-sec', 'https://priyapatel.security', 750.00, 2, 4.85, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 3. Insert Client Profiles
INSERT INTO client_profiles (id, user_id, company_name, organization_type, website_url, location, description, total_gigs_posted, created_at, updated_at) VALUES
(1, 5, 'InnovateX AI Labs', 'Startup', 'https://innovatex.in', 'Indiranagar, Bengaluru & Campus Incubator', 
 'Deep-tech AI accelerator working with university labs to commercialize real-world student AI/ML breakthroughs.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(2, 6, 'Campus Robotics & Innovation Club', 'College Club Lead', 'https://campus-robotics.ac.in', 'Department of Robotics, Room 302', 
 'Premier inter-college technical club organizing national robotics competitions, IoT workshops, and hackathons.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(3, 7, 'Chai & Byte Artisan Cafe', 'Local Business', 'https://chaibyte.in', 'University North Gate, College Lane', 
 'Beloved campus food & coffee spot. We hire student designers, social media leads, and developers for our local tech & marketing.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(4, 8, 'EduVibe Learning Tech', 'Startup', 'https://eduvibe.tech', 'Cyber City, Gurugram', 
 'Fast-growing campus edtech platform providing peer learning networks and automated assignment grading tools.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(5, 9, 'Razorpay', 'Fintech Enterprise', 'https://razorpay.com', 'Koramangala, Bengaluru', 
 'Razorpay is India’s premier financial technology unicorn powering online payments, neo-banking, and merchant checkout for millions of businesses.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(6, 10, 'Swiggy', 'Consumer Tech Unicorn', 'https://swiggy.com', 'Bellandur, Bengaluru', 
 'Swiggy is India’s leading on-demand convenience platform connecting millions of consumers with restaurants, grocery stores, and daily essentials.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(7, 11, 'Postman', 'SaaS Developer Platform', 'https://postman.com', 'Bengaluru Campus & Remote', 
 'Postman is the globally recognized API development platform trusted by over 30 million software engineers worldwide.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(8, 12, 'Zepto', 'Quick-Commerce Tech', 'https://zeptonow.com', 'Powai, Mumbai & Bengaluru', 
 'Zepto is revolutionizing fast commerce in India with 10-minute grocery delivery powered by high-throughput real-time routing algorithms.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(9, 13, 'Groww', 'Fintech & WealthTech', 'https://groww.in', 'Vaishnavi Tech Park, Bengaluru', 
 'Groww makes investing simple and accessible to 40+ million Indians across stocks, mutual funds, gold, and UPI payments.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(10, 14, 'CRED', 'Design & Fintech Studio', 'https://cred.club', 'Indiranagar, Bengaluru', 
 'CRED is an exclusive community of creditworthy individuals celebrated worldwide for world-class design systems and fluid micro-interactions.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(11, 15, 'Sarvam AI', 'Generative AI Research', 'https://sarvam.ai', 'Bengaluru & Remote', 
 'Sarvam AI is developing sovereign foundational models and Indic speech/generative AI systems for India’s diverse multilingual ecosystem.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 4. Insert Real Gigs & Live Industry Internships (in Indian Rupees ₹)
INSERT INTO gigs (id, client_id, title, category, description, budget, budget_type, deadline, required_skills, location_type, campus_location, company_name, external_apply_url, is_verified, status, applicants_count, created_at, updated_at) VALUES
-- 1. Razorpay Real Internship
(1, 9, 'Software Development Engineer (SDE) Intern - Backend (Java / Spring Boot / Go)', 'Web Development', 
 'Razorpay is hiring 2025/2026 pre-final & final year students for our core Payments & Banking Engineering team in Bengaluru.

Key Responsibilities:
• Build resilient, distributed payment microservices handling 5,000+ transactions per second with 99.99% uptime.
• Design clean RESTful APIs in Java 17 / Spring Boot and Go.
• Optimize MySQL schemas, implement Redis caching, and assist in distributed Kafka event streaming.
• Participate in code reviews, write automated unit/integration tests with JUnit and Mockito.

Perks & Learning:
• ₹45,000 / month stipend with high PPO conversion opportunities.
• Direct mentorship from Senior Staff Engineers and free daily gourmet cafeteria meals.', 
 45000.00, 'MONTHLY', DATEADD('DAY', 30, CURRENT_DATE), 'Java, Spring Boot, MySQL, REST APIs, Redis, Microservices, Git', 'HYBRID', 'Razorpay HQ, SJR Cyber Lync, Koramangala Bengaluru', 'Razorpay', 'https://razorpay.com/jobs/', TRUE, 'OPEN', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 2. Swiggy Real Internship
(2, 10, 'Full-Stack & Frontend Engineering Intern (React.js, Next.js, Node.js & Web Speed)', 'Web Development', 
 'Swiggy Consumer Tech team is seeking enthusiastic software engineering interns to power India’s leading convenience web ecosystem.

What you will do:
• Build high-performance, accessible consumer web screens for Swiggy Food and Instamart.
• Optimize Core Web Vitals (LCP, FID, CLS) aiming for sub-second page rendering on 4G networks.
• Develop reactive frontend components using React 18, Next.js, TypeScript, and modern Tailwind CSS.
• Integrate with Node.js microservices and GraphQL endpoints.

Requirements:
• Strong fundamentals in JavaScript/TypeScript, HTML5, CSS3, and DOM performance.
• Prior experience with React, state management (Redux/Zustand), and responsive web design.', 
 40000.00, 'MONTHLY', DATEADD('DAY', 25, CURRENT_DATE), 'React, TypeScript, JavaScript, Tailwind CSS, Node.js, Next.js, REST APIs', 'REMOTE', NULL, 'Swiggy', 'https://careers.swiggy.com/', TRUE, 'OPEN', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 3. Postman Real Internship
(3, 11, 'Developer Experience & API Platform Engineering Intern', 'Web Development', 
 'Postman is the API platform used by 30+ million developers. As an engineering intern, you will help make developer workflows magical.

Responsibilities:
• Contribute to Postman core API testing tooling, OpenAPI v3 spec generators, and Newman test runners.
• Write robust developer tooling in TypeScript and Node.js.
• Collaborate with developer advocates to craft interactive developer workspaces, sample collections, and documentation.
• Debug real developer issues reported in Postman open-source GitHub repositories.

Eligibility:
• B.Tech/Dual Degree students graduating in 2025 or 2026.
• Love for APIs, clean code, Git, and automated testing.', 
 45000.00, 'MONTHLY', DATEADD('DAY', 28, CURRENT_DATE), 'JavaScript, Node.js, REST APIs, Postman, TypeScript, Git, Docker', 'HYBRID', 'Postman Office, Bengaluru Campus', 'Postman', 'https://www.postman.com/company/careers/', TRUE, 'OPEN', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 4. Zepto Real Internship
(4, 12, 'Quick-Commerce Backend Systems & Dispatch Routing Intern', 'Web Development', 
 'Join India’s fastest-growing 10-minute grocery delivery startup.

About the Role:
• Work on real-time dark-store inventory allocation and delivery rider geo-routing algorithms.
• Build high-throughput microservices using Go and Java handling tens of thousands of orders per minute during peak IPL & festival surges.
• Benchmark Redis cache latencies and Kafka event queues to eliminate bottlenecks.

Perks:
• ₹38,000 / month stipend + Zepto credits + Pre-Placement Offer (PPO) pathway.
• Fast-paced hypergrowth engineering environment.', 
 38000.00, 'MONTHLY', DATEADD('DAY', 21, CURRENT_DATE), 'Java, Golang, MySQL, Kafka, Redis, Data Structures, Microservices', 'HYBRID', 'Zepto Tech Hub, Powai Mumbai', 'Zepto', 'https://www.zeptonow.com/careers', TRUE, 'OPEN', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 5. Groww Real Internship
(5, 13, 'Mobile App Engineering Intern (Android / Flutter & Jetpack Compose)', 'Mobile Apps', 
 'Groww is democratizing investing for over 40 million Indians. We are hiring talented mobile engineering interns.

Scope of Work:
• Implement pixel-perfect, silky-smooth mobile screens in Flutter and Kotlin for stock trading charts and SIP calculators.
• Integrate native biometric authentication (Fingerprint / Face Unlock) and UPI 2.0 payment hooks.
• Maintain 99.9% crash-free rates with unit tests and memory profiler benchmarking.

Requirements:
• Experience with Flutter/Dart or Kotlin/Android development.
• Strong understanding of REST APIs, asynchronous programming, and clean architecture (MVVM/Bloc).', 
 35000.00, 'MONTHLY', DATEADD('DAY', 24, CURRENT_DATE), 'Flutter, Dart, Kotlin, Android, REST APIs, Git, UI/UX', 'HYBRID', 'Vaishnavi Tech Park, Bengaluru', 'Groww', 'https://groww.in/careers', TRUE, 'OPEN', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 6. CRED Real Internship
(6, 14, 'Product Design & UI/UX Design Systems Intern', 'UI/UX Design', 
 'CRED is revered globally for cutting-edge design aesthetics, playful micro-interactions, and flawless typography.

What you will craft:
• Design next-generation financial user interfaces and gamified credit reward animations.
• Contribute to the CRED Design System in Figma with tokens, responsive variants, and auto-layout.
• Conduct rapid user testing with students and professionals to refine user journeys.
• Create high-fidelity interactive prototypes with micro-interactions in Figma, ProtoPie, or After Effects.

Portfolio Requirement:
• Please include your Behance, Dribbble, or live Figma project link showcasing clean typography and mobile design.', 
 35000.00, 'MONTHLY', DATEADD('DAY', 18, CURRENT_DATE), 'Figma, UI/UX Design, Prototyping, Wireframing, Motion Design, Interaction Design', 'ON_CAMPUS', 'CRED Studio, 100 Feet Rd, Indiranagar Bengaluru', 'CRED', 'https://careers.cred.club/', TRUE, 'OPEN', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 7. Sarvam AI Real Internship
(7, 15, 'Generative AI & Indic LLM Research Intern (LangChain, LoRA & PyTorch)', 'AI & Data Science', 
 'Sarvam AI is building foundational artificial intelligence systems specifically fine-tuned for India’s multilingual population.

Key Focus:
• Fine-tune open-source models (Llama-3, Mistral, Gemma) using parameter-efficient fine-tuning (PEFT/LoRA).
• Construct high-accuracy Retrieval-Augmented Generation (RAG) pipelines across Hindi, Tamil, Telugu, and Marathi technical corpora.
• Benchmark translation fidelity and hallucination rates with automated evaluation frameworks.
• Containerize AI inference microservices using FastAPI and Docker.

Stipend: ₹30,000 / month (Fully Remote with GPU cloud credits provided).', 
 30000.00, 'MONTHLY', DATEADD('DAY', 26, CURRENT_DATE), 'Python, PyTorch, LLMs, LangChain, HuggingFace, RAG, NLP, FastAPI', 'REMOTE', NULL, 'Sarvam AI', 'https://unstop.com/internships', TRUE, 'OPEN', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 8. InnovateX AI Labs Student Project
(8, 5, 'Fine-Tune Llama-3 8B with LoRA on University Syllabus & PDF Notes', 'AI & Data Science', 
 'We need a knowledgeable AI student to fine-tune an open-source Llama-3 8B model using LoRA/QLoRA on 500+ pages of academic engineering textbooks and lecture notes. Deliverables include a quantized GGUF model, training evaluation loss graphs, and a lightweight FastAPI endpoint for querying answers. Excellent micro-internship for AI/ML students!', 
 14000.00, 'FIXED', DATEADD('DAY', 14, CURRENT_DATE), 'Python, PyTorch, LoRA, HuggingFace, FastAPI, LangChain', 'REMOTE', NULL, 'InnovateX AI Labs', NULL, TRUE, 'OPEN', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 9. Local Campus Business (Chai & Byte Cafe)
(9, 7, 'Contactless QR Menu & Live Kitchen Display Web App (React + UPI Mock)', 'Web Development', 
 'Chai & Byte Artisan Cafe is building a student-first web app for table ordering at our university branch.

Deliverables:
• Dynamic table QR code scanner that pulls the cafe menu with real-time stock availability.
• Seamless mobile ordering interface with UPI deep-link mock checkout (Google Pay / PhonePe / Paytm).
• Kitchen Display System (KDS) for our baristas showing pending orders with sound chime alerts.
• Admin dashboard to toggle sold-out snacks and track daily sales.', 
 16000.00, 'FIXED', DATEADD('DAY', 12, CURRENT_DATE), 'React, Tailwind CSS, JavaScript, Node.js, WebSockets, UPI Integration', 'ON_CAMPUS', 'University North Gate, College Lane', 'Chai & Byte Artisan Cafe', NULL, TRUE, 'OPEN', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 10. Campus Robotics Club
(10, 6, 'Design 3D Interactive Web Landing Page in Three.js for Annual Tech Fest', 'Web Development', 
 'Campus Robotics Club is hosting "RoboCon 2026". We need an eye-catching, high-performance 3D interactive landing page using Three.js / Spline and Tailwind CSS with a sci-fi robotic arm model that rotates with user mouse movement. Must be fully responsive on mobile phones and load in under 2.5 seconds.', 
 9500.00, 'FIXED', DATEADD('DAY', 10, CURRENT_DATE), 'Three.js, JavaScript, Tailwind CSS, WebGL, Responsive UI', 'HYBRID', 'Department of Robotics Lab 302', 'Campus Robotics & Innovation Club', NULL, TRUE, 'OPEN', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 11. EduVibe Campus Project
(11, 8, 'Hostel Late-Night Food Delivery Mobile App (React Native + Spring Boot)', 'Mobile Apps', 
 'EduVibe is prototyping a hyper-local hostel night-canteen ordering app. Need a student dev to build 6 clean mobile screens in React Native and integrate with our existing Spring Boot order placement & status APIs. Deliverables include APK build, clean TypeScript components, and order push notification support.', 
 18000.00, 'FIXED', DATEADD('DAY', 15, CURRENT_DATE), 'React Native, TypeScript, Java, Spring Boot, REST APIs, Tailwind', 'REMOTE', NULL, 'EduVibe Learning Tech', NULL, TRUE, 'OPEN', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- 12. Security Audit Gig
(12, 5, 'Vulnerability Assessment & Pen-Testing (VAPT) for Web Application', 'Web Development', 
 'Perform comprehensive black-box and grey-box security assessment on our internal staging web portal. Identify SQL injections, XSS vulnerabilities, broken authentication, and CSRF flaws according to OWASP Top 10 standards. Deliver a professional remediation report with PoC exploits.', 
 12500.00, 'FIXED', DATEADD('DAY', 8, CURRENT_DATE), 'Burp Suite, OWASP Top 10, Kali Linux, Web Security, Pen-Testing', 'REMOTE', NULL, 'InnovateX AI Labs', NULL, TRUE, 'OPEN', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 5. Insert Applications
INSERT INTO applications (id, gig_id, student_id, cover_letter, proposed_budget, estimated_days, portfolio_link, status, client_feedback, applied_at, reviewed_at) VALUES
(1, 1, 1, 
 'Hi Razorpay Hiring Team! I am a final-year CS undergrad at IIT Delhi. I have built distributed Spring Boot backends and optimized database schemas with Redis caching. I built an end-to-end payment reconciliation microservice in a hackathon. I would love the opportunity to contribute to Razorpay core payments!', 
 45000.00, 30, 'https://github.com/aryansharma-dev/payment-reconciliation-engine', 'PENDING', NULL, CURRENT_TIMESTAMP, NULL),

(2, 7, 2, 
 'Hi Sarvam AI team! I am an AI researcher at BITS Pilani. I have previously fine-tuned Mistral 7B on bilingual corpora using QLoRA with 4-bit quantization and built multilingual RAG pipelines. Check my open-source HuggingFace repo with 1,200+ stars.', 
 30000.00, 30, 'https://github.com/ananya-ai/qlora-indic-finetune', 'ACCEPTED', 'Outstanding profile and relevant open-source contributions. Welcome aboard for the research internship!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

(3, 6, 3, 
 'Hello CRED Studio! I am deeply inspired by CRED design language. I study at IDC School of Design, IIT Bombay. I have crafted complete design systems in Figma with auto-layout and interactive prototypes in ProtoPie. Please review my portfolio at behance.net/rohanmehta.', 
 35000.00, 30, 'https://behance.net/rohanmehta/cred-redesign-case-study', 'PENDING', NULL, CURRENT_TIMESTAMP, NULL),

(4, 8, 2, 
 'Hi Kunal! I can complete the Llama-3 8B fine-tuning with LoRA on your syllabus notes in 10 days, including dataset cleaning, loss graphs, and FastAPI inference scripts.', 
 14000.00, 10, 'https://github.com/ananya-ai/academic-rag-system', 'PENDING', NULL, CURRENT_TIMESTAMP, NULL),

(5, 10, 3, 
 'Hello Dr. Iyer! I specialize in Three.js and creative web experiences. I can deliver a fluid, 60 FPS interactive 3D robot arm landing page for RoboCon 2026 within 8 days.', 
 9500.00, 8, 'https://behance.net/rohanmehta/threejs-robocon-demo', 'ACCEPTED', 'Approved! Please sync with the robotics club technical committee.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
