from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad
from Crypto.Util.number import long_to_bytes
from hashlib import sha256

def decrypt_with_key_iv(ciphertext, key, iv):
    cipher = AES.new(key, AES.MODE_CBC, iv)
    plaintext = cipher.decrypt(ciphertext)
    return unpad(plaintext, AES.block_size)

# Given prime p
p = 0xdd6cc28d
g = 0x83e21c05
A = 0xcfabb6dd
B = 0xc4a21ba9

# Brute force 'a' and 'b'
for a in range(1,p):
    for b in range(1,p):
        if pow(A, b, p) == pow(B, a, p):
            print("Found matching values for 'a' and 'b':", a, b)
            C = pow(A, b, p)
            shared_secret = long_to_bytes(C)
            print("Shared Secret (C):", shared_secret.hex())
            break
    else:
        continue
    break

# Derive the AES key
key = sha256(shared_secret).digest()[:16]

# Reusing the IV provided in the challenge
iv = b'\xc1V2\xe7\xed\xc7@8\xf9\\\xef\x80\xd7\x80L*'

# Provided ciphertext
ciphertext = b'\x94\x99\x01\xd1\xad\x95\xe0\x13\xb3\xacZj{\x97|z\x1a(&\xe8\x01\xe4Y\x08\xc4\xbeN\xcd\xb2*\xe6{'

# Decrypt the ciphertext
plaintext = decrypt_with_key_iv(ciphertext, key, iv)

print("Decrypted plaintext:", plaintext.decode())
