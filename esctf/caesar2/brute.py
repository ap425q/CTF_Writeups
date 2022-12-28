letters = "abcdefghijklmnopqrstuvwxyz"
enc_string = "lozgcvsufhmrkltfyweljgujwpaimwmzvrnkwleqlbhazchvuovayzaayhbhclpgbugbuhipsrvhbheljgujwsfcefgvc"
x = 0
while x < 26:
    x = x + 1
    stringtodecrypt = enc_string
    stringtodecrypt = stringtodecrypt.lower()
    ciphershift = int(x)
    stringdecrypted = ""
    for character in stringtodecrypt:
        position = letters.find(character)
        newposition = position - ciphershift
        if character in letters:
            stringdecrypted = stringdecrypted + letters[newposition]
        else:
            stringdecrypted = stringdecrypted + character
    ciphershift = str(ciphershift)
    print("You used a cipher shift of " + ciphershift)
    print("Your decrypted message reads:")
    print(stringdecrypted)
    print("\n")
