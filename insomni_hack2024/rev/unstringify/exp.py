from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad
from Crypto.Util.strxor import strxor
from Crypto.Util.number import long_to_bytes

# Key in hex format
key_hex = "03005A19005B025903000115095C5E015342160D064D0D4252273A420B630F54"

# Encrypted data in hex format
encrypted_data_hex = "51CC0FBEA0262A146D758930ECFD461C0FA3B4798B62C90668EA03BE2AC7F2C4"

# Convert hex strings to bytes
key = bytes.fromhex(key_hex)
encrypted_data = bytes.fromhex(encrypted_data_hex)

# Create an AES cipher object with ECB mode
cipher = AES.new(key, AES.MODE_ECB)

# # Decrypt the data
# decrypted_data = cipher.decrypt(encrypted_data)

# # Print the result in hex format
# print("Decrypted Data:", decrypted_data.hex())

encrypt = cipher.encrypt()