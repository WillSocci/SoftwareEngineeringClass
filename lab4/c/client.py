import httpx

url = "http://localhost:5000/"

#root endpoint
resp = httpx.get(url)
print("Root:", resp.text)

#echo
mydata = {"text": "Hello Will!", "param2": "POST test", "body": "my own value"}
resp = httpx.post(url + "echo", data=mydata)
print("Echo:", resp.text)

#factors endpoint
resp = httpx.get(url + "factor", params={"inINT": 12})
print("Factor 12:", resp.json())

resp = httpx.get(url + "factor", params={"inINT": 13})
print("Factor 13:", resp.json())
