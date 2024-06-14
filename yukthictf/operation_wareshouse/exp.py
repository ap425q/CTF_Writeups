#!/usr/bin/python3

import hashlib
from itertools import chain

# This information only exists to make the cookie unique on the
# computer, not as a security feature.
probably_public_bits = [
	'jamie',
	'flask.app',
	'Flask',
	'/usr/local/lib/python3.8/dist-packages/flask/app.py',
]

# This information is here to make it harder for an attacker to
# guess the cookie name.  They are unlikely to be contained anywhere
# within the unauthenticated debug page.
private_bits = ['2482659590742', 'd2cbdbed3c0b454abfc075e908d823a7']

h = hashlib.sha1() # or hashlib.md5()
for bit in chain(probably_public_bits, private_bits):
    if not bit:
        continue
    if isinstance(bit, str):
        bit = bit.encode('utf-8')
    h.update(bit)
h.update(b'cookiesalt')
#h.update(b'shittysalt')

cookie_name = '__wzd' + h.hexdigest()[:20]

num = None
if num is None:
    h.update(b'pinsalt')
    num = ('%09d' % int(h.hexdigest(), 16))[:9]

rv =None
if rv is None:
    for group_size in 5, 4, 3:
        if len(num) % group_size == 0:
            rv = '-'.join(num[x:x + group_size].rjust(group_size, '0')
                          for x in range(0, len(num), group_size))
            break
    else:
        rv = num

print(rv)

# Mac addr : 345052369706

# machine id : d2cbdbed3c0b454abfc075e908d823a7