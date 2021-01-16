#Functions and  more -------------------------------------------------------------------------------------------------------------

import time

import math

def add(x, y):
    return x + y

def subtract(x, y):
    return x - y

def multiply(x, y):
    return x * y

def divide(x, y):
    return x / y

def check(choice):
    if action != 1 and action != 2 and action !=3 and action !=4 and action != 5 and action!=6 and action!=9 and action!=10:
        return False

def menu():
    print "Επέλεξε ενέργεια:"
    print"--------------------"
    print "1 - Πρόσθεση"
    print "2 - Αφαίρεση"
    print "3 - Πολλαπλασιασμός"
    print "4 - Διαίρεση"
    print "5 - Τεραγωνική ρίζα"
    print "6 - Ύψωση στο τετράγωνο"
    print "9 - Καθαρισμός"
    print "10 - Tερματισμός"
    
#---------------------------------------------------------------------------------------------------------------------------------
    
action = 23
while action!=10:

    suma = 0
    print "--------------------------------------------------"
    num = input("Δώσε αρχικό αριθμό: ")
    print "Μνήμη:", num
    suma += num
    action = 23
    
    while action !=9 and action!=10:
        
        print "--------------------------------------------------"
        menu()
        print" "
        action = input("Καταχώρησε ενέργεια: ")
        while check(action) == False:
            action = input("Δώσε έγκυρη εντολή: ")
            
        #Single Operations###############################################
            
        if action == 9:
            print"Καθαρισμός μνήμης:"
            print "10%"
            time.sleep(0.1)
            print "40%"
            time.sleep(0.1)
            print "70%"
            time.sleep(0.1)
            print " "
            print"Η μνήμη καθαρίστηκε"
            print " "
            suma = 0
            print"Μνήμη: ",suma
            break
        
        elif action == 10:
            break
        
        elif action == 5:
            if suma < 0:
                print "Μαθηματικό σφάλμα"
                break
            else:
                print "√",suma,"=", math.sqrt(suma)
                suma = math.sqrt(suma)

        elif action == 6:
            print suma,"**",2,"=",pow(suma,2)
            suma = pow(suma,2)
        #################################################################

        #Double Operations###############################################        
        if action != 5 and action !=6:
            print "--------------------------------------------------"
            print"Μνήμη: ",suma 
            num2 = input("Δώσε δεύτερο αριθμό: ")
            print "--------------------------------------------------"
            
            if action == 1:
                print suma, "+", num2, "=", add(suma, num2)
                suma += num2
                
            elif action == 2:
                print suma, "-", num2, "=", subtract(suma, num2)
                suma -= num2
                
            elif action == 3:
                print suma, "*", num2, "=", multiply(suma, num2)
                suma *= num2
                
            elif action == 4:
                print suma, "/", num2, "=", divide(suma, num2)
                suma /=num2
        #################################################################
                
print "--------------------------------------------------"        
print "Powered Off"       
