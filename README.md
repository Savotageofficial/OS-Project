# **Algorithm Comparison Project**

[![No AI](https://custom-icon-badges.demolab.com/badge/No%20AI-2f2f2f?logo=non-ai&logoColor=white)](https://github.com/Savotageofficial/OS-Project)
[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/)
[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](https://www.java.com/)
[![JavaFX](https://img.shields.io/badge/JavaFX-v17.0.10-52d3aa)](https://openjfx.io/)
[![Linux](https://img.shields.io/badge/Linux-FCC624?logo=linux&logoColor=black)](https://www.linux.org/)
[![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?logo=ubuntu&logoColor=white)](https://ubuntu.com/)
[![Debian](https://img.shields.io/badge/Debian-A81D33?logo=debian&logoColor=fff)](https://www.debian.org/)
[![Windows](https://custom-icon-badges.demolab.com/badge/Windows-0078D6?logo=windows11&logoColor=white)](https://www.microsoft.com/windows)
[![Git](https://img.shields.io/badge/Git-F05032?logo=git&logoColor=fff)](https://git-scm.com/)

A JavaFX desktop application that simulates and compares classic **CPU scheduling algorithms**, built as an Operating Systems course project. The app lets you input custom processes and instantly see side-by-side metrics — waiting time, turnaround time, and response time — so you can understand the trade-offs between each algorithm at a glance.

---

## **Team Members**

| Name | GitHub |
|------|--------|
| Ahmed Mohamed | https://github.com/3bsalam6 |
| Mohamed Safwat | https://github.com/Savotageofficial |
| Adham Mohamed | https://github.com/adham0gad |
| Abdelrahman Ahmed | https://github.com/abdoahmed98710 |
| Asmaa Saad | https://github.com/Asmaa1167 |
| Tasneem Kadry | https://github.com/T-oso |
| Jana Kareem | https://github.com/janapola115-oss |
| Mennat Allah Mahmoud | https://github.com/XXMennahMahmoudXX |

---

## **Core Features**

### **Accurate Comparisons**
- Computes **average waiting time**, **turnaround time**, and **response time** for each algorithm to give a fair, data-driven comparison.
- Results are displayed side-by-side so you can immediately see which algorithm performs best for your workload.

### **Supported Scheduling Algorithms**
- **SRTF** — Shortest Remaining Time First (preemptive)
- **Round Robin** — with configurable time quantum

### **JavaFX User Interface**
- Clean, interactive GUI built with JavaFX 17.
- Input processes (arrival time, burst time, priority) through on-screen forms.
- Gantt chart visualization to track process execution order over time.
- Live metrics table updated after each simulation run.

---

## **Prerequisites**

| Requirement | Version |
|-------------|---------|
| Java JDK | 17 or higher |
| JavaFX SDK | 17.0.10 |
| IntelliJ IDEA | 2022.x or higher (recommended) |

---

## **Getting Started**

### 1. Clone the repository

```bash
git clone https://github.com/Savotageofficial/OS-Project.git
cd OS-Project
```

### 2. Set up JavaFX

Download the [JavaFX SDK 17.0.10](https://gluonhq.com/products/javafx/) for your OS and extract it somewhere on your machine (e.g. `~/javafx-sdk-17.0.10`).

### 3. Open in IntelliJ IDEA

1. Open the project folder in IntelliJ IDEA.
2. Go to **File → Project Structure → Libraries** and add the `lib` folder from your JavaFX SDK.
3. Go to **Run → Edit Configurations** and add the following VM options:

```
--module-path /path/to/javafx-sdk-17.0.10/lib --add-modules javafx.controls,javafx.fxml
```

Replace `/path/to/javafx-sdk-17.0.10` with your actual JavaFX SDK path.

### 4. Run the application

Run the `Main` class from IntelliJ, or build and run from the terminal:

```bash
java --module-path /path/to/javafx-sdk-17.0.10/lib \
     --add-modules javafx.controls,javafx.fxml \
     -cp out/production/OS-Project Main
```

---

## **How to Use**

1. **Add Processes** — Enter each process's arrival time, burst time, and (optionally) priority using the input form.
2. **Set Time Quantum** — If Round Robin is selected, enter your desired time quantum.
3. **Select an Algorithm** — Choose one or more scheduling algorithms from the dropdown or checkbox list.
4. **Run Simulation** — Click **Compare** to simulate all selected algorithms.
5. **View Results** — The Gantt chart and metrics table will update with average waiting, turnaround, and response times for each algorithm.

---

## **Project Structure**

```
OS-Project/
├── src/
│   ├── algorithms/       # Scheduling algorithm implementations
│   ├── model/            # Process and simulation data models
|   ├── ..../             # Controllers
│   └── Main.java         # Application entry point
├── resources/
│   └── com.example.osproject/           # CSS stylesheets and FXML VIEWS
|         
└── README.md
```

---

## **License**

This project was developed for academic purposes as part of an Operating Systems course. All code was written by the team members listed above — no AI tools were used in its development.
