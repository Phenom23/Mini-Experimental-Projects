#-*- coding: utf-8 -*-
#Functions##################################################################################
def action_check(action):
    if action !=1 and action !=2 and action !=3:
        return False

def action_checker2(action):
    if action !=1 and action !=2 and action !=3 and action!=4:
        return False
    
def mark_checker(mark):
    if mark < 0 or mark > 20:
        return False
############################################################################################

print"------------------------------------------"
print"Πρόγραμμα υπολογισμού μορίων Επαλ - Γελ "
print"------------------------------------------"

action = 0
while action !=3:
    
    #Αρχικό μενού
    print " "
    print "Μενού επιλογών: 1-Επαλ, 2-Γενικό, 3-Τερματισμός"
    print" "
    action = input("Καταχώρησε τύπο σχολείου/ενέργεια: ")
    while action_check(action) == False:
        print " "
        print "Μενού επιλογών: 1-Επαλ, 2-Γενικό, 3-Τερματισμός"
        print " "
        action = input("Καταχώρησε ορθό τύπο σχολείου/ενέργεια: ")
        print " "

    
    if action == 1: #Αλγόριθμος ΕΠΑΛ

        sum_k = 0
        sum_eid = 0

        for i in range(2):
            print " "
            eid = input("Δώσε βαθμό μαθήματος ειδικότητας: ")
            while mark_checker(eid) == False:
                print" "
                eid = input("Δώσε ορθό βαθμό μαθήματος ειδικότητας (0-20): ")
                print" "
            sum_k += eid *1.5
            sum_eid += eid *3.5

        print" "
        koin1 = input("Δώσε βαθμό μαθηματικών: ")
        print " "
        while mark_checker(koin1) == False:
            koin1 = input("Δώσε ορθό βαθμό μαθηματικών (0-20): ")
        sum_k += koin1 *3.5
        sum_eid += koin1 *1.5

        koin2 = input("Δώσε βαθμό γλώσσας: ")
        print " "
        while mark_checker(koin2) == False:
            koin2 = input("Δώσε ορθό βαθμό γλώσσας (0-20): ")
        sum_k += koin2 *3.5
        sum_eid += koin2 *1.5

        mo_k = (sum_k * 200.0 )/2.0
        mo_eid =(sum_eid * 200.0 )/2.0

        print"---------------------------------------------------"
        print "Έβγαλες μέσο όρο:",mo_eid,"μόρια τομέα/ειδικότητας"
        print "Έβγαλες μέσο όρο:",mo_k,"μόρια κοινής ομάδας"
        print"---------------------------------------------------"


    elif action == 2: #Αλγόριθμος Γενικού Λυκείου

        print" "
        print"Επέλεξε επιστημονικό πεδίο:"
        print"---------------------------------------------------"
        print" "
        print"1 - Ανθρωπιστικές, Νομικές και Κοινωνικές Επιστήμες"
        print"2 - Θετικές και Τεχνολογικές Επιστήμες"
        print"3 - Επιστήμες Υγείας και Ζωής"
        print"4 - Επιστήμες Οικονομίας και Πληροφορικής"
        print" "

        pedio = input("Καταχώρησε ενέργεια(1/2/3/4): ")
        print" "
        
        while action_checker2(pedio) == False:
            
            pedio = input("Καταχώρησε ορθή ενέργεια (1/2/3/4): ")
            print" "
        
        if pedio == 1: #Ανθρωπιστικές, Νομικές και Κοινωνικές Επιστήμες

            suma = 0

            arxaia = input("Δώσε βαθμό αρχαίων: ")
            print" "
            while mark_checker(arxaia) == False:
                arxaia = input("Δώσε σωστό βαθμό αρχαίων (0-20): ")
                print" "
                
            suma += arxaia
            arxaia = arxaia * 1.3

            history = input("Δώσε βαθμό ιστορίας: ")
            print" "
            while mark_checker(history) == False:
                history = input("Δώσε σωστό βαθμό ιστορίας (0-20): ")
                print" "

            suma += history
            history = history * 0.7

            glossa = input("Δώσε βαθμό γλώσσας: ")
            print" "
            while mark_checker(glossa) == False:
                glossa = input('Δώσε σωστό βαθμό γλώσσας (0-20): ')
                print" "

            suma += glossa

            koinon = input("Δώσε βαθμό κοινωνιολογίας: ")
            print" "
            while mark_checker(koinon) == False:
                koinon = input("Δώσε σωστό βαθμό κοινωνιολογίας (0-20): ")
                print" "

            suma +=  koinon
            
            suma *= 2
            suma += arxaia + history
            suma *=100.0

            print"---------------------------------"
            print"Έβγαλες μέσο όρο:",suma,"μόρια"
            print"---------------------------------"
                    
        elif pedio == 2: #Θετικές και Τεχνολογικές Επιστήμες
            
            suma = 0

            maths = input("Δώσε βαθμό μαθηματικών: ")
            print" "
            while mark_checker(maths) == False:
                maths = input("Δώσε σωστό βαθμό μαθηματικών (0-20): ")
                print" "

            suma += maths 
            maths *=1.3

            physics = input("Δώσε βαθμό φυσικής: ")
            print" "
            while mark_checker(physics) == False:
                physics = input("Δώσε σωστό βαθμό φυσικής (0-20): ")
                print" "

            suma += physics 
            physics *=0.7

            glossa = input("Δώσε βαθμό γλώσσας: ")
            print" "
            while mark_checker(glossa) == False:
                glossa = input('Δώσε σωστό βαθμό γλώσσας (0-20): ')
                print" "

            suma += glossa

            chem = input("Δώσε βαθμό χημείας: ")
            print" "
            while mark_checker(chem) == False:
                chem = input("Δώσε σωστό βαθμό χημείας (0-20): ")
                print" "
            suma +=  chem

            suma *=2
            suma += maths + physics
            suma *= 100

            print"---------------------------------"
            print"Έβγαλες μέσο όρο:",suma,"μόρια"
            print"---------------------------------"
            
        elif pedio == 3: #Επιστήμες Υγείας και Ζωής
            
            suma = 0

            biol = input("Δώσε βαθμό βιολογίας: ")
            print" "
            while mark_checker(biol) == False:
                biol = input("Δώσε σωστό βαθμό βιολογίας (0-20): ")
                print" "

            suma += biol
            biol *=1.3

            glossa = input("Δώσε βαθμό γλώσσα: ")
            print" "
            while mark_checker(glossa) == False:
                glossa = input("Δώσε σωστό βαθμό γλώσσας (0-20): ")
                print" "

            suma += glossa 

            physics = input("Δώσε βαθμό φυσικής: ")
            print" "
            while mark_checker(physics) == False:
                physics = input('Δώσε σωστό βαθμό φυσικής (0-20): ')
                print" "
            suma += physics

            chem = input("Δώσε βαθμό χημείας: ")
            print" "
            while mark_checker(chem) == False:
                chem = input("Δώσε σωστό βαθμό χημείας (0-20): ")
                print" "

            suma += chem
            chem *=0.7

            suma*=2
            suma += biol + chem
            suma *= 100

            print"---------------------------------"
            print"Έβγαλες μέσο όρο:",suma,"μόρια"
            print"---------------------------------"
            
        else: #Επιστήμες Οικονομίας και Πληροφορικής

            suma = 0

            maths = input("Δώσε βαθμό μαθηματικών: ")
            print" "
            while mark_checker(maths) == False:
                maths = input("Δώσε σωστό βαθμό μαθηματικών (0-20): ")
                print" "

            suma += maths
            maths *=1.3

            glossa = input("Δώσε βαθμό γλώσσας: ")
            print" "
            while mark_checker(glossa) == False:
                glossa = input("Δώσε σωστό βαθμό γλώσσας (0-20): ")
                print" "

            suma += glossa 

            plirof = input("Δώσε βαθμό πληροφορικής: ")
            print" "
            while mark_checker(plirof) == False:
                plirof = input('Δώσε σωστό βαθμό πληροφορικής (0-20): ')
                print" "

            suma += plirof

            oikon = input("Δώσε βαθμό οικονομίας: ")
            print" "
            while mark_checker(oikon) == False:
                oikon = input("Δώσε σωστό βαθμό οικονομίας (0-20): ")
                print" "

            suma += oikon
            oikon *=0.7

            suma *=2
            suma += maths + oikon
            suma *=100

            print"---------------------------------"
            print"Έβγαλες μέσο όρο:",suma,"μόρια"
            print"---------------------------------"
print " "            
print"------------------------------------------"
print"Powered Off"
