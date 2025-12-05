import unittest
from my_server import app, validTokens

class TokenTests(unittest.TestCase):

    def setUp(self):
        self.client = app.test_client()
        validTokens.clear()

    def test_home(self):
        res = self.client.get("/")
        self.assertEqual(res.status_code, 200)

    def test_login_creates_token(self):
        res = self.client.post("/login", data={"id": "will1"})
        self.assertEqual(res.status_code, 200)
        data = res.get_json()
        self.assertIn("uuid-token", data)
        self.assertIn(data["uuid-token"], validTokens)

    def test_protected_valid_token(self):
        login = self.client.post("/login", data={"id": "test2"})
        token = login.get_json()["uuid-token"]

        res = self.client.post("/protected", data={"token": token})
        self.assertEqual(res.status_code, 200)
        self.assertIn("Valid token", res.get_json()["message"])

    def test_protected_invalid_token(self):
        res = self.client.post("/protected", data={"token": "fake"})
        self.assertEqual(res.status_code, 401)

if __name__ == '__main__':
    unittest.main()
