b = ['"2125394059"', '"316324253"', '"322717996"', '"1817181343"', '"1916880576"', '"1376134909"', '"1536615367"', '"969122838"', '"935365158"', '"2029694981"', '"538501427"', '"1099949760"', '"977807481"']

# Convert each hexadecimal value in the list to string
string_list = [bytes.fromhex(hex_value).decode('latin-1') for hex_value in b]
