from pyautogui import *
from time import sleep

sleep(5)
print("Started")
with open("code1.txt") as file:
	for item in file.readlines():
		write(item,interval=0.01)

sleep(10)

with open("code2.txt") as file:
	for item in file.readlines():
		write(item,interval=0.01)



