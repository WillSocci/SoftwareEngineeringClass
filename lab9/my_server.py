from flask import Flask, request, jsonify
import uuid

app = Flask(__name__)

validTokens = {}

@app.route("/")
def home():
    return "Server is running\n"

@app.route("/login", methods=['POST'])
def login():
    userId = request.form.get('id')
    if not userId:
        return jsonify({"error": "Missing id"}), 400
    
    uuid_token = str(uuid.uuid4())
    
    validTokens[uuid_token] = {
        "user_id": userId
    }
    
    return jsonify({
        "uuid-token": uuid_token,
        "user_id": userId
    })

@app.route("/protected", methods=['POST'])
def protected():
    token = request.form.get('token')
    
    if token in validTokens:
        ui = validTokens[token]
        return jsonify({
            "message": "Valid token!",
            "user_id": ui['user_id']
        })
    else:
        return jsonify({"message": "Invalid token"}), 401

if __name__ == "__main__":
    app.run(host='0.0.0.0')