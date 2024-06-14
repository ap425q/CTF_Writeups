def givekey(param_1):
    local_58 = "gmybjoglwjogumvrha"
    result = ""

    for char in param_1:
        if not ('a' <= char <= 'z'):
            result += char
        else:
            char_code = ord(char)
            offset = ord(local_58[char_code % len(local_58)])
            new_char_code = (char_code - offset + 0x1a) % 0x1a + ord('a')
            result += chr(new_char_code)

    return result

# Example usage:
input_string = "pneantr"
output_string = givekey(input_string)
print(output_string)

