import gmpy2
from Crypto.Util.number import long_to_bytes, bytes_to_long, inverse
from Crypto.PublicKey import RSA
from gmpy2 import mpz, isqrt, is_square

def fermat_factor(n):
    a = gmpy2.isqrt(n) + 1
    b2 = a * a - n
    while not gmpy2.is_square(b2):
        a += 1
        b2 = a * a - n
    b = gmpy2.isqrt(b2)
    p = a + b
    q = a - b
    return int(p), int(q)

def decrypt(ct, p, q, e):
    n = p * q
    phi = (p - 1) * (q - 1)
    d = inverse(e, phi)
    m = pow(bytes_to_long(ct), d, n)
    return long_to_bytes(m)

# Read the public key
with open('pub.pem', 'rb') as f:
    pubkey = RSA.import_key(f.read())
    n = pubkey.n
    e = pubkey.e

# Read the ciphertext
with open('flag.txt.enc', 'rb') as f:
    ct = f.read()
    c = bytes_to_long(ct)

# Print n, e, and c
print(f"n: {n}")
print(f"e: {e}")
print(f"c: {c}")

# Factorize n using Fermat's method
p, q = fermat_factor(n)

# Print the recovered primes
print(f"p: {p}")
print(f"q: {q}")

# Decrypt the ciphertext
flag = decrypt(ct, p, q, e)
print("Decrypted flag:", flag.decode())
