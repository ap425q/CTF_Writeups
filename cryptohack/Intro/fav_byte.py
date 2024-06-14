flag = '0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104'


ct = bytes.fromhex(flag)

for i in range(128):
	if(ct[2] ^ i == 121):
		print(f"Found the byte {i}")
		break

out = b""

for j in ct:
	out += bytes([j^i])

print(out.decode())