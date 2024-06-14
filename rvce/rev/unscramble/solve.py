import base64

def xor_with_key(hex_input, key):
    xored = []
    for i in range(0, len(hex_input), 2):
        hex_char = int(hex_input[i:i+2], 16)
        xored_char = hex_char ^ key
        xored.append(xored_char)
    return bytes(xored)

def from_hex(hex_input):
    return bytes.fromhex(hex_input)

def reverse_shift(input_str, amount):
    result = []
    for c in input_str:
        if c.isalpha():
            base = 'A' if c.isupper() else 'a'
            offset = (ord(c) - ord(base) - amount) % 26
            if offset < 0:
                offset += 26
            result.append(chr(ord(base) + offset))
        else:
            result.append(c)
    return ''.join(result)

def decode_base64(input_str):
    return base64.b64decode(input_str).decode('utf-8')

def reverse_string(input_str):
    return input_str[::-1]

def decrypt(encrypted_flag):
    # Step 1: Reverse XOR with key 9
    xored_result = xor_with_key(encrypted_flag, 9)
    
    # Step 2: Convert from hex to string
    hex_result = from_hex(xored_result.hex())
    
    # Step 3: Reverse shift by 25
    shifted_result = reverse_shift(hex_result.decode('utf-8'), 25)
    
    # Step 4: Decode Base64
    base64_decoded_result_1 = decode_base64(shifted_result)
    
    # Step 5: Reverse the string
    reversed_result = reverse_string(base64_decoded_result_1)
    
    # Step 6: Decode Base64 again
    original_flag = decode_base64(reversed_result)
    
    return original_flag

encrypted_flag = "465a38585060405f685f4465734d6a636d4f45705f4e67384565403d5d5c6e506d5d3c4d513a513c5862663c58636a736a5c404d504e6639453866676d4f3873"
flag = decrypt(encrypted_flag)
print(f"The decrypted flag is: {flag}")
