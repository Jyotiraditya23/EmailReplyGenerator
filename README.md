# AI-Powered Gmail Reply Chrome Extension

A Chrome extension that intelligently reads incoming Gmail emails and generates **formal, context-aware replies** using **Google Gemini AI**.  
The system leverages a **Spring Boot backend with Spring AI** to process email content and return high-quality AI-generated responses in real time.

---

## 🚀 Features

- 📩 **Gmail Email Scanning** – Automatically reads the content of incoming emails.
- 🤖 **AI-Based Reply Generation** – Generates professional and relevant email replies using Google Gemini.
- ⚡ **Real-Time Processing** – Fast response generation via scalable REST APIs.
- 🧠 **Context-Aware Replies** – Uses Spring AI to ensure responses match email intent and tone.
- 🧩 **Seamless Chrome Extension Integration** – Works directly inside Gmail UI.

---

## 🛠️ Tech Stack

### Frontend (Chrome Extension)
- JavaScript
- Chrome Extension APIs (Manifest V3)
- Gmail DOM interaction

### Backend
- Java
- Spring Boot
- Spring AI
- RESTful APIs

### AI
- Google Gemini API

---

## 📐 System Architecture

1. User opens an email in Gmail  
2. Chrome Extension captures the email content  
3. Email content is sent to Spring Boot backend via REST API  
4. Spring AI processes the content using Gemini API  
5. AI-generated formal reply is returned  
6. Extension displays the reply in Gmail compose box  

---
