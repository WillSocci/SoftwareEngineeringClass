from flask import Flask, request, jsonify
import jwt
import uuid
from datetime import datetime, timedelta

app = Flask(__name__)

SECRET_KEY = "secret"

@app.route("/")
def home():
    return "Server is running\n"

@app.route("/login", methods=['POST'])
def login():
    user_id = request.form.get('id')
    if not user_id:
        return jsonify({"error": "Missing id"}), 400
    
    nine_hours_from_now = datetime.now() + timedelta(hours=9)
    uuid_token = str(uuid.uuid4())
    
    payload = {
        "jti": uuid_token,
        "user_id": user_id,
        "exp": nine_hours_from_now
    }
    
    token = jwt.encode(payload, SECRET_KEY, algorithm="HS256")
    
    return jsonify({
        "token": token,
        "uuid-token": uuid_token
    })

@app.route("/protected", methods=['POST'])
def protected():
    token = request.form.get('token')
    
    try:
        decoded = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
        return jsonify({"message": "Valid token!", "user_id": decoded['user_id']})
    except:
        return jsonify({"message": "Invalid or expired token"}), 401

if __name__ == "__main__":
    app.run(host='0.0.0.0')