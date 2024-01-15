from Crypto.Cipher import AES

with open("encrypted", "rb") as f:
    data = f.read()

iv1 = data[:16]
ciphertext1 = data[16:32]
iv2 = data[32:48]
ciphertext2 = data[48:]

plaintext = b"This is top secret message. I hope, no one can intercept UwU !!!"

cipher = AES.new(iv1, AES.MODE_ECB)
decrypted_block = cipher.decrypt(ciphertext1)

key = bytes([a ^ b for a, b in zip(decrypted_block, plaintext)])

print("Recovered IV:", key)


cipher = AES.new(key, AES.MODE_CBC,iv2)
decrypted_block = cipher.decrypt(ciphertext2).rstrip(b"\x00")

print(decrypted_block.decode('latin-1'))