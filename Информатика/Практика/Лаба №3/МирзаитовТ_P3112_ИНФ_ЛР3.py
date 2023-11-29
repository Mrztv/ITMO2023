import re

print("Для проверки готовых тестов введите 1\nДля Ввода своих тестов введите 2")
choose = input()

#Варианты на работу
#1 (512)
#2 (5)
#3 (0)


if(choose == '1'):
    #1
    test1_1 = '[<O'
    test1_2 = 'Word[<O'
    test1_3 = 'Wrong emoji ]<O'
    test1_4 = 'Two emoji [<O[<O'
    test1_5 = 'NO EMODJI HERE'

    pattern = r"\[\<O"
    print("Задание №1")
    print("Тест №1")
    print(test1_1)
    print( len( re.findall(pattern, test1_1) ))
    #Должен вывести '1'
    print("Тест №2")
    print(test1_2)
    print( len( re.findall(pattern, test1_2) ))
    #Должен вывести '1'
    print("Тест №3")
    print(test1_3)
    print( len( re.findall(pattern, test1_3) ))
    #Должен вывести '0'
    print("Тест №4")
    print(test1_4)
    print( len( re.findall(pattern, test1_4) ))
    #Должен вывести '2'
    print("Тест №5")
    print(test1_5)
    print( len( re.findall(pattern, test1_5) ))
    #Должен вывести '0'

    #2
    pattern2 = re.compile(r'(\w*[аеёиоуыэюя][аеёиоуыэюя](-\w* |\w* )([-,\.\\\/] |)\b(?!\w*[бвгджзйклмнпрстфхцчшщ]\w*[бвгджзйклмнпрстфхцчшщ]\w[бвгджзйклмнпрстфхцчшщ]))', re.IGNORECASE)
    print("\n\nЗадание №2")
    
    test2_1 = 'Ёе рр'
    find2_1 = re.findall(pattern2, test2_1)
    print("Тест №1")
    #Должен вывести "ее"
    print(test2_1)
    for i in find2_1:
        print(i[0])


    print("Тест №2")
    #Должен вывести "Разнообразный, участие"
    test2_2 = 'Разнообразный и богатый опыт говорит нам, что глубокий уровень погружения обеспечивает \
широкому кругу (специалистов) участие в формировании системы массового участия.'
    find2_2 = re.findall(pattern2, test2_2)
    print(test2_2)
    for i in find2_2:
        print(i[0])

    print("Тест №3")
    #Должен вывести "являются"
    test2_3 = 'Однозначно, акционеры крупнейших яя-мы яя - мы компаний являются толко методом политического \
участия и ассоциативно распрее. делены по отраслям.'
    find2_3 = re.findall(pattern2, test2_3)
    print(test2_3)
    for i in find2_3:
        print(i[0])
        
    print("Тест №4")
    #Должен вывести "поражающих"
    test2_4 = 'Мы вынуждены отталкиваться от того, что граница обучения кадров предполагает независимые \
способы реализации экспериментов, поражающих по своей масштабности и грандиозности.'
    find2_4 = re.findall(pattern2, test2_4)
    print(test2_4)
    for i in find2_4:
        print(i[0])
        
    print("Тест №5")
    #Должен вывести "общечеловеческие"
    test2_5 = 'Лишь базовые сценарии поведения пользователей объявлены нарушающими общечеловеческие нормы \
этики и морали.'
    find2_5 = re.findall(pattern2, test2_5)
    print(test2_5)
    for i in find2_5:
        print(i[0])


    #3
    pattern3 = r'(?<!@)[\w\._\d]+@[^_@\.]+\.\w+$'
    pattern3_1 = r'\w*@.*[@]'
    print("\n\nЗадание №3")

    print("Тест №1")
    #Должно вывести "google.com"
    test3_1 = 'timur@goog.ya@le.com'
    find3_1 = re.findall(pattern3_1, test3_1)
    if ( len(find3_1) == 0) :
        find3_1 = re.findall(pattern3, test3_1)
        if ( len(find3_1) != 0):
            find3_1 = re.findall('@', test3_1)
            print(test3_1[test3_1.find('@')+1::])
        else:
            print("Fail!")
    else: print("Fail!")
    
    print("Тест №2")
    #Должно вывести "Fail!"
    test3_2 = '__@google.com'
    find3_2 = re.findall(pattern3_1, test3_2)
    if ( len(find3_2) == 0) :
        find3_2 = re.findall(pattern3, test3_2)
        if ( len(find3_2) != 0):
            find3_2 = re.findall('@', test3_2)
            print(test3_2[test3_2.find('@')+1::])
        else:
            print("Fail!")
    else: print("Fail!")
    
    print("Тест №3")
    #Должно вывести "Fail!"
    test3_3 = 'timur@y_a.com'
    find3_3 = re.findall(pattern3_1, test3_3)
    if ( len(find3_3) == 0) :
        find3_2 = re.findall(pattern3, test3_3)
        if ( len(find3_3) != 0):
            find3_3 = re.findall('@', test3_3)
            print(test3_3[test3_3.find('@')+1::])
        else:
            print("Fail!")
    else: print("Fail!")
    
    print("Тест №4")
    #Должно вывести "Fail!"
    test3_4 = 'timur@google'
    find3_4 = re.findall(pattern3_1, test3_4)
    if ( len(find3_4) == 0) :
        find3_4 = re.findall(pattern3, test3_4)
        if ( len(find3_4) != 0):
            find3_4 = re.findall('@', test3_4)
            print(test3_4[test3_4.find('@')+1::])
        else:
            print("Fail!")
    else: print("Fail!")
    
    print("Тест №5")
    #Должно вывести "Fail!"
    test3_5 = '@google.com'
    find3_5 = re.findall(pattern3_1, test3_5)
    if ( len(find3_5) == 0) :
        find3_5 = re.findall(pattern3, test3_5)
        if ( len(find3_5) != 0):
            find3_5 = re.findall('@', test3_5)
            print(test3_5[test3_5.find('@')+1::])
        else:
            print("Fail!")
    else: print("Fail!")

elif(choose == '2'):
    second_choose = input("Номер задания: ")
    if(second_choose == '1'):
        pattern = r"\[\<O"
        s = input("Введите строку: ")
        print("Найдено", len( re.findall(pattern, s)), "смайликов")
    
    elif(second_choose == '2'):
        pattern = r"\w*[аеёиоуыэюя][аеёиоуыэюя]\w* \b(?!\w*[бвгджзйклмнпрстфхцчшщ]\w*[бвгджзйклмнпрстфхцчшщ]\w[бвгджзйклмнпрстфхцчшщъь])"
        s = input("Введите строку: ") 
        find = re.findall(pattern, s)
        for i in find:
            print(i)
    
    elif(second_choose == '3'):
        pattern = r'(?<!@)[\w\._\d]+@[^_@\.]+\.\w+$'
        pattern_1 = r'\w*@.*[@]'
        s = input("Введите строку: ")
        find3_5 = re.findall(pattern_1, s)
        if ( len(find3_5) == 0) :
            find3_5 = re.findall(pattern, s)
            if ( len(find3_5) != 0):
                find3_5 = re.findall('@', s)
                print(s[s.find('@')+1::])
            else:
                print("Fail!")
        else: print("Fail!")
    
