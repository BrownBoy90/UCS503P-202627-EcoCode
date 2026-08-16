# UCS503P-202627-EcoCode

Sure. Idea 3 — EcoCode is actually easier to explain than TraceForge.

EcoCode in layman language

Imagine two programmers write code that gives the same correct answer.

Program A takes:

* 5 seconds
* 300 MB RAM
* high CPU usage

Program B takes:

* 1 second
* 120 MB RAM
* much less CPU usage

Both programs work.

But Program B is more efficient.

EcoCode is a platform that helps developers answer:

“My code works, but is it wasting CPU, memory, database queries, network calls, or energy?”

It analyzes a program, measures how efficiently it runs, and suggests areas that could be improved.

So you can think of EcoCode as:

A fitness tracker for software.

A fitness watch might tell a person:

* heart rate
* calories
* steps
* sleep

EcoCode tells a program:

* execution time
* CPU usage
* memory usage
* database queries
* network traffic
* unnecessary operations
* potential optimization opportunities

⸻

Simple real-world example

Suppose you write this program:

users = [...]
for user in users:
    if user.id in list_of_ids:
        print(user.name)

Suppose list_of_ids has thousands of values.

The code is correct.

But repeatedly searching a normal list can be inefficient.

You improve it:

ids = set(list_of_ids)
for user in users:
    if user.id in ids:
        print(user.name)

Both versions produce the same output.

But EcoCode could show:

BEFORE
Runtime:       4.8 sec
Memory:        95 MB
CPU Time:      4.4 sec
Efficiency:    48/100

After optimization:

AFTER
Runtime:       0.6 sec
Memory:        102 MB
CPU Time:      0.5 sec
Efficiency:    86/100

And explain:

Runtime improved significantly, although memory increased slightly because a hash set was introduced.

That trade-off is important.

EcoCode shouldn’t simply say:

“Lower RAM = always better.”

It should show engineering trade-offs.

⸻

What problem does EcoCode solve?

A lot of developers focus on:

“Does my program give the right answer?”

But production software also needs to answer:

“How much computation does it require?”

For example, imagine an API receives 1 million requests every day.

If one request wastes:

100 ms extra CPU time

then across one million requests that waste becomes significant.

Similarly, imagine an API unnecessarily returns:

2 MB

when the user only needs:

50 KB

The system wastes:

* bandwidth
* server work
* client processing
* battery
* time

So EcoCode tries to identify those inefficiencies.

⸻

The basic workflow

A user comes to your website.

Developer
   ↓
EcoCode

They create a project.

For example:

Project:
Student Management API
Language:
Java
Repository:
github.com/team/student-api

Then EcoCode can analyze either:

source code

and/or

a running application

The system generates a report.

Example:

ECOCODE ANALYSIS
Runtime                         1.81 sec
Peak memory                     184 MB
Average CPU                     63%
Database queries/request         28
Response payload                721 KB
Warnings:
⚠ Repeated database access inside loop
⚠ Large API response
⚠ Duplicate calculations
⚠ Potential inefficient collection lookup
✓ Compression enabled

⸻

EcoCode really has two major parts

This distinction is important.

Part 1 — Static Analysis

You inspect source code without running it.

EcoCode looks for suspicious patterns.

Part 2 — Runtime Profiling

You actually execute or monitor the software and measure:

* CPU
* memory
* execution time
* network usage
* database activity

Combining both makes the project much more substantial.

⸻

1. Static Code Analyzer

Imagine a developer uploads:

for (User user : users) {
    database.getOrders(user.getId());
}

EcoCode could recognize:

⚠ Database operation inside a loop.

Suppose there are 1,000 users.

That might generate:

1000 database queries

instead of perhaps one batched query.

EcoCode reports:

Potential N+1 Query Pattern
File:
UserService.java
Line:
83
Severity:
High
Reason:
A repository/database call occurs inside
an iteration over a collection.
Potential Impact:
Excessive database and CPU usage.

That’s useful software-engineering functionality.

⸻

Another example — repeated computation

Suppose someone writes:

for item in items:
    total = calculate_expensive_value(data)
    process(item, total)

But calculate_expensive_value(data) gives the same answer each time.

EcoCode could recommend:

total = calculate_expensive_value(data)
for item in items:
    process(item, total)

So the computation happens once.

⸻

Another example — inefficient string building

Java example:

String result = "";
for (String word : words) {
    result = result + word;
}

For a large collection, repeated string creation may be inefficient.

EcoCode could say:

⚠ Repeated immutable String concatenation in loop
Consider StringBuilder when appropriate.

Again, the system isn’t rewriting everything magically.

It’s identifying patterns.

⸻

Another example — unnecessary object creation

Suppose:

for (...) {
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");
}

If the formatter does not need to be recreated every iteration, EcoCode can flag it.

Potential repeated object allocation

This relates directly to your syllabus:

Green Coding Guidelines — Optimizing Memory & CPU Utilization.

⸻

You can create a rule engine

Your project can contain a set of rules.

For example:

RULE EC001
Database call inside loop
RULE EC002
Repeated expensive calculation
RULE EC003
Potential inefficient collection lookup
RULE EC004
Excessive nested loops
RULE EC005
Repeated object allocation
RULE EC006
Blocking operation inside request loop
RULE EC007
Large response object
RULE EC008
Missing pagination
RULE EC009
Duplicate API request
RULE EC010
Unbounded data fetch

Each rule can have:

ID
Name
Description
Severity
Category
Recommendation

For example:

EC008
Missing Pagination
Severity: HIGH
Category:
Database / Network
Description:
API may retrieve a large dataset without
pagination.
Recommendation:
Use page/limit parameters.

Now you’ve built a proper analysis engine, not merely a dashboard.

⸻

2. Runtime Profiler

Static analysis tells you:

“This might be inefficient.”

Runtime profiling tells you:

“This actually consumed X resources.”

Suppose somebody wants to benchmark a Java program.

EcoCode runs it in a controlled environment.

Input:

Dataset size:
100,000 records

Results:

Execution time      2.32 sec
Peak memory          281 MB
CPU utilization       78%
Disk read             34 MB
Disk write             3 MB

Then run Version 2.

Execution time      0.91 sec
Peak memory          174 MB
CPU utilization       49%
Disk read             29 MB
Disk write             3 MB

EcoCode compares them.

⸻

Comparison becomes one of your best features

Imagine your professor sees:

              VERSION 1      VERSION 2
Runtime         2.32 s          0.91 s
Memory          281 MB          174 MB
CPU              78%             49%
DB Queries       104              12
Payload          1.8 MB          320 KB

Then:

Runtime improvement:       60.8%
Memory reduction:          38.1%
Database query reduction:  88.5%
Payload reduction:         82.2%

That’s excellent demonstration material because you have measurable evidence.

⸻

What does “Green” mean here?

It doesn’t mean:

“We’ll use a green-colored UI.”

Green software means trying to perform required work with fewer computing resources where practical.

For EcoCode, this could mean reducing:

* unnecessary CPU cycles
* memory usage
* database load
* bandwidth
* repeated network communication
* unnecessary rendering
* excessive disk I/O

If the same task can be done using less computational work, that can potentially reduce energy consumption.

Your syllabus explicitly includes:

Green Coding Guidelines — Optimizing Memory & CPU Utilization

so EcoCode is very naturally connected to the course.

⸻

EcoScore

You could create an EcoScore.

For example:

EcoScore: 74/100

Broken down as:

CPU Efficiency          78/100
Memory Efficiency       71/100
Database Efficiency     53/100
Network Efficiency      82/100
Code Smell Score        76/100

But be careful academically:

Don’t claim:

“74 means exactly 26% carbon waste.”

That would be scientifically weak.

Instead say:

EcoScore is a project-defined comparative efficiency metric based on normalized software-resource indicators.

That’s defendable.

⸻

How might the score work?

For example:

EcoScore =
25% CPU score
+ 20% Memory score
+ 20% Network score
+ 20% Database score
+ 15% Static analysis score

You document the formula.

You could normalize measurements against:

* previous version of the same project
* a defined benchmark
* a baseline execution

That makes it useful for comparisons, rather than pretending to give universal energy truth.

⸻

The most important workflow

Let’s say your team builds a backend endpoint:

GET /students

Prototype 1:

Response time:    980 ms
DB queries:        102
Payload:          2.8 MB
Peak memory:      212 MB

EcoCode identifies:

⚠ No pagination
⚠ Possible N+1 database query
⚠ Excessive response fields

Developers fix the code.

Prototype 2:

Response time:    190 ms
DB queries:          4
Payload:          180 KB
Peak memory:      157 MB

EcoCode then displays:

Improvement detected
Response time       ↓ 80.6%
Database queries    ↓ 96.1%
Payload             ↓ 93.6%
Memory              ↓ 25.9%

This is exactly the type of improvement story you can show from W7 to W17.

⸻

Database Query Analyzer

This could become a major module.

Imagine an application sends:

SELECT * FROM users;

when there are 500,000 users.

EcoCode could flag:

⚠ Potential unbounded query
Large table queried without LIMIT/pagination.

Another pattern:

SELECT *

when the endpoint only needs:

id
name

EcoCode could suggest:

Fetch only required fields where appropriate.

⸻

N+1 query problem

This is an excellent serious feature.

Suppose:

1 query:
Get 100 students
Then:
100 separate queries to get each department.

Total:

101 queries

EcoCode can identify that runtime behavior.

Then after fixing with a JOIN/fetch strategy:

2 queries

You can visually show:

Database Query Analysis
Before: █████████████████████ 101
After:  ██                     2

That’s a very convincing final demo.

⸻

API/network analyzer

Suppose frontend calls:

GET /profile
GET /profile
GET /profile
GET /notifications
GET /profile

during one page load.

EcoCode might detect:

⚠ Duplicate request
/profile requested 4 times in 1 page load.

Possible recommendation:

Cache or reuse already-loaded profile data.

⸻

Large payload detection

Imagine:

GET /students

returns:

5.2 MB

but UI displays only:

Student Name
Roll Number
Department

EcoCode reports:

Payload: 5.2 MB
Displayed fields:
3
Returned fields:
27
Potential over-fetching detected.

Recommendation:

Use projection/DTO to return only necessary fields.

Again, very relevant to green and efficient software architecture.

⸻

Frontend efficiency

You don’t have to restrict EcoCode to backend code.

You could analyze a website.

For example:

Initial page weight     6.8 MB
JavaScript              3.1 MB
Images                  3.2 MB
Requests                  83

Warnings:

⚠ Very large image asset
⚠ Unused JavaScript bundle
⚠ Too many network requests
⚠ Animation running continuously

Then compare optimized version:

Initial page weight     1.7 MB
Requests                  31

This maps to:

Energy-Aware UI/UX Design
Reduced Animations
Low Power UX Patterns

from your syllabus.

⸻

Dark mode — important nuance

You may mention dark mode, but don’t make:

Dark mode = green software

the core argument.

Energy savings depend strongly on display technology and content.

It’s much stronger to focus on measurable things such as:

* fewer bytes transferred
* fewer computations
* fewer queries
* reduced processing
* optimized assets

⸻

Project structure

Your platform could have these main modules:

EcoCode
1. User & Project Management
2. Repository Analysis
3. Static Rule Engine
4. Runtime Profiler
5. API Performance Analyzer
6. Database Analyzer
7. Version Comparison
8. EcoScore Engine
9. Reports & Dashboard

⸻

User accounts

Roles might be:

Developer
Team Lead
Reviewer
Administrator

Developer:

Run analysis
View own projects
Compare versions

Lead:

View team dashboard
Approve optimization goals
Track project improvements

Admin:

Manage rules
Manage users
Manage benchmark policies

That gives you role-based access control.

⸻

Project dashboard

Conceptually:

ECOCODE
──────────────────────────────
Project:
Student Management API
Branch:
main
Latest Analysis:
11 Aug 2026
EcoScore
78 / 100
Runtime
320 ms
Peak Memory
142 MB
DB Queries
14/request
Payload
281 KB

Warnings:

HIGH
2
MEDIUM
7
LOW
11

⸻

Issue page

Click a warning:

EC001 — Database Call Inside Loop
Severity:
HIGH
File:
StudentService.java
Line:
112
Detected Code:
for (Student s : students) {
    repo.findDepartment(s.getDepartmentId());
}

Explanation:

The loop may execute one database
query for every student.

Suggested direction:

Consider fetching required department
data in a batch or using an appropriate
join/fetch strategy.

This gives a professional static-analysis-tool feel.

⸻

Version history

EcoCode should keep every analysis.

For example:

v0.1     EcoScore 52
v0.2     EcoScore 61
v0.3     EcoScore 74
v1.0     EcoScore 86

Click v0.1 → v1.0:

Metric                 v0.1        v1.0
Runtime               1.8 s        0.5 s
Memory               231 MB       151 MB
Queries               64           11
Payload               1.2 MB      310 KB
Warnings              28           12

This naturally demonstrates software evolution, another syllabus topic.

⸻

GitHub integration

Like TraceForge, you can connect GitHub.

When developer pushes:

commit abc123
Optimize student retrieval query

EcoCode can create an analysis run.

Then show:

Commit abc123
Before EcoScore: 71
After EcoScore:  82
Runtime:
621 ms → 301 ms
Queries:
31 → 8

That would be a fantastic advanced feature.

⸻

CI/CD integration

Imagine GitHub Actions runs tests.

Then EcoCode benchmark runs too.

You could define a performance rule:

API response must remain below 500 ms.

Old commit:

420 ms

New commit:

790 ms

EcoCode flags:

❌ PERFORMANCE REGRESSION
Endpoint:
/api/students
Baseline:
420 ms
Current:
790 ms
Regression:
+88%

Now the project starts feeling like a proper developer tool.

⸻

Performance budgets

Allow developers to configure budgets.

Example:

GET /dashboard
Response time       <= 400 ms
Payload             <= 300 KB
Queries             <= 15

Another:

Frontend Home Page
Page weight         <= 2 MB
Requests            <= 40

If violated:

❌ Performance Budget Failed

This is an excellent feature.

⸻

Architecture

I would again use a modular monolith.

                  React Frontend
                        │
                        ▼
                 Spring Boot API
                        │
       ┌────────────────┼────────────────┐
       │                │                │
       ▼                ▼                ▼
 Project Module    Analysis Module   Report Module
                        │
          ┌─────────────┼──────────────┐
          ▼             ▼              ▼
      Static        Profiler        Metrics
      Analyzer                      Engine
          │             │              │
          └─────────────┼──────────────┘
                        ▼
                    PostgreSQL

Then an isolated analysis environment:

EcoCode Backend
      │
      ▼
 Job Queue
      │
      ▼
Sandbox / Container
      │
      ├── Compile
      ├── Execute
      ├── Measure
      └── Return metrics

⸻

Why the sandbox matters

Suppose users upload code.

You must not simply run arbitrary code directly on your main server.

Someone could upload:

while True:
    pass

or malicious code.

Instead run code in a restricted environment.

For example:

CPU limit
Memory limit
Execution timeout
No host filesystem access
Restricted/no network

This itself gives you a great Software Engineering discussion:

* security
* isolation
* fault tolerance
* resource management

For a semester prototype, you could support only your own benchmark projects initially rather than arbitrary untrusted uploads. That’s safer and more achievable.

⸻

Technology stack

For a 3-person team:

Frontend
React + TypeScript
Backend
Spring Boot
Database
PostgreSQL
Code Analysis
JavaParser / AST-based Java analysis
or Python AST for Python
Profiling
Java/JVM tools
Linux process metrics
Containerization
Docker
Testing
JUnit
Mockito
Playwright
CI/CD
GitHub Actions

You don’t need to support 10 languages.

Choose one language initially.

I’d recommend:

Java

because you can build EcoCode in Java and initially analyze Java projects.

Or Python if you want simpler AST analysis.

⸻

Why AST is interesting

Instead of searching text like:

"for"

you parse code into its structural representation.

For example:

for (User u : users) {
    repo.findById(u.getId());
}

becomes conceptually:

ForLoop
 ├── Variable: u
 └── Body
      └── MethodCall
           repo.findById(...)

Your analyzer can ask:

“Is there a repository/database method call inside a loop node?”

That’s far more robust than plain regex.

And it’s definitely appropriate third-year technical work.

⸻

Your SRS becomes substantial

Functional requirements:

FR-01
User shall create a project.
FR-02
User shall connect a repository.
FR-03
System shall run static analysis.
FR-04
System shall classify detected issues by severity.
FR-05
System shall store analysis history.
FR-06
System shall compare two analysis runs.
FR-07
System shall measure runtime.
FR-08
System shall measure peak memory.
FR-09
System shall calculate EcoScore.
FR-10
System shall generate a report.

Non-functional requirements:

NFR-01
Analysis jobs shall execute in an isolated environment.
NFR-02
Normal dashboard APIs shall respond within 500 ms
under defined test workload.
NFR-03
Passwords shall be securely hashed.
NFR-04
Analysis history shall be retained.
NFR-05
Analysis failures shall not crash the main service.

⸻

UML opportunities

EcoCode gives you plenty.

Use-case diagram

Actors:

Developer
Team Lead
Administrator

Use cases:

Create Project
Connect Repository
Run Analysis
View Warning
Compare Versions
Set Performance Budget
Generate Report
Manage Rules

⸻

Class diagram

Classes:

User
Project
Repository
AnalysisRun
Metric
StaticIssue
Rule
Benchmark
PerformanceBudget
Commit
Report

Relationships:

Project
   │
   ├── AnalysisRuns
   │       ├── Metrics
   │       └── Issues
   │
   ├── PerformanceBudgets
   └── Repository

⸻

Sequence diagram

Run analysis:

Developer
   │
   ▼
Frontend
   │
   ▼
AnalysisController
   │
   ▼
AnalysisService
   │
   ├── StaticAnalyzer
   │
   └── Profiler
          │
          ▼
      Sandbox
          │
          ▼
        Metrics
          │
          ▼
       Database

⸻

Testing EcoCode itself

This is important because your course is Software Engineering.

Unit test

Test individual rule:

givenDatabaseCallInsideLoop_shouldProduceEC001()

Integration test

AnalysisService
+
Database
+
Static Analyzer

System test

Upload/select project
↓
Run analysis
↓
Receive metrics
↓
See warning
↓
Compare with another run

Black-box tests

Give known sample code.

Expected:

EC001 detected.

White-box tests

Test branches in the analyzer.

⸻

TDD example

Before implementing:

EC001 Database Call Inside Loop

Write three tests:

1. DB call inside loop → detect
2. DB call outside loop → don't detect
3. Ordinary method inside loop → don't detect

Tests initially fail.

Implement analyzer.

Tests pass.

Refactor.

You’ve demonstrated TDD practically.

⸻

What could W7 prototype contain?

Keep W7 realistic:

✅ Login
✅ Projects
✅ Upload/select source code
✅ Java static analysis
✅ 5–8 analysis rules
✅ Issue dashboard
✅ Severity levels
✅ Analysis history
✅ Basic report

That alone is a legitimate prototype.

⸻

W15 second prototype

Add:

✅ Runtime benchmarking
✅ CPU/time metrics
✅ Memory metrics
✅ Version comparison
✅ 15+ analysis rules
✅ Performance budgets
✅ GitHub integration
✅ EcoScore

⸻

W17 final version

Add:

✅ CI/CD analysis
✅ Performance regression detection
✅ Better sandboxing
✅ Database/API metrics
✅ PDF/HTML reports
✅ Optimized UI
✅ Automated test suite
✅ Before-vs-after case study

⸻

Your final demo

This could be extremely strong.

Start with deliberately inefficient sample application.

Run EcoCode.

ECOCODE ANALYSIS
─────────────────────────
EcoScore: 49/100
HIGH      4
MEDIUM    7
LOW       5
Runtime       1.74 sec
Memory        242 MB
DB Queries     82
Payload       1.4 MB

Warnings:

EC001 DB call inside loop
EC004 Unbounded query
EC008 Missing pagination
EC010 Duplicate computation

Then show your optimized branch.

Press:

Compare

                BEFORE       AFTER
EcoScore          49           84
Runtime          1.74 s       0.52 s
Memory           242 MB       167 MB
DB Queries        82            9
Payload           1.4 MB      288 KB

Then:

Improvements
Runtime          ↓ 70%
Memory           ↓ 31%
DB queries       ↓ 89%
Payload          ↓ 79%

That’s a very satisfying demonstration because everyone can immediately understand the benefit.

⸻

How is it different from a normal code analyzer?

This is an important viva question.

A normal static analyzer might focus on:

bugs
code style
security
code smells

EcoCode’s focus is:

computational efficiency
resource consumption
performance regressions
green coding practices

And your key differentiator is:

It combines static code analysis with actual runtime measurements and historical before-vs-after comparisons.

⸻

Don’t make this mistake

Don’t try to build:

“AI will read any code in any language and automatically optimize everything.”

That becomes vague and unrealistic.

A far stronger 3rd-year project is:

Supported language:
Java
15 well-defined static rules
Runtime benchmarking
Version comparison
Performance budgets
GitHub integration

Each component can be tested and demonstrated scientifically.

⸻

Difficulty compared with TraceForge

Here’s how I’d compare them:

Area	TraceForge	EcoCode
General SE fit	⭐⭐⭐⭐⭐	⭐⭐⭐⭐⭐
Algorithms	⭐⭐⭐	⭐⭐⭐⭐⭐
Systems knowledge	⭐⭐⭐	⭐⭐⭐⭐⭐
Requirements/UML	⭐⭐⭐⭐⭐	⭐⭐⭐⭐
Testing opportunities	⭐⭐⭐⭐⭐	⭐⭐⭐⭐⭐
Green Software relevance	⭐⭐⭐⭐	⭐⭐⭐⭐⭐
Implementation difficulty	8/10	9/10
Novelty	8.5/10	9.5/10
Demo wow-factor	9/10	9.5/10

TraceForge

Safer and easier to finish.

EcoCode

More technically challenging, but potentially more impressive.

⸻

One-sentence explanation to your professor

If your professor asks:

“What exactly are you building?”

Say:

EcoCode is a green-software analysis platform that evaluates source code and running applications for CPU, memory, database, network and execution inefficiencies, identifies optimization opportunities, and compares software versions to measure whether code changes actually improve resource efficiency.

And in pure layman terms:

It is like a health check-up for software: it finds where a program is wasting computing resources and shows whether the developer’s fixes actually made it faster and lighter.

For a 3-person third-year team, I would scope EcoCode around Java static analysis + controlled runtime profiling + before/after comparison + performance budgets. That is difficult enough to be taken seriously while still being buildable within one semester.