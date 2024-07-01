#define _GNU_SOURCE
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/uio.h>
#include <errno.h>

#define BUF_SIZE 1024

int main() {
    // Open the current directory
    int dir_fd = open(".", O_RDONLY | O_DIRECTORY);
    if (dir_fd == -1) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    // Open the flag.txt file in the current directory
    int file_fd = openat(dir_fd, "flag.txt", O_RDONLY);
    if (file_fd == -1) {
        perror("openat");
        close(dir_fd);
        exit(EXIT_FAILURE);
    }

    // Buffer to hold the file contents
    char buffer[BUF_SIZE];
    struct iovec iov[1];
    iov[0].iov_base = buffer;
    iov[0].iov_len = BUF_SIZE;

    // Read the contents of the file
    ssize_t num_read;
    while ((num_read = preadv2(file_fd, iov, 1, 0, 0)) > 0) {
        // Write the contents to the terminal
        ssize_t num_written = pwrite64(STDOUT_FILENO, buffer, num_read, 0);
        if (num_written == -1) {
            perror("pwrite64");
            close(file_fd);
            close(dir_fd);
            exit(EXIT_FAILURE);
        }
    }

    if (num_read == -1) {
        perror("preadv2");
    }

    // Close the file and directory descriptors
    close(file_fd);
    close(dir_fd);

    return 0;
}
