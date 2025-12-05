import jwt
import uuid
from datetime import datetime, timedelta

SECRET_KEY = "secret"

#create a token
print("1. Creating a token")
nine_hours_from_now = datetime.now() + timedelta(hours=9)
uuid_token = str(uuid.uuid4())
print("UUID:", uuid_token)

payload = {
    "jti": uuid_token,
    "user_id": "william.socci@uconn.edu",
    "exp": nine_hours_from_now
}

token = jwt.encode(payload, SECRET_KEY, algorithm="HS256")
print("Token:", token)

#decode the token
print("\n2. Decoding the token")
decoded = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
print("Decoded payload:", decoded)
print("User ID:", decoded['user_id'])
print("Token ID:", decoded['jti'])

#test with expired token
print("\n3. Testing expired token")
past_time = datetime.now() - timedelta(hours=1)
expired_payload = {
    "jti": str(uuid.uuid4()),
    "user_id": "test@example.com",
    "exp": past_time
}
expired_token = jwt.encode(expired_payload, SECRET_KEY, algorithm="HS256")

try:
    jwt.decode(expired_token, SECRET_KEY, algorithms=["HS256"])
    print("ERROR: Expired token was accepted!")
except jwt.ExpiredSignatureError:
    print("PASS: Expired token correctly rejected")

#test with invalid token
print("\n4. Testing invalid token")
try:
    jwt.decode("invalid_token", SECRET_KEY, algorithms=["HS256"])
    print("ERROR: Invalid token was accepted!")
except:
    print("PASS: Invalid token correctly rejected")

#verify unique UUID generation
print("\n5. Testing unique UUID generation")
uuid1 = str(uuid.uuid4())
uuid2 = str(uuid.uuid4())
if uuid1 != uuid2:
    print("PASS: UUIDs are unique")
    print("UUID 1:", uuid1)
    print("UUID 2:", uuid2)
else:
    print("ERROR: UUIDs are not unique")

print("Tests complete")