from pwn import *

context.log_level = "critical"
context.update(arch='amd64')

file = open('output','r')

k = 1

for i in reversed(file.readlines()):
    p = process('./unstringify')
    buffer = bytes(i,'utf-8')
    p.send(buffer)
    k = k + 1
    p.recvuntil("flag: \n")
    randi = p.recvuntil("?").decode()
    if(randi.startswith("Wrong")):
        print(f"{k} [-] Galat hai BC")
    else:
        print(i)
        print("[+] Chutiya kaat gaya shayad")
        break
    p.close()



