<?php
$target_dir = "uploads/";
$timestamp = time(); // Get current timestamp
$extension = strtolower(pathinfo($_FILES["fileToUpload"]["name"], PATHINFO_EXTENSION)); // Get file extension
$target_file = $target_dir . $timestamp . '.' . $extension; // Concatenate timestamp and extension to create unique filename
$uploadOk = 1;

// Check if file already exists
if (file_exists($target_file)) {
    echo "Sorry, a file with the same name already exists.";
    $uploadOk = 0;
}

// Check file size
if ($_FILES["fileToUpload"]["size"] > 500000) {
    echo "Sorry, your file is too large.";
    $uploadOk = 0;
}

// Allow only certain file formats
$allowedExtensions = array("doc", "docx", "pdf");
if (!in_array($extension, $allowedExtensions)) {
    echo "Sorry, only doc, docx, and pdf files are allowed.";
    $uploadOk = 0;
}

// Check if $uploadOk is set to 0 by an error
if ($uploadOk == 0) {
    echo "Your file was not uploaded.";
// if everything is ok, try to upload file
} else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
        echo "The file ". htmlspecialchars(basename($target_file)). " has been uploaded.";
    } else {
        echo "Sorry, there was an error uploading your file.";
    }
}
