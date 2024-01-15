from Crypto.Util.number import *

string = "label"
new = []
for i in string:
	new.append(ord(i)^13)

for i in new:
	print(chr(i),end="")