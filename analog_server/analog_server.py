import socket
import sys
import serial

HOST = "192.168.2.3"
PORT = 5002
maxSes = 5
ses_num = 0
s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind((HOST, PORT))

while True:
    s.listen(1)
    conn, addr=s.accept()
    s.setsockopt(socket.IPPROTO_TCP, socket.TCP_NODELAY, 1)
    print 'Connected by', addr

    ser = serial.Serial('/dev/ttyACM0', 9600)

    while not data:
        data = conn.recv(255)


    print data

print "end"
conn.close()
s.close()
