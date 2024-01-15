def reverse_vba_obfuscation(v6, v7, v8):
    v9 = ""
    for i in range(len(v6)):
        v9 += chr(v6[i] ^ ord(chr(v8)[i % len(chr(v8))]))
    print(v9)

    v10 = ""
    for i in range(len(v7)):
        v10 += chr(v7[i] ^ ord(v9[i % len(v9)]))

    return v10

# Provided arrays and key
v6 = [98, 120, 113, 99, 116, 99, 113, 108, 115, 39, 116, 111, 72, 113, 38, 123, 36, 34, 72, 116, 35, 121, 72, 101, 98, 121, 72, 116, 39, 115, 114, 72, 99, 39, 39, 39, 106]
v7 = [44, 32, 51, 84, 43, 53, 48, 62, 68, 114, 38, 61, 17, 70, 121, 45, 112, 126, 26, 39, 21, 78, 21, 7, 6, 26, 127, 8, 89, 0, 1, 54, 26, 87, 16, 10, 84]
v8 = 23

result = reverse_vba_obfuscation(v6, v7, v8)
print(result)


for i in v6:
    print(chr(i),end="")

for i in v7:
    print(chr(i),end="")