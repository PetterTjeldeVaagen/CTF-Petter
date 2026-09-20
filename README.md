## Prerequisites

- Java 21+ (JDK) and Maven — or just use the included `mvnw`/`mvnw.cmd` wrapper, no separate Maven install needed
- Node.js 18+ and npm

## Setup

Install the frontend dependencies (only needed once):

```bash
cd ctf
npm install
cd ..
```

## Running

### Linux / macOS

From the repository root, run the provided script. It starts the Spring Boot backend on `http://localhost:8000` and the Vite dev server on `http://localhost:5173`.

```bash
chmod +x run.sh   # only needed the first time
./run.sh
```

Press `Ctrl+C` to stop both servers.

### Windows

From the repository root, run:

```powershell
run.bat
```

This opens the Spring Boot backend and the Vite dev server each in their own window. Close a window (or press `Ctrl+C` in it) to stop that server.

## Usage

Open the frontend URL printed by Vite (typically `http://localhost:5173`) in your browser. The database is created and seeded automatically on backend startup (`database/database.sqlite`).
