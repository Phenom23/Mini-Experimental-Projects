#Functions----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
def cellphone(number):
    pool = ["0","1","2","3","4","5","6","7","8","9"]
    if number[0:2] == "69" and len(number)==10:
        chk = 0
        for i in range(10):
            if number[i] in pool:
                chk += 1
        if chk == 10:
            return True

def url(address):
    if (address[0:12] == "https://www." or address[0:4] == "www." or address[0:11] == "http://www.") and (address[len(address)-4:len(address)] == ".com" or address[len(address)-5:len(address)] == ".com/" or address[len(address)-3:len(address)] == ".gr" or address[len(address)-4:len(address)] == ".gr/"  or address[len(address)-4:len(address)] == ".net" or address[len(address)-5:len(address)] == ".net/"):
        return True
    
def mail(address):
    if "@" in address and (address[len(address)-3:len(address)] == ".gr" or address[len(address)-4:len(address)] == ".com"):
        return True     
#------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
action = 5
print" "
print"Σημείωση: Tο url να είναι της μορφής 'https://www.' ή 'www.' ή 'http://www.'"
while action !="Stop":
    print"-----------------------------------------------------------------------------"
    print" "
    action = raw_input("Δώσε κείμενο(url/mail/κινητό τηλέφωνο/'Stop'): ")
    print" "
    if cellphone(action) == True:
        print"Καταχώρησες αριθμό κινητού τηλεφώνου."
    elif url(action) == True:
        print "Καταχώρησες διεύθυνση url."
    elif mail(action) == True:
        print "Καταχώρησες διεύθυνση e-mail."
    elif action == "Stop":
        print "Powered Off."
        print " "
        print"-----------------------------------------------------------------------------"
    else:
        print "Δεν εντοπίστηκε ο τύπος των εισακτέων δεδομένων."
    print" "
