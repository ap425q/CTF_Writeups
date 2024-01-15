from pwn import *
from Crypto.Util.number import long_to_bytes,bytes_to_long
from sage.modules.free_module_integer import IntegerLattice
io=remote("34.30.232.46",int(1111))
io.recv()
data={"option":"encrypt","msgs":['00' for i in range(20)]}
import json
param={"option":"parameters"}

io.sendline(json.dumps(param).encode())
param=json.loads(io.recvline().decode())
io.sendline(json.dumps({"option":"get_flag"}).encode())
print(io.recvline())
flag=json.loads(io.recvline().decode())["enc_flag"]

print(flag)
M=param["M"]
a=param["a"]
b=param["b"]

print(param)

data_enc=[]
data=json.dumps(data)
for i in range(48):
    io.recvuntil(b"to encrypt\n")
    io.sendline(data.encode())
    t=json.loads(io.recvuntil(b"}"))["encrypted_msgs"]
    data_enc.append(t)

data_enc=[[int(i,16)<<100 for i in j] for j in data_enc]
summed_data=[]
for i in data_enc:
    p=0
    for j in i:
        p=p+j
    summed_data.append(p)

x=var("x")
eq=[x]
n=48
for i in range(n*20):
    eq.append(eq[-1]*a+b)
eq=eq[1:]
eq=[eq[i:i+20] for i in range(0,n*20,20)]
eq2=[]
for i in eq:
    qq=0
    for j in i:
        qq+=j
    aa,bb=qq.coefficients()
    bb=int(int(bb[0])%M)
    aa=int(int(aa[0])%M)
    eq2.append([bb,aa])


# this code is from https://hackmd.io/@hakatashi/B1OM7HFVI

def Babai_closest_vector(M, G, target):
  small = target
  for _ in range(1):
    for i in reversed(range(M.nrows())):
      c = ((small * G[i]) / (G[i] * G[i])).round()
      small -= M[i] * c
  return target - small

A_values=[[i[0],1] for i in eq2]
b_values=[i-j[1] for i, j in zip(summed_data,eq2)]

q=M
n=2
m=48
A = matrix(ZZ, m + n, m)
for i in range(m):
  A[i, i] = q
for x in range(m):
  for y in range(n):
    A[m + y, x] = A_values[x][y]
  
lattice = IntegerLattice(A, lll_reduce=True)
print("LLL done")
gram = lattice.reduced_basis.gram_schmidt()[0]
target = vector(ZZ, b_values)
res = Babai_closest_vector(lattice.reduced_basis, gram, target)
print("Closest Vector: {}".format(res))
print(res)
R = IntegerModRing(q)

M = Matrix(R, A_values)
xx,random = M.solve_right(res)
xx=xx>>100
xx=int(xx)
flag=int(flag,16)
flag=xx^^flag
print(long_to_bytes(int(flag)))
#BSidesIndore{MAStEr_Of_1cG_and_ShuFf1e?}