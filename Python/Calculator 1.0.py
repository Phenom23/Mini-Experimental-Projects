# -*- coding: cp1253 -*-

#------------------------------------------------------------------------------------
def add(x, y):
    return x + y

def subtract(x, y):
    return x - y

def multiply(x, y):
    return x * y

def divide(x, y):
    return x / y

def check(choice):
    if choice != 1 and choice != 2 and choice !=3 and choice !=4 and choice != 5:
        return False
#------------------------------------------------------------------------------------
    
#------------------------------------------------------------------------------------
print "������� ��������:"
print "1 - ��������"
print "2 - ��������"
print "3 - ���������������"
print "4 - ��������"
print "5 - T����"
#------------------------------------------------------------------------------------

#------------------------------------------------------------------------------------
choice = input("���� �������(1/2/3/4/5): ")
while choice != 5:

    while check(choice) == False:
        choice = input("���� ���� �������(1/2/3/4/5): ")

        
    if choice == 1 or choice == 2 or choice== 3 or choice == 4:
        num1 = float(input("���� ����� ������: "))
        num2 = float(input("���� ������� ������: "))

        if choice == 1:
            print num1, "+", num2, "=", add(num1, num2)

        elif choice == 2:
            print num1, "-", num2, "=", subtract(num1, num2)

        elif choice == 3:
            print num1, "*", num2, "=", multiply(num1, num2)

        elif choice == 4:
            print num1, "/", num2, "=", divide(num1, num2)
            
    choice = input("���� �������(1/2/3/4/5): ")
#------------------------------------------------------------------------------------      
        
        
    
