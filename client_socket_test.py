import socket

HOST='192.168.1.5'
PORT=5002
s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

count = 0
while True:
    data = s.recv(255)
    if len(data) > 0:
        print data
        count += 1
    else:
        break

s.close()
print "Received " + str(count) + " bytes."
