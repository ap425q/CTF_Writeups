string1 = "usedistofindouttheseed"
string2 = "thisisthekeytogetyourseed"

# Perform XOR operation on each pair of corresponding characters
xor_result = "".join(chr(ord(a) ^ ord(b)) for a, b in zip(string1, string2))

# Convert the XOR result to an integer
result_integer = int.from_bytes(xor_result.encode(), 'big')

print("XOR Result:", xor_result)
print("Result Integer:", result_integer)
