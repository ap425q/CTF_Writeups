from Crypto.Util.number import *
import random

p = getPrime(1024)
FLAG = b'----Redatcted----'

g = random.randint(0,p**2)
k = bytes_to_long(FLAG)
assert k < p

chall = pow(g,k,p**2)

print(f'g={g}')
print(f'p={p}')
print(f'chall={chall}')