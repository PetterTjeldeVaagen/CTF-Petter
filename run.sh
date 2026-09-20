#!/bin/bash

chmod +x backend/mvnw

if [ ! -d "ctf/node_modules" ]; then
    echo "Installing frontend dependencies..."
    (cd ctf && npm install)
fi

echo "Starting Spring Boot backend..."
(cd backend && ./mvnw -q spring-boot:run) &
BACKEND_PID=$!

echo "Starting Vue frontend..."
(cd ctf && npm run dev) &
VUE_PID=$!

cleanup() {
    echo "Stopping servers..."
    kill "$BACKEND_PID" "$VUE_PID" 2>/dev/null
}

trap cleanup EXIT INT TERM

wait