# Talleres Florida - Repair Shop Management System

## Overview
**Talleres Florida** is a console-based Java application designed to simulate a workshop management system for repairs and services. The application allows users to manage various types of work orders, including reviews, mechanical repairs, and bodywork/painting repairs. The program keeps track of ongoing, completed, and canceled jobs, and it also provides a simple interface for modifying and finalizing tasks.

The system is backed by file persistence, meaning the data is saved to a file (`trabajos.dat`), allowing the information to be retrieved even after closing the application. This project is perfect for practicing concepts in Java, such as object-oriented programming, file handling, and user interaction through the console.

All interactions and functionality occur in the **console**.

## Purpose:
This project is a great way for developers to practice basic Java programming, focusing on:

  - Object-Oriented Programming (OOP): Using classes, inheritance, and polymorphism to manage different types of jobs (e.g., Revision and Reparacion).
  - File Handling: Storing and retrieving data from files using ObjectOutputStream and ObjectInputStream.
  - User Interaction: Implementing a simple console-based menu system to interact with the user.
  - Array Management: Using arrays to store and manipulate job data efficiently.
  - Error Handling: Using try-catch blocks to handle input errors and ensure the program runs smoothly.

## Features
- **Add a Job**: Users can add new jobs to the system by specifying the type of work (review, mechanical repair, or bodywork/painting repair), and entering details like work hours and material costs.
- **Search for Jobs**: Users can search for jobs based on their type (reviews, mechanical repairs, bodywork/painting repairs).
- **Modify a Job**: Users can modify existing jobs by adding additional hours or material costs.
- **Finalize and Cancel Jobs**: Jobs can be marked as completed or canceled.
- **List Jobs**: Users can view all jobs in the system or filter to see only pending jobs.
- **Total Jobs Count**: Users can see the total number of jobs completed.
- **File Persistence**: All job data is stored in a file (`trabajos.dat`), and the system loads and saves the data automatically when the program starts and ends.

## Main Operations in the Program
- **Add Work Order**: Adds a new job, specifying the type of work, material costs, and hours worked.
- **Search Jobs**: Allows users to search for jobs by type (reviews, mechanical repairs, bodywork).
- **Modify Job**: Modify a job by adding more hours or material costs.
- **Finalize Job**: Mark a job as completed.
- **Cancel Job**: Cancel a job by removing it from the list.
- **List Jobs**: List all jobs in the system.
- **Pending Jobs**: List all jobs that are still pending.
- **Show Total Jobs**: Display the total number of jobs completed.
- **Save Data**: Automatically saves the data to a file when the user exits the program.

## Methods Implemented
- **`main(String[] args)`**: Main entry point. Displays a menu, handles user input, and runs the core functionality.
- **`agregarTrabajoAlista(Trabajo trabajo)`**: Adds a job to the list, or shifts jobs to make room if the limit is reached.
- **`buscarTrabajo(int tipoTrabajoBusqueda)`**: Searches for jobs based on the selected type.
- **`modificarTrabajo(int indiceListaTrabajo, double horasAgregar, double costeMateriales)`**: Modifies a job by adding additional hours and material costs.
- **`finalizarTrabajo(int indiceListaTrabajo)`**: Finalizes a job, marking it as completed.
- **`cancelarTrabajo(int indiceListaTrabajo)`**: Cancels a job, removing it from the list.
- **`listarTrabajos()`**: Lists all jobs.
- **`listarTrabajosPendientes()`**: Lists only pending jobs.
- **`mostrarTotalTrabajos()`**: Displays the total number of completed jobs.
- **`guardarEnFichero()`**: Saves job data to a file (`trabajos.dat`).
- **`recuperarDelFichero()`**: Loads job data from the file when the program starts.
- **`arranqueDelPrograma()`**: Checks if the job file exists, loads it if available or creates it if not.
