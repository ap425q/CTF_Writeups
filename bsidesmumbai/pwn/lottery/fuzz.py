#!usr/bin/env/python3

from pwn import *

p = remote("143.110.176.49", 1024)

print(p.recvline())
print(p.recvline())
print(p.recvline())
print(p.recvuntil('? '))
p.sendline(b"20")
for i in range(20):
	print(p.recvuntil(':'))
	p.sendline(b"3 1 5 3 3")
	print(p.recvline())