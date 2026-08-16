# UCS503P-202627-EcoCode
# EcoCode

**A green-software analysis platform that measures, scores, and helps optimize the resource efficiency of code and running applications.**

> EcoCode is like a health check-up for software: it finds where a program is wasting computing resources and shows whether a developer's fixes actually made it faster and lighter.

---

## Table of Contents

- [What Is EcoCode?](#what-is-eecode)
- [Why It Matters](#why-it-matters)
- [Core Concept: A Fitness Tracker for Software](#core-concept-a-fitness-tracker-for-software)
- [Example: Before vs. After](#example-before-vs-after)
- [Basic Workflow](#basic-workflow)
- [System Components](#system-components)
  - [1. Static Code Analyzer](#1-static-code-analyzer)
  - [2. Runtime Profiler](#2-runtime-profiler)
- [Rule Engine](#rule-engine)
- [EcoScore](#ecoscore)
- [Feature Modules](#feature-modules)
  - [Database Query Analyzer](#database-query-analyzer)
  - [API / Network Analyzer](#api--network-analyzer)
  - [Frontend Efficiency Analyzer](#frontend-efficiency-analyzer)
  - [Version Comparison](#version-comparison)
  - [GitHub & CI/CD Integration](#github--cicd-integration)
  - [Performance Budgets](#performance-budgets)
- [Architecture](#architecture)
- [Sandboxing & Security](#sandboxing--security)
- [Technology Stack](#technology-stack)
- [Requirements](#requirements)
  - [Functional](#functional-requirements)
  - [Non-Functional](#non-functional-requirements)
- [UML Overview](#uml-overview)
- [Testing Strategy](#testing-strategy)
- [Project Roadmap](#project-roadmap)
- [Final Demo Script](#final-demo-script)
- [How EcoCode Differs from a Normal Code Analyzer](#how-ecocode-differs-from-a-normal-code-analyzer)
- [Scope Guidance](#scope-guidance)
- [One-Sentence Summary](#one-sentence-summary)

---

## What Is EcoCode?

Two programs can produce the same correct output while using very different amounts of computing resources:

| | Program A | Program B |
|---|---|---|
| Time | 5 sec | 1 sec |
| RAM | 300 MB | 120 MB |
| CPU | High | Low |

Both are "correct." Program B is **efficient**. EcoCode analyzes source code and running applications to answer:

> "My code works — but is it wasting CPU, memory, database queries, network calls, or energy?"

It identifies inefficiencies and suggests areas for improvement.

## Why It Matters

Most developers focus on correctness ("does my program give the right answer?"). Production software also needs to answer: **"how much computation does it require?"**

Small inefficiencies compound at scale:
- 100 ms of wasted CPU time per request × 1,000,000 requests/day = significant waste.
- Returning 2 MB when the client only needs 50 KB wastes bandwidth, server work, client processing, and battery — repeated across every request.

## Core Concept: A Fitness Tracker for Software

| A fitness watch tracks... | EcoCode tracks... |
|---|---|
| Heart rate | Execution time |
| Calories | CPU usage |
| Steps | Memory usage |
| Sleep | DB queries, network traffic, unnecessary operations, optimization opportunities |

## Example: Before vs. After

**Before** — searching a list repeatedly:

```python
users = [...]
for user in users:
    if user.id in list_of_ids:
        print(user.name)
```

**After** — using a hash set for O(1) lookups:

```python
ids = set(list_of_ids)
for user in users:
    if user.id in ids:
        print(user.name)
```

EcoCode's report:

```
BEFORE                          AFTER
Runtime:      4.8 sec           Runtime:      0.6 sec
Memory:       95 MB             Memory:       102 MB
CPU Time:     4.4 sec           CPU Time:     0.5 sec
Efficiency:   48/100            Efficiency:   86/100
```

EcoCode explains the trade-off in plain language: runtime improved significantly, though memory increased slightly because a hash set was introduced. **EcoCode shows engineering trade-offs — it never claims "lower RAM = always better."**

## Basic Workflow

```
Developer → EcoCode → Create Project → Analyze (source and/or running app) → Report
```

Example project setup:

```
Project:     Student Management API
Language:    Java
Repository:  github.com/team/student-api
```

Example report:

```
ECOCODE ANALYSIS
Runtime                 1.81 sec
Peak memory             184 MB
Average CPU             63%
Database queries/req    28
Response payload        721 KB

Warnings:
⚠ Repeated database access inside loop
⚠ Large API response
⚠ Duplicate calculations
⚠ Potential inefficient collection lookup
✓ Compression enabled
```

## System Components

EcoCode has two major analysis modes that combine to form a complete picture.

### 1. Static Code Analyzer

Inspects source code **without running it**, looking for suspicious patterns.

**N+1 query detection:**

```java
for (User user : users) {
    database.getOrders(user.getId());
}
```

With 1,000 users, this may generate 1,000 database queries instead of one batched query:

```
Potential N+1 Query Pattern
File:              UserService.java
Line:              83
Severity:          High
Reason:            A repository/database call occurs inside an iteration over a collection.
Potential Impact:  Excessive database and CPU usage.
```

**Repeated computation:**

```python
# Inefficient — recalculates every iteration
for item in items:
    total = calculate_expensive_value(data)
    process(item, total)

# Improved — computed once
total = calculate_expensive_value(data)
for item in items:
    process(item, total)
```

**Inefficient string building (Java):**

```java
String result = "";
for (String word : words) {
    result = result + word;   // ⚠ Consider StringBuilder
}
```

**Unnecessary object creation:**

```java
for (...) {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");  // ⚠ Recreated every iteration
}
```

These patterns map directly to the "Green Coding Guidelines — Optimizing Memory & CPU Utilization" curriculum area.

### 2. Runtime Profiler

Static analysis says "this *might* be inefficient." Runtime profiling proves **what actually happened**.

```
                     VERSION 1        VERSION 2
Execution time       2.32 sec         0.91 sec
Peak memory          281 MB           174 MB
CPU utilization      78%              49%
Disk read            34 MB            29 MB
Disk write           3 MB             3 MB
```

Comparison output:

```
Runtime improvement:       60.8%
Memory reduction:          38.1%
Database query reduction:  88.5%
Payload reduction:         82.2%
```

## Rule Engine

Static analysis is powered by a configurable rule set:

| Rule ID | Name |
|---|---|
| EC001 | Database call inside loop |
| EC002 | Repeated expensive calculation |
| EC003 | Potential inefficient collection lookup |
| EC004 | Excessive nested loops |
| EC005 | Repeated object allocation |
| EC006 | Blocking operation inside request loop |
| EC007 | Large response object |
| EC008 | Missing pagination |
| EC009 | Duplicate API request |
| EC010 | Unbounded data fetch |

Each rule has: **ID, Name, Description, Severity, Category, Recommendation**. Example:

```
EC008 — Missing Pagination
Severity:        HIGH
Category:        Database / Network
Description:     API may retrieve a large dataset without pagination.
Recommendation:  Use page/limit parameters.
```

## EcoScore

A single, project-defined comparative efficiency metric:

```
EcoScore: 74/100

CPU Efficiency          78/100
Memory Efficiency       71/100
Database Efficiency     53/100
Network Efficiency      82/100
Code Smell Score        76/100
```

Suggested weighting:

```
EcoScore = 25% CPU + 20% Memory + 20% Network + 20% Database + 15% Static analysis
```

Scores are normalized against a previous version, a defined benchmark, or a baseline execution — **not** presented as an absolute measure of energy or carbon impact. Avoid claims like "74 = 26% carbon waste"; instead describe EcoScore as *"a project-defined comparative efficiency metric based on normalized software-resource indicators."*

## Feature Modules

### Database Query Analyzer

Flags unbounded queries and over-fetching:

```
⚠ Potential unbounded query
Large table queried without LIMIT/pagination.
```

```
SELECT * FROM users;   -- ⚠ fetch only required fields where appropriate
```

**N+1 detection example:**

```
Database Query Analysis
Before: █████████████████████ 101
After:  ██                     2
```

### API / Network Analyzer

Detects duplicate requests and over-fetching:

```
⚠ Duplicate request
/profile requested 4 times in 1 page load.
→ Recommendation: Cache or reuse already-loaded profile data.
```

```
Payload:            5.2 MB
Displayed fields:   3
Returned fields:    27
→ Potential over-fetching detected.
→ Recommendation: Use projection/DTO to return only necessary fields.
```

### Frontend Efficiency Analyzer

Analyzes page weight, unused assets, and request counts:

```
                    BEFORE          AFTER
Page weight         6.8 MB          1.7 MB
JavaScript          3.1 MB          —
Images              3.2 MB          —
Requests            83              31
```

Maps to: *Energy-Aware UI/UX Design, Reduced Animations, Low-Power UX Patterns.*

> **Note:** Dark mode should not be presented as "= green software." Energy impact depends heavily on display technology and content. Focus arguments on measurable factors: fewer bytes transferred, fewer computations, fewer queries, optimized assets.

### Version Comparison

Every analysis run is retained, enabling historical tracking:

```
v0.1  EcoScore 52
v0.2  EcoScore 61
v0.3  EcoScore 74
v1.0  EcoScore 86
```

```
              v0.1        v1.0
Runtime       1.8 s       0.5 s
Memory        231 MB      151 MB
Queries       64          11
Payload       1.2 MB      310 KB
Warnings      28          12
```

### GitHub & CI/CD Integration

On push, EcoCode can run an analysis automatically:

```
Commit abc123 — "Optimize student retrieval query"
Before EcoScore: 71
After EcoScore:  82
Runtime:  621 ms → 301 ms
Queries:  31 → 8
```

CI/CD regression detection:

```
❌ PERFORMANCE REGRESSION
Endpoint:    /api/students
Baseline:    420 ms
Current:     790 ms
Regression:  +88%
```

### Performance Budgets

Developer-configured thresholds that fail a build when violated:

```
GET /dashboard
Response time  <= 400 ms
Payload        <= 300 KB
Queries        <= 15

Frontend Home Page
Page weight    <= 2 MB
Requests       <= 40
```

## Architecture

Modular monolith:

```
                  React Frontend
                        │
                        ▼
                 Spring Boot API
                        │
       ┌────────────────┼────────────────┐
       ▼                ▼                ▼
 Project Module   Analysis Module   Report Module
                        │
          ┌─────────────┼──────────────┐
          ▼             ▼              ▼
      Static        Profiler        Metrics
      Analyzer                      Engine
          │             │              │
          └─────────────┼──────────────┘
                        ▼
                    PostgreSQL
```

Isolated analysis pipeline:

```
EcoCode Backend → Job Queue → Sandbox / Container
                                  ├── Compile
                                  ├── Execute
                                  ├── Measure
                                  └── Return metrics
```

## Sandboxing & Security

User-submitted code must never run directly on the main server (e.g., an infinite loop or malicious payload). Analysis jobs run in a restricted environment with:

- CPU and memory limits
- Execution timeout
- No host filesystem access
- Restricted or no network access

For an initial prototype, scope execution to your own benchmark projects rather than arbitrary untrusted uploads — safer and more achievable within a semester.

## Technology Stack

| Layer | Choice |
|---|---|
| Frontend | React + TypeScript |
| Backend | Spring Boot |
| Database | PostgreSQL |
| Code Analysis | JavaParser / AST-based Java analysis (or Python AST) |
| Profiling | JVM tools, Linux process metrics |
| Containerization | Docker |
| Testing | JUnit, Mockito, Playwright |
| CI/CD | GitHub Actions |

**Scope to one language initially** — Java is recommended (build EcoCode itself in Java and analyze Java projects), or Python for simpler AST tooling.

**Why AST over regex:** parsing code into a structural tree lets the analyzer ask precise questions like *"is there a repository/database call inside a loop node?"* — far more robust than text pattern matching.

```
for (User u : users) {
    repo.findById(u.getId());
}
```
```
ForLoop
 ├── Variable: u
 └── Body
      └── MethodCall: repo.findById(...)
```

## Requirements

### Functional Requirements

| ID | Requirement |
|---|---|
| FR-01 | User shall create a project. |
| FR-02 | User shall connect a repository. |
| FR-03 | System shall run static analysis. |
| FR-04 | System shall classify detected issues by severity. |
| FR-05 | System shall store analysis history. |
| FR-06 | System shall compare two analysis runs. |
| FR-07 | System shall measure runtime. |
| FR-08 | System shall measure peak memory. |
| FR-09 | System shall calculate EcoScore. |
| FR-10 | System shall generate a report. |

### Non-Functional Requirements

| ID | Requirement |
|---|---|
| NFR-01 | Analysis jobs shall execute in an isolated environment. |
| NFR-02 | Dashboard APIs shall respond within 500 ms under defined test workload. |
| NFR-03 | Passwords shall be securely hashed. |
| NFR-04 | Analysis history shall be retained. |
| NFR-05 | Analysis failures shall not crash the main service. |

### Roles

| Role | Permissions |
|---|---|
| **Developer** | Run analysis, view own projects, compare versions |
| **Team Lead** | View team dashboard, approve optimization goals, track improvements |
| **Administrator** | Manage rules, manage users, manage benchmark policies |

## UML Overview

**Use-case actors:** Developer, Team Lead, Administrator

**Use cases:** Create Project · Connect Repository · Run Analysis · View Warning · Compare Versions · Set Performance Budget · Generate Report · Manage Rules

**Key classes:** `User`, `Project`, `Repository`, `AnalysisRun`, `Metric`, `StaticIssue`, `Rule`, `Benchmark`, `PerformanceBudget`, `Commit`, `Report`

```
Project
   │
   ├── AnalysisRuns
   │       ├── Metrics
   │       └── Issues
   │
   ├── PerformanceBudgets
   └── Repository
```

**Analysis sequence:**

```
Developer → Frontend → AnalysisController → AnalysisService
                                                 ├── StaticAnalyzer
                                                 └── Profiler → Sandbox → Metrics → Database
```

## Testing Strategy

| Level | Example |
|---|---|
| **Unit** | `givenDatabaseCallInsideLoop_shouldProduceEC001()` |
| **Integration** | AnalysisService + Database + Static Analyzer |
| **System** | Upload/select project → run analysis → receive metrics → view warning → compare runs |
| **Black-box** | Feed known sample code; expect EC001 detected |
| **White-box** | Test individual branches inside the analyzer |

**TDD example for EC001 (database call inside loop):**

1. DB call inside loop → should detect
2. DB call outside loop → should not detect
3. Ordinary method inside loop → should not detect

Write the three tests first (they fail), implement the analyzer until they pass, then refactor.

## Project Roadmap

### Prototype 1 (W7)
- Login
- Project management
- Upload/select source code
- Java static analysis
- 5–8 analysis rules
- Issue dashboard with severity levels
- Analysis history
- Basic report

### Prototype 2 (W15)
- Runtime benchmarking (CPU/time/memory metrics)
- Version comparison
- 15+ analysis rules
- Performance budgets
- GitHub integration
- EcoScore

### Final Version (W17)
- CI/CD analysis and performance regression detection
- Improved sandboxing
- Database/API metrics
- PDF/HTML reports
- Optimized UI
- Automated test suite
- Before-vs-after case study

## Final Demo Script

Start with a deliberately inefficient sample application:

```
ECOCODE ANALYSIS
EcoScore: 49/100
HIGH 4   MEDIUM 7   LOW 5
Runtime:       1.74 sec
Memory:        242 MB
DB Queries:    82
Payload:       1.4 MB

Warnings:
EC001 DB call inside loop
EC004 Unbounded query
EC008 Missing pagination
EC010 Duplicate computation
```

Then show the optimized branch and compare:

```
                BEFORE       AFTER
EcoScore          49           84
Runtime          1.74 s       0.52 s
Memory           242 MB       167 MB
DB Queries        82            9
Payload           1.4 MB      288 KB

Improvements:
Runtime      ↓ 70%
Memory       ↓ 31%
DB queries   ↓ 89%
Payload      ↓ 79%
```

## How EcoCode Differs from a Normal Code Analyzer

| Typical static analyzer focuses on | EcoCode focuses on |
|---|---|
| Bugs | Computational efficiency |
| Code style | Resource consumption |
| Security | Performance regressions |
| Code smells | Green coding practices |

**Key differentiator:** EcoCode combines static code analysis with actual runtime measurements and historical before-vs-after comparisons.

## Scope Guidance

Avoid an unrealistic pitch like *"AI will read any code in any language and automatically optimize everything."* A stronger, defensible scope for a semester-length project:

- One supported language (Java)
- 15 well-defined static rules
- Runtime benchmarking
- Version comparison
- Performance budgets
- GitHub integration

Each of these is independently testable and demonstrable.

## One-Sentence Summary

> EcoCode is a green-software analysis platform that evaluates source code and running applications for CPU, memory, database, network, and execution inefficiencies, identifies optimization opportunities, and compares software versions to measure whether code changes actually improve resource efficiency.

**In plain terms:** it's a health check-up for software — it finds where a program wastes computing resources and shows whether the developer's fixes actually made it faster and lighter.

---

*Recommended scope for a 3-person team: Java static analysis + controlled runtime profiling + before/after comparison + performance budgets — challenging enough to be taken seriously, achievable within one semester.*
