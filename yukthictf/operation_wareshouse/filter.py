def sanitize_file(file_name):
    # Split the file_name by "../"
    parts = file_name.split("../")
    
    # Rebuild the file_name with the specified replacements
    # The first part is kept as is (since the first "../" is removed),
    # then join the remaining parts with "." (for subsequent "../" replacements)
    sanitized_file_name = parts[0] + ".".join(parts[1:])
    
    # If the original file_name started with "../", we remove the leading part
    if file_name.startswith("../"):
        sanitized_file_name = sanitized_file_name[1:]  # Remove the first character, which is now incorrect
    
    return sanitized_file_name

print(sanitize_file("....//app/venv/lib/python3.8/site-packages/werkzeug/debug/__init__.py"))