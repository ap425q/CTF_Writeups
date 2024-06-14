from itertools import cycle

def xor(a, b):
    return bytes(i^j for i, j in zip(a, cycle(b)))

enc = open("enc.txt", "rb").read()

# Convert the provided hexadecimal key to bytes
key_hex = "89504E470D0A1A0A"
key = bytes.fromhex(key_hex)

decrypted_data = xor(enc, key)

with open('decrypted.png', 'wb') as f:
    f.write(decrypted_data)
