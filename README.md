## MakeupSearchApp

This project is a distributed system built as a native Android application backed by a Java-based web service. The app allows users to search for makeup products by brand and/or product type, while all business logic, third-party API integration, logging, and analytics are handled server-side.
The goal of the project was to design and implement a complete distributed application with a mobile client, backend service, persistent storage, and an operational analytics dashboard.

##### System Architecture

**Android Client**
- Collects user input and sends HTTP requests
- Parses JSON responses and renders results
- Supports repeatable queries without restarting
- Handles validation and network errors

**Backend Web Service (Java Servlets)**
- Implements REST APIs consumed by the Android app
- Executes business logic and input validation
- Integrates with third‑party API and handles errors

**Third‑Party API Integration**
- Fetches raw product data from a public makeup API
- Normalizes fields, filters invalid data, sorts results
- Returns a simplified JSON response to the client

**Logging & Operations Dashboard**
- Logs request/response traffic to MongoDB Atlas
- Web dashboard shows request volume, latency, status distribution, and logs

##### Key Features
- Native Android UI with EditText, Button, and RecyclerView
- Server‑side data processing and validation
- Robust error handling across client, server, and API failures
- Request/response logging with structured NoSQL storage
- Analytics dashboard with charts and full log visibility
- Containerized backend deployed using GitHub Codespaces

##### Technologies Used
- Android: Android Studio, Java, Retrofit, Gson
- Backend: Java Servlets, REST APIs, MVC pattern
- Data & Storage: MongoDB Atlas, CRUD operations
- Infrastructure: Docker, GitHub Codespaces
- Web & Visualization: HTML, Chart.js
- Protocols & Formats: HTTP, JSON
