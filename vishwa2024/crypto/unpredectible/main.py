import random

class challenge1:
    key = random.randint(1,26)

    def level1(self, message):
        encrypted = ""
        
        for char in message:
            if char.isalpha():
                shifted = ord(char) + self.key
                
                if char.islower():
                    if shifted > ord('z'):
                        shifted -= 26

                    elif shifted < ord('a'):
                        shifted += 26

                elif char.isupper():
                    if shifted > ord('Z'):
                        shifted -= 26

                    elif shifted < ord('A'):
                        shifted += 26

                encrypted += chr(shifted)

            else:
                encrypted += char

        return encrypted

    def level2(self, message):
        message = self.level1(message)
        encrypted = ""
        key = random.randint(1,100)

        for char in message:
            charInt = ord(char)
            res = charInt ^ key
            encrypted += chr(res)

        return encrypted


encrypt = challenge1()
print(encrypt.level2(flag))

