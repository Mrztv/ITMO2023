import re
import time
for i in range(100):
    source_file = open('D:\ИТМО 23-24\Информатика\Практика\Лаба №4\source.yml', 'r')
    out_file_ = open('D:\ИТМО 23-24\Информатика\Практика\Лаба №4\out_regex.json', 'w+')
    out_file = []
    out_file.append('{\n')
    k = 1 
    inBlock = False
    with open(r"D:\ИТМО 23-24\Информатика\Практика\Лаба №4\source.yml", 'r') as fp:
        for sizeOfFile, line in enumerate(fp):
            pass

    for i, line in enumerate(source_file):
        pattern = r"((.+): (.+))|((.+):)|(-)"
        line = line.strip('\n| ')
        line = line.replace('"', '')
        new_line = re.findall(pattern, line)
        new_line = list(new_line[0])
        #print(new_line)
        if(new_line[-1] == ''):
            new_line.pop()
            if(new_line[-1] == ''):
                new_line.pop()
                new_line.pop()
                new_line.pop(0)
                if(new_line[1].isdigit()):
                    out_file.append('\t'*k + '"' + new_line[0] + '": ' + new_line[1] + ',\n')
                else:
                    out_file.append('\t'*k + '"' + new_line[0] + '": "' + new_line[1] + '",\n')
            else:
                new_line.pop(0)
                new_line.pop(0)
                new_line.pop(0)
                new_line.pop(0)
                #print(new_line)
                out_file.append('\t'*k + '"' + new_line[0] + '":\n')
                out_file.append('\t'*k + '{\n')
                k+=1
        else:
            if(inBlock == False):
                out_file.pop()
                k-=1
                out_file.append('\t'*k + '[{\n')
                k+=1
                inBlock = True
            else:
                s = out_file.pop()
                s = s[:-2]
                out_file.append(s + '\n')
                k-=1
                out_file.append('\t'*k + '},\n')
                out_file.append('\t'*k + '{\n')
                k+=1

        if(i == sizeOfFile and inBlock):
            s = out_file.pop()
            s = s[:-2]
            out_file.append(s + '\n')
            k-=1
            out_file.append('\t'*k + '}]\n')


    while k>0:
        out_file.append('\t'*(k-1) + '}\n')
        k-=1



    #print(out_file)
    out_file_.writelines(out_file)
print(time.process_time())