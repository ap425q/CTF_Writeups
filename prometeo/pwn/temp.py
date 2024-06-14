#!usr/bin/env/python3

from pwn import *

# p = remote("159.65.24.125",31077)

elf = ELF('./vuln')

p = process('./vuln')

# gdb.attach(p,gdbscript=''' b *0x080491e2 ''')

offset = 

buf = b"A"*offset + p64(elf.symbols[''])

p.recvline()

p.sendline(buf)

p.interactive()