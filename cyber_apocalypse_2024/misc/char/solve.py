#!usr/bin/env/python3

from pwn import *

p = remote('94.237.60.112',45231)

fin_flag = ''

for i in range(0,104):
	i = str(i)
	print(p.recvuntil(': '))
	p.sendline(i.encode())
	print(p.recvuntil(':'))
	flag = p.recvuntil('\n')
	fin_flag = fin_flag + flag.strip().decode()

print(fin_flag)

#HTB{tH15_1s_4_r3aLly_l0nG_fL4g_i_h0p3_f0r_y0Ur_s4k3_tH4t_y0U_sCr1pTEd_tH1s_oR_els3_iT_t0oK_qU1t3_l0ng!!}