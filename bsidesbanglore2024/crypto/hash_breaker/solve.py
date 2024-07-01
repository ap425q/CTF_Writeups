def ultrabrend_decrypt(hash_value):
    # Convert hexadecimal hash value to bytes
    digest = bytearray.fromhex(hash_value)

    # Reverse the last XOR operation
    for i in range(31, 0, -1):
        digest[i] ^= digest[(i+1) % 32] ^ i

    # Reverse the digest modification steps
    t_mod_256 = digest[30]
    t_div_256 = digest[29]
    digest[28] = 0
    digest[29] = 0
    for i in range(27, -1, -1):
        digest[i] = (digest[i] - digest[i+1]) % 256

    digest[0] = t_div_256
    digest[30] = t_mod_256

    # Reverse the XOR operation with message characters
    message_length = 29
    message = [0] * message_length

    for i in range(message_length):
        message[i] = chr(digest[i % 29] ^ digest[i])

    # Convert list of characters to string
    original_message = ''.join(message)

    # Remove trailing spaces (if any)
    original_message = original_message.rstrip()

    return original_message

# Example usage:
hash_value = "1a061d36422e5a08190009ddfd34d74d603f2f7c384a08b3521c08130d171dcf"
original_message = ultrabrend_decrypt(hash_value)
print("Original Message:", original_message)
