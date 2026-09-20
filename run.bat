@echo off
if not exist "ctf\node_modules" (
    echo Installing frontend dependencies...
    call npm install --prefix ctf
)

echo Starting Spring Boot backend...
start "CTF Backend" cmd /k "cd backend && mvnw.cmd spring-boot:run"

echo Starting Vue frontend...
start "CTF Frontend" cmd /k "cd ctf && npm run dev"

echo Both servers were started in separate windows. Close those windows to stop them.