import httpx

url = "http://localhost:5000/"

# Test home
res = httpx.get(url)
print("HOME:", res.status_code, res.text)

# Login
print("\nLogging in...")
res = httpx.post(url + "login", data={"id": "wis22009"})
print("LOGIN:", res.text)

data = res.json()
token = data["uuid-token"]
print("TOKEN:", token)

# Call protected
print("\nCalling /protected...")
res = httpx.post(url + "protected", data={"token": token})
print("PROTECTED:", res.status_code, res.text)
