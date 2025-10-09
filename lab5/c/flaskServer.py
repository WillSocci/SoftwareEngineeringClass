from flask import Flask, request, jsonify


# Define the Flask app
app = Flask(__name__)

subscribers = {}

def home():
  return "Hello from Flask!"

@app.route('/', methods=['GET'])
def root():
  print(f"Hello at the root")
  return jsonify({'main endpoint':'Ack'})

@app.route('/list-subscribers', methods=['GET'])
def listSubscribers():
  return jsonify(subscribers)

# Windows> curl.exe -X POST -H "Content-Type: application/json" -d "{\"name\":\"Alice\",\"URI\":\"http://good.site.com\"}" http://localhost:5000/add-subscriber

@app.route('/add-subscriber', methods=['POST'])
def addSubscriber():
  data = request.json
  name = data.get('name')
  URI = data.get('URI')
  subscribers[name] = URI
  print(f"You entered: Name={name}, Address={URI}")
  return jsonify({'message': f'You sent name: {name} and address: {URI}'})

@app.route('/deleting-subscriber', methods=['DELETE'])
def deleteSubscriber():
    data = request.json
    name = data.get('name')
    if name not in subscribers:
        return jsonify({'message': f'{name} not found'}), 404
  
    URI = subscribers.pop(name)
    print(f'Subscriber {name}, {URI} is deleted')
    return jsonify({'message': f'Sent name: {name} is deleted'})

@app.route('/update-sub', methods=['POST'])
def updateSubject():
    global currentsub

    data = request.json

    subject = data.get('subject')
    currentsub = subject

    print(f'Publishing new subject: {subject}')

    for name, uri in subscribers.items():
        print(f'Notifying {name} at {uri}... Publishing New Subject: {subject}\n')
    
    return jsonify({'message' : 'Subject published', 'subject' : f'{subject}', 
                    'subscribers_notified' : len(subscribers), 'subscribers' : list(subscribers.keys())})

if __name__ == "__main__":
  app.run(debug=False, host="0.0.0.0", port=5000)
