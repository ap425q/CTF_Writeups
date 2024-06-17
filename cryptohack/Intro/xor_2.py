from pwn import *

flag = "0e0b213f26041e480b26217f27342e175d0e070a3c5b103e2526217f27342e175d0e077e263451150104"

flag = bytes.fromhex(flag)

key = xor(flag[:7],b"crypto{") + xor(flag[-1],b'}')

print(key)

print(xor(key,flag))


