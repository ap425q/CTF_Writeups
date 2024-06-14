section .data
    plaintext   db  0x6C, 0x65, 0x34, 0x35, 0x65, 0x66, 0x34, 0x7D, 0x64, 0x65, 0x35, 0x6E, 0x30, 0x6E, 0x72, 0x65
                db  0x79, 0x35, 0x30, 0x62, 0x65, 0x72, 0x35, 0x69, 0x49, 0x4E, 0x53, 0x7B, 0x72, 0x75, 0x6D, 0x6D
    key         db  0x00, 0x03, 0x19, 0x5A, 0x5B, 0x00, 0x59, 0x02, 0x00, 0x03, 0x15, 0x01, 0x5C, 0x09, 0x01, 0x5E
                db  0x42, 0x53, 0x0D, 0x16, 0x4D, 0x06, 0x42, 0x0D, 0x27, 0x52, 0x42, 0x3A, 0x63, 0x0B, 0x54, 0x0F
    ciphertext  db  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

section .text
    global _start

_start:
    ; Print plaintext before encryption
    mov rsi, plaintext
    mov rdx, 32  ; length of plaintext
    mov rax, 1   ; syscall: write
    mov rdi, 1   ; file descriptor: STDOUT
    syscall

    ; Add newline
    mov rax, 1
    mov rdi, 1
    mov rsi, newline
    mov rdx, 1
    syscall

    ; Load plaintext into YMM0
    vmovups ymm0, [plaintext]

    ; Load key into YMM1
    vmovups ymm1, [key]

    ; AES encryption using vaesenc
    vaesenc ymm0, ymm1

    ; Store ciphertext
    vmovups [ciphertext], ymm0

    ; Print ciphertext after encryption
    mov rsi, ciphertext
    mov rdx, 32  ; length of ciphertext
    mov rax, 1   ; syscall: write
    mov rdi, 1   ; file descriptor: STDOUT
    syscall

    ; Add newline
    mov rax, 1
    mov rdi, 1
    mov rsi, newline
    mov rdx, 1
    syscall

    ; AES decryption using vaesdec
    vaesdec ymm0, ymm1

    ; Print plaintext after decryption
    mov rsi, plaintext
    mov rdx, 32  ; length of plaintext
    mov rax, 1   ; syscall: write
    mov rdi, 1   ; file descriptor: STDOUT
    syscall

    ; Add newline
    mov rax, 1
    mov rdi, 1
    mov rsi, newline
    mov rdx, 1
    syscall

    ; Exit the program
    mov rax, 60         ; syscall: exit
    xor rdi, rdi        ; status: 0
    syscall

section .bss
    newline resb 1
