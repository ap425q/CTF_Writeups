from flask import Flask, request, render_template, render_template_string, redirect
import subprocess
import urllib
flag = open('flag.txt').read()
app = Flask(__name__)
@app.route('/')
def main():
    return redirect('/login')

@app.route('/login',methods=['GET','POST'])
def login(): # function
    if request.method == 'GET':
        return render_template('login.html')
    elif request.method == 'POST':
        if len(request.values["username"]) >= 40:      #This is the code for the server running on their side so we need to fail the first 3 conditions , basically we have to reach the flag
            return render_template_string("Username is too long!") # we need to make a post request and then username length must be essl than 40 so it should be less than 40 
        elif len(request.values["username"].upper()) <= 50:   # and also username when converted into uppercase must be greater than equal to 50 
            return render_template_string("Username is too short!")
        else:
            return flag
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000)



    #so basically we have to find a username which is less than 40 in length and when converted to uppercase it must be greater than 50 , Which seems impossible

    #we have to fail both the selected conditions in order to get the flag
    #we dont want to go inside the if and elif block we want else to be executed


    #So length of username must be less than 40 and when converted to uppercase it must be greater than 50

    #Can you find the username ???

    # For ex :

    #  username : aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa #39 A's then the if condition fails now we move to elif
    #             AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA #now it converts it into upper case but did the length change ??

    #             #obviously if you change any lc to uc the length wont change right ???
    #             # but for elif to fail len >= 50  
    #             #then how can we bypass this check ?? Any idea ?? ??

    #Ok So There are some unicode characters when you change from lc to uc the length of the character changes those are the character we have to use 
    # Ex : ß <--This character when converted to upper case splits into 2 characters

    # so if we use 39*ß then we will fail first condition then it gets converted into uc which will result in size being twice that is 78 so we also fail 2nd condition
    #then we get our flag now lets do it
    