input_string = ""
for j in range(100):
    # Calculate the value for this index according to the condition
    value = (j + 2) * j * (j + 1) % 34
    
    # XOR with 'A' or 'a' based on even or odd index
    if j % 2 == 0:
        input_string += chr(value ^ ord('A'))
    else:
        input_string += chr(value ^ ord('a'))

print(input_string)
