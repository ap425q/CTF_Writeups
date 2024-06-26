from flask import Flask, Response, request, session
from typing import List

with open(__file__, "r") as f:
    SOURCE: str = f.read()

app: Flask = Flask(__name__)

app.secret_key = ""
app.config['SESSION_TYPE'] = 'filesystem'

def text_response(body: str, status: int = 200, **kwargs) -> Response:
    return Response(body, mimetype="text/plain", status=status, **kwargs)

@app.route("/source")
def send_source() -> Response:
    return text_response(SOURCE)

@app.route("/")
def main_page() -> Response:
    if "X-Forwarded-For" not in request.headers:
        return text_response("Unauthorized. Your IP is not whitelisted!", 401)

    ips: List[str] = request.headers["X-Forwarded-For"].split(", ")
    
    if ips[0] != "192.168. 35.4":
        return text_response("Access Denied. Only trusted members are allowed.", 403)

    session['can_solve_puzzle'] = True
    return text_response("Welcome! I am Bond! James Bond!. Solve the puzzle to get the treasure.")

@app.route("/puzzle", methods=["POST"])
def solve_puzzle() -> Response:
    if not session.get('can_solve_puzzle', False):
        return text_response("You can't solve the puzzle without permission.", 403)

    blacklist = [ ';', '"', 'os', '_', '\\', '/', '`',
                  ' ', '-', '!', '[', ']', '*', 'import',
                  'eval', 'print', 'banner', 'echo', 'cat', '%', 
                  '&', '>', '<', '+', '1', '2', '3', '4',
                  '5', '6', '7', '8', '9', '0', 'b', 's', 
                  'lower', 'upper', 'system', '}', '{' ]
    
    user_input = request.form.get("solution", "")
    
    if any(char in user_input for char in blacklist):
        return text_response("Forbidden characters detected. Try again.", 403)
    
    try:
        flag = eval(user_input.strip() + '()')
        return text_response(f"Correct! Here is your flag: {flag}")
    except Exception as e:
        return text_response(f"Incorrect solution. Error: {e}. Please try again.", 400)
