import requests

# Define the base URL
base_url = "https://share.comefindme.dev/uploads/"

# Loop through numbers from 1 to 50
for i in range(100, 300):
    # Construct the URL with the current number
    url = base_url + str(i)

    # Make a GET request
    response = requests.get(url)

    # Check if the request was successful
    if response.status_code == 200:
        # Print the JSON response
        print(response.json())
    else:
        # Print an error message if the request failed
        print(f"Failed to fetch data for user {i}. Status code: {response.status_code}")
