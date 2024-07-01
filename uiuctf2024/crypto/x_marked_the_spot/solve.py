import itertools

# Read the ciphertext from the file
with open("ct", "rb") as ct_file:
    ct = ct_file.read()

# Initialize the charset for brute-forcing (assuming ASCII printable characters)
charset = b"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-={}[]|:;'<>,.?/~`"

# Define the length of the key
key_length = 8

# Function to decrypt the ciphertext using a given key
def decrypt(ct, key):
    return bytes(c ^ k for c, k in zip(ct, itertools.cycle(key)))

# Brute-force the key
for key in itertools.product(charset, repeat=key_length):
    key = bytes(key)
    flag = decrypt(ct, key)
    if flag.startswith(b"uiuctf{"):
        print(f"Key found: {key}")
        print(f"Flag: {flag.decode()}")
        break
else:
    print("Key not found.")
