import httpx

url = "http://localhost:5000/"

print("Logging in...")

#Login attempt
login_data = {
    "id": "william.socci@uconn.edu"
}

response = httpx.post(url + "login", data=login_data)
print("LOGIN:", response.status_code, response.text)

token = response.json().get("token", None)
print("Token received:", token)

#Protected attempt
protected_data = {
    "token": token
}

response = httpx.post(url + "protected", data=protected_data)
print("PROTECTED:", response.status_code, response.text)

#Invalid attempt
invalid_data = {
    "token": "invalid_token_12345"
}

response = httpx.post(url + "protected", data=invalid_data)
print("INVALID TOKEN TEST:", response.status_code, response.text)