from Crypto.Util.number import long_to_bytes, bytes_to_long, isPrime
from Crypto.PublicKey import RSA

def next_prime(x):
    while not isPrime(x):
        x += 1
    return x

def recover_primes(n, e, nbits):
    # Try possible p values
    for p_candidate in range(2**(nbits // 2 - 1), 2**(nbits // 2)):
        if isPrime(p_candidate):
            pbits = p_candidate.bit_length()
            for r in range(2, pbits):
                mask = 2**(pbits-r-1)
                base = p_candidate ^ mask
                q_candidate = next_prime(base)
                if p_candidate * q_candidate == n:
                    return p_candidate, q_candidate
    raise ValueError("Failed to recover primes")

def decrypt(ct, p, q, e):
    n = p * q
    phi = (p - 1) * (q - 1)
    d = pow(e, -1, phi)
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

# Define the bit length used for the key generation
nbits = 1024

# Recover the primes p and q
p, q = recover_primes(n, e, nbits)

# Decrypt the ciphertext
flag = decrypt(ct, p, q, e)
print("Decrypted flag:", flag.decode())
