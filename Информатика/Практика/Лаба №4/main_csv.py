import time
for i in range(100):
    source_file = open('D:\ИТМО 23-24\Информатика\Практика\Лаба №4\source.yml', 'r')
    out_file_ = open('D:\ИТМО 23-24\Информатика\Практика\Лаба №4\out_csv.csv', 'w+')
    out_file = ['', '']
    flag = False
    FirstFlag = True

    for line in source_file:
        line = line.strip('\n| ',)
        line = line.replace('"', '')
        #print(line)

        if flag and line != '-' and FirstFlag:
            line = line.split(':', 1)
            out_file[0] += line[0].strip() + ', '
            out_file[1] += line[1].strip() + ', '
        if flag and line != '-' and not FirstFlag:
            line = line.split(':', 1)
            out_file[1] += line[1].strip() + ', '
        if(line == '-' and not flag): 
            flag = True
        elif(line == '-' and flag):
            out_file[-2] = out_file[-2][:-2] + '\n'
            out_file[-1] = out_file[-1][:-2] + '\n'
            FirstFlag = False
        elif(line == '-' and flag and not FirstFlag):
            out_file[-1] = out_file[-1][:-2] + '\n'

        

    #print(out_file)
    out_file_.writelines(out_file)
print(time.process_time())