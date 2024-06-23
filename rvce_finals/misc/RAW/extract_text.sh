#!/bin/bash

# Function to recursively extract text from files longer than 20 characters
extract_text_recursive() {
    local search_dir="$1"

    # Check if the directory exists
    if [ ! -d "$search_dir" ]; then
        echo "Error: Directory $search_dir does not exist."
        exit 1
    fi

    # Use find to locate all files and directories and process them
    find "$search_dir" -type f -print0 | while IFS= read -r -d $'\0' file; do
        echo "Processing file: $file"
        
        # Check the file type to determine how to extract text
        file_type=$(file --mime-type -b "$file")
        
        case "$file_type" in
            text/plain)
                # Extract text from plain text file
                extract_long_strings "$(cat "$file")"
                ;;
            application/pdf)
                # Extract text from PDF using pdftotext
                extract_long_strings "$(pdftotext "$file" -)"
                ;;
            *)
                # For other types, attempt to extract text using strings command
                extract_long_strings "$(strings "$file")"
                ;;
        esac
        
        echo "----------------------------------------------"
    done

    # Recursively process directories
    find "$search_dir" -mindepth 1 -type d -print0 | while IFS= read -r -d $'\0' dir; do
        echo "Entering directory: $dir"
        extract_text_recursive "$dir"
    done
}

# Function to extract strings longer than 20 characters
extract_long_strings() {
    local input="$1"

    # Use awk to filter strings longer than 20 characters
    echo "$input" | awk 'length > 20'
}

# Check if correct number of arguments are passed
if [ $# -ne 1 ]; then
    echo "Usage: $0 <directory>"
    exit 1
fi

extract_text_recursive "$1"
