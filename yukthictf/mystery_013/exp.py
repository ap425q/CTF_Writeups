import requests

def readcredentialsfromfile(filename):
    with open(filename, 'r') as file:
        return [line.strip() for line in file.readlines()]

def hit_post_requests(usernames, passwords, url):
    for username in usernames:
        for password in passwords:
            payload = {'user': username, 'passwd': password}
            response = requests.post(url, data=payload)
            print(f"Username: {username}, Password: {password}, Status Code: {response.status_code}, Response: {response.text}")

def main():
    # Assuming you have two files containing usernames and passwords
    usernames = readcredentialsfromfile('usernames.txt')
    passwords = readcredentialsfromfile('usernames.txt')
    url = 'http://localhost:42751/logic/v1/login'  # Replace this with your actual endpoint URL
    hit_post_requests(usernames, passwords, url)

if __name__ == "__main__":
    main()