import os
from Crypto.Cipher import AES

KEY = 

class CustomCounter(object):
    def __init__(self, value=os.urandom(16), step_up=False):
        self.value = value.hex()
        self.step = 1
        self.stup = step_up

    def increment(self):
        if self.stup:
            self.new_value = hex(int(self.value, 16) + self.step)
        else:
            self.new_value = hex(int(self.value, 16) - self.step)
        self.value = self.new_value[2:]
        return bytes.fromhex(self.value.zfill(32))

    def __repr__(self):
        self.increment()
        return self.value


def decrypt():
    cipher = AES.new(KEY, AES.MODE_ECB)
    ctr = CustomCounter()
    output = []
    with open("chall.txt", 'r') as file:
        encrypted_data = file.read().strip()
        encrypted_bytes = bytes.fromhex(encrypted_data)
        block_size = 16
        num_blocks = len(encrypted_bytes) // block_size

        for i in range(num_blocks):
            block_start = i * block_size
            block_end = block_start + block_size
            encrypted_block = encrypted_bytes[block_start:block_end]
            keystream = cipher.encrypt(ctr.increment())
            decrypted_block = bytes(x ^ y for x, y in zip(encrypted_block, keystream))
            output.append(decrypted_block)

    with open("flag.png", 'wb') as output_file:
        output_file.write(b''.join(output))

    return "Decryption complete. Original flag.png file saved."

print(decrypt())
