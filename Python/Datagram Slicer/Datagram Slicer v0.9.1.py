#-*- coding: utf-8 -*-
print "------------------------------------------ "
print "The Great Datagram Slicer"
print "------------------------------------------ "
print " "

#Φόρτωση μεταβλητών
mtu_n = input("Δώσε μέγεθος MTU δικτύου(επικεφαλίδα + δεδομένα bytes): ")
while mtu_n < 20 or mtu_n >65535 :
    print u"Σφάλμα"
    mtu_n = input("Δώσε έγκυρο μέγεθος MTU δικτύου(επικεφαλίδα + δεδομένα bytes): ")
epik = input("Δώσε μέγεθος επικεφαλίδας Datagram (λέξεις 32bit): ")
while epik <5 or epik > 15:
    print u"Σφάλμα"
    epik = input("Δώσε έγκυρο μέγεθος επικεφαλίδας Datagram (λέξεις 32bit): ")
data = input("Δώσε μέγεθος δεδομένων Datagram(επικεφαλίδα + δεδομένα bytes): ")
while data >65535 or data < epik*4 :
    print u"Σφάλμα"
    data = input("Δώσε σωστό μέγεθος δεδομένων Datagram (επικεφαλίδα + δεδομένα bytes): ")
id = raw_input("Δώσε αναγνωριστικό του Datagram: ")
df = input("Δώσε τιμή σημαίας DF (0/1): ")
while df!=0 and df!=1:
    print u"Σφάλμα"
    df = input("Δώσε σωστή τιμή σημαίας DF (0/1): ")              
if df == "0":
    print "To πακέτο δεν μπορεί να διασπαστεί"
else:
    if (epik+data) <= mtu_n:
        print "Δεν χρειάζεται διάσπαση το πακέτο"
        
#Υπολογισμοί και παρουσίαση δεδομένων        
    else:
        payload = int((mtu_n -(4*epik))/8)
        bulk = (data-(4*epik))/float(payload*8)
        if bulk % 1 != 0.0:
            bulk = int(bulk) + 1
        else:
            bulk = int(bulk)
        print "------------------------------------------ "
        for i in range(bulk):
            print u"Για το",i+1,u"o τμήμα:"
            print " "
            print u"Επικεφαλίδα:",epik,u"τετράδες bytes"
            print u"Offset:",payload * i,u"οκτάδες bytes"
            print u"Αναγνωριστικό:",id
            print u"DF:",df
            if i < bulk-1:
                print u"MF:",1
                print u"Καθαρά δεδομένα:",payload*8,u"bytes"
                print u"Συνολικά δεδομένα:",payload*8 + epik*4,u"bytes"
            else:
                print u"MF:",0
                print u"Καθαρά δεδομένα:",(data-(4*epik))%(payload*8),u"bytes"
                print u"Συνολικά δεδομένα:",(data-(4*epik))%(payload*8) + epik*4,u"bytes"
            print "------------------------------------------ "
                
            
