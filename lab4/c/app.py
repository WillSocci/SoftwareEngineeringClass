from flask import Flask, request, jsonify

app = Flask(__name__)

def trial_division(n: int):
    factors = []
    # Handle 2 separately
    while n % 2 == 0:
        factors.append(2)
        n //= 2
        
    # Check odd numbers up to sqrt(n)
    p = 3
    while p * p <= n:
        while n % p == 0:
            factors.append(p)
            n //= p
        p += 2
    if n > 1:
        factors.append(n)
    return factors

@app.route("/")
def hello():
    return "You called!\n"

# curl -d "text=Hello!&param2=value2" -X POST http://localhost:5000/echo
@app.route("/echo", methods=['POST'])
def echo():
    return "You said: " + request.form['text']

@app.route("/factor", methods=['GET'])
def factors():
    try:
        in_int = int(request.args.get("inINT"))
    except (TypeError, ValueError):
        return jsonify({"error": "Please provide a valid integer parameter ?inINT="}), 400
    
    result = trial_division(in_int)
    return jsonify({"input": in_int, "factors": result})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
