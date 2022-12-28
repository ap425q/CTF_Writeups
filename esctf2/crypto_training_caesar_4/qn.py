from Crypto.PublicKey.ECC import EccPoint
import string
import random


# NIST P-256
Gx = 0x6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296
Gy = 0x4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5
G_b = EccPoint(Gx,Gy)
p = 0xffffffff00000000ffffffffffffffffbce6faada7179e84f3b9cac2fc632551

secret = 1 #the value used is a different secret
char_map = {f:ord(f)*G_b for f in string.printable}

flag = 'dummy flag'

def enc(pt):
	ct = [char_map[c]*secret for c in pt]
	return ct

def get_point(point):
	return (int(point.x),int(point.y))

ct = enc(flag)
print([get_point(c) for c in ct])
