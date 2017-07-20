import socket
import sys
import serial

HOST = "192.168.1.5"
PORT = 5002

s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
s.listen(1)

conn, addr=s.accept()
s.setsockopt(socket.IPPROTO_TCP, socket.TCP_NODELAY, 1)
print 'Connected by', addr

ser = serial.Serial('/dev/ttyACM0', 9600)

while True :
    try:
        state=ser.readline()
        #print(state)
        conn.send(state)
    except:
        conn.send("-1")

conn.close()
s.close()
