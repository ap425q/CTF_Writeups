encrypted_flag = "982a9290d6d4bf88957586bbdcda8681de33c796c691bb9fde1a83d582c886988375838aead0e8c7dc2bc3d7cd97a4"
known_prefix = "uoftctf{"

# Convert hex string to bytes
encrypted_flag_bytes = bytes.fromhex(encrypted_flag)

# XOR the known prefix with the first 8 bytes of the encrypted flag
xor_result = [encrypted_flag_bytes[i] ^ ord(known_prefix[i]) for i in range(len(known_prefix))]

# Convert the result back to bytes
xor_key = bytes(xor_result)

print("XOR Key:", xor_key.hex())

def xor(message, key):
    return bytes([message[i] ^ key[i % len(key)] for i in range(len(message))])


xor_key = bytes.fromhex("ed45f4e4b5a0d9f3")  # Replace this with the XOR key you obtained

# Convert hex string to bytes
encrypted_flag_bytes = bytes.fromhex(encrypted_flag)

# XOR the entire encrypted flag with the XOR key
decrypted_flag = xor(encrypted_flag_bytes, xor_key).decode()

print("Decrypted Flag:", decrypted_flag)