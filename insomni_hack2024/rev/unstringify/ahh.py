from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad
from Crypto.Util.strxor import strxor

# Given ciphertext and key in hexadecimal format
ciphertext_hex = "51CC0FBEA0262A146D758930ECFD461C0FA3B4798B62C90668EA03BE2AC7F2C4"
key_hex = "0003195A5B005902000315015C09015E42530D164D06420D2752423A630B540F"

# Convert hex strings to bytes
ciphertext = bytes.fromhex(ciphertext_hex)
key = bytes.fromhex(key_hex)

# Create an AES cipher object
cipher = AES.new(key, AES.MODE_ECB)

# Decrypt the ciphertext
plaintext_bytes = strxor(cipher.decrypt(ciphertext), key)

# Convert bytes to hex
plaintext_hex = plaintext_bytes

print("Decrypted plaintext:", plaintext_hex)
