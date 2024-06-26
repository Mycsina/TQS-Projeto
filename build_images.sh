#!/bin/bash

# Define the directories containing Dockerfiles
api="./backend"
nextjs="./frontend"
nginx="./nginx"

api_name="quickserve-api"
nextjs_name="quickserve-frontend"
nginx_name="nginx"

# Function to build Docker images
build_images() {
    local directory="$1"
    local image_name="$2"
    echo "Building Docker image in $directory"
    docker build -t "$image_name":latest "$directory"
}

# Function to build all Docker images
build_all_images() {
    build_images "$api" "$api_name"
    build_images "$nextjs" "$nextjs_name"
    build_images "$nginx" "$nginx_name"
}   

# Check command line argument
if [ "$1" == "all" ]; then
    build_all_images
elif [ "$1" == "api" ]; then
    build_images "$api" "$api_name"
elif [ "$1" == "nextjs" ]; then
    build_images "$nextjs" "$nextjs_name"
elif [ "$1" == "nginx" ]; then
    build_images "$nginx" "$nginx_name"
else
    echo "Invalid argument. Usage: $0 [all | api | nextjs | nginx]"
    exit 1
fi