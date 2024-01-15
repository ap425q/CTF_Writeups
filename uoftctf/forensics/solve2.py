ascii_values = [117, 111, 102, 116, 99, 116, 102, 123, 48, 100, 100, 95, 119, 52, 121, 95, 116, 48, 95, 114, 117, 110, 95, 112, 119, 53, 104, 125]

characters = [chr(value) for value in ascii_values]

result_string = ''.join(characters)

print(result_string)
