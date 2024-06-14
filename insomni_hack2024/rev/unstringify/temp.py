from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad
import binascii

# Given key and ciphertext in hexadecimal format
key_hex = "0003195A5B005902000315015C09015E42530D164D06420D2752423A630B540F"
ciphertext_hex = "51CC0FBEA0262A146D758930ECFD461C0FA3B4798B62C90668EA03BE2AC7F2C4"

# Convert hex strings to bytes
key_bytes = binascii.unhexlify(key_hex)
ciphertext_bytes = binascii.unhexlify(ciphertext_hex)

# Create AES cipher object with ECB mode
cipher = AES.new(key_bytes, AES.MODE_ECB)

# Decrypt the ciphertext
plaintext_bytes = cipher.decrypt(ciphertext_bytes)


print("Decrypted plaintext:", plaintext_bytes.decode('latin-1'))
