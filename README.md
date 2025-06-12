# Yearbook Inventory System

A console-based Java application for managing yearbook photography equipment—cameras, lenses, camera bags, and sign-outs. Built as a school project in spring 2025.

**Programmer:** Pierre Ikladious  
**Created:** May 30, 2025  
**Last revised:** Thursday, June 12, 2025

## Overview

The app presents a text menu that lets you browse inventory, add or remove gear, assemble camera bags, and track what has been signed out and returned. Data is persisted in plain `.dat` files on disk (no database).

## Features

- **Cameras & lenses** — View, add, and delete entries
- **Camera bags** — Create kits, edit contents, and remove bags
- **Sign-out / return** — Check out a bag and record returns with timestamps
- **File persistence** — `FileManager` reads and writes line-based data files

## Requirements

- Java JDK 8 or newer

## How to run

From the project directory:

```bash
javac *.java
java YearbookIkl
```

Follow the numbered menu in the terminal. Enter `0` to exit.

## Project structure

| File | Role |
|------|------|
| `YearbookIkl.java` | Main entry point and menu loop |
| `CameraManager.java` | Camera inventory operations |
| `LensManager.java` | Lens inventory operations |
| `BagManager.java` | Camera bag CRUD and editing |
| `SignOutManager.java` | Sign-out and return workflow |
| `FileManager.java` | Shared file I/O helpers |
| `*.dat` | Sample/persisted data (cameras, lenses, bags, sign-outs) |

## Data files

- `cameras.dat` — Camera records
- `lenses.dat` — Lens records
- `bags.dat` — Camera bag definitions
- `signedout.dat` — Active and historical sign-outs

## License

Personal/educational project. Use and modify as you like.
