import socket
import sys
import serial

HOST = "192.168.1.5"
PORT = 5002
maxSes = 5
ses_num = 0
s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))

while ses_num < maxSes:
    ses_num += 1
    s.listen(1)
    conn, addr=s.accept()
    s.setsockopt(socket.IPPROTO_TCP, socket.TCP_NODELAY, 1)
    print 'Connected by', addr

    ser = serial.Serial('/dev/ttyACM0', 9600)

    while True :
        try:
            state=ser.readline()
            if int(state) > 254:
                break
            conn.send(state)
        except:
            conn.send("255")

    conn.close()
    s.close()
