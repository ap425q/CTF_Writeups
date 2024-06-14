# Given hex strings
X1_hex = "b3c8d73e3a9b23df7cc1253277a4878ef65bcfe9735f29d84424"
X2_X1_hex = "fb3514ac2e94885e9d5ec915821650572d5e0b842e9630f32b1b"
X2_X3_hex = "d2656867798e8584ec34ab2d4562b1a9c82b8fcf1feeeddf70e2"

# Convert hex strings to integers
X1 = int(X1_hex, 16)
X2_X1 = int(X2_X1_hex, 16)
X2_X3 = int(X2_X3_hex, 16)

# Compute X2
X2 = X1 ^ X2_X1

# Compute X3
X3 = X2 ^ X2_X3

# Compute X1 ^ X2 & X3
result = X1 ^ (X2 & X3)

flag_xor ="07c1de3e3867c32fe29cbd6957a2695f0e021f4b58c2b03446bb"
flag_xor =int(flag_xor,16)
# Convert result back to hex
result_hex = hex(result^flag_xor)
print(result_hex)


