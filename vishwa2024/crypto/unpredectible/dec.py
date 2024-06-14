class challenge1:
    def level1_decrypt(self, message, key):
        decrypted = ""
        for char in message:
            if char.isalpha():
                shifted = ord(char) - key
                if char.islower():
                    if shifted > ord('z'):
                        shifted -= 26
                    elif shifted < ord('a'):
                        shifted += 26
                elif char.isupper():
                    if shifted > ord('Z'):
                        shifted -= 26
                    elif shifted < ord('A'):
                        shifted += 26
                decrypted += chr(shifted)
            else:
                decrypted += char
        return decrypted

    def level2_decrypt(self, message, key):
        decrypted = ""
        for char in message:
            charInt = ord(char)
            res = charInt ^ key
            decrypted += chr(res)
        return decrypted

# Encrypted string
encrypted_string = "TgwhsoMVJd,|mxqzv>/|@x+|l{}gp,lb"

# Brute force level 2 key
for level2_key in range(1, 101):  # Try all possible keys
    decrypt = challenge1()
    decrypted_message_level2 = decrypt.level2_decrypt(encrypted_string, level2_key)
    
    # Brute force level 1 key
    for level1_key in range(1, 27):  # Try all possible keys
        original_flag = decrypt.level1_decrypt(decrypted_message_level2, level1_key)
        print(f"Level 1 Key: {level1_key}, Level 2 Key: {level2_key}, Decrypted Message: {original_flag}")
