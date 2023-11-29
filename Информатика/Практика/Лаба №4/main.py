import time 
for xcvbn in range(100):
    source_file = open('D:\ИТМО 23-24\Информатика\Практика\Лаба №4\source.yml', 'r')
    out_file_ = open('D:\ИТМО 23-24\Информатика\Практика\Лаба №4\out.json', 'w+')
    out_file = []
    out_file.append('{\n')
    k = 1 
    inBlock = False

    def outBlock():
        global k
        global inBlock
        s = (str)(out_file.pop())
        s = s[:-2]
        out_file.append(s + '\n')
        out_file.append('\t'*(k-1) + '}]\n')
        k-=1
        inBlock = False

    for line in source_file:
        line = line.strip('\n| ',)
        if(line[-1] == ':'):
            if(inBlock == True):
                outBlock()
                s = (str)(out_file.pop())
                s = s[:-1]
                s += ',\n'
                out_file.append(s)


            out_file.append('\t'*k + '"' + line.split(':')[0].replace(' ', '') + '"' + ':' +'\n')
            out_file.append('\t'*k +'{\n')
            k+=1
        if(inBlock == True and line[0] != '-'):
            out_file.append('\t'*k + '"' + line.split(':', 1)[0] + '"' + ': ' + '"' + line.split(':', 1)[1].replace('"', '').lstrip() + '",' + '\n')
        if(line[0] == '-' and inBlock == False):
            out_file.pop()
            k-=1
            out_file.append('\t'*k + '[{\n')
            inBlock = True
            k+=1
        elif(inBlock == True and line[0] == '-'):
            s = (str)(out_file.pop())
            s = s[:-2]
            out_file.append(s + '\n')
            out_file.append('\t'*(k-1) + '},' + '\n')
            out_file.append('\t'*(k-1) + '{' + '\n')

    outBlock()
    while k>0:
        out_file.append('\t'*(k-1) + '}\n')
        k-=1



    #print(out_file)
    out_file_.writelines(out_file)
end = time.perf_counter()
print(time.process_time())