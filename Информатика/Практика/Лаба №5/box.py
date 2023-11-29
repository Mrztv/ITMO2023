import csv
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import numpy as np
# tips = seaborn.load_dataset("tips")
# print(tips)
MaxData = int(-1)
Max = {'Sep': [], 'Oct': []}
link = "SPFB.RTS-12.18_180901_181231.csv"
with open(link, newline='') as csvfile:
    file = pd.read_csv(csvfile)

    file = file.drop(['<TICKER>', '<PER>', '<TIME>', '<VOL>'], axis=1)
    file = file.drop(file[(file['<DATE>'] != '19/09/18') & (file['<DATE>'] != '19/10/18') & (file['<DATE>'] != '19/11/18') & (file['<DATE>'] != '19/12/18')].index)
    file.replace(to_replace='19/09/18', value='September', inplace=True)
    file.replace(to_replace='19/10/18', value='October', inplace=True)
    file.replace(to_replace='19/11/18', value='November', inplace=True)
    file.replace(to_replace='19/12/18', value='December', inplace=True)
    # file.rename(columns={'<HIGH>': 'HIGH', '<LOW>':'LOW', '<OPEN>':'OPEN', '<CLOSE>':'CLOSE'})
    September = pd.DataFrame(columns=['HIGH','LOW','OPEN','CLOSE'])
    September = file.loc[file['<DATE>'].str.contains('September'), ['<LOW>', '<HIGH>', '<OPEN>', '<CLOSE>']]
    September  = September.rename(columns={'<LOW>':'LOW','<HIGH>':'HIGH','<CLOSE>':'CLOSE','<OPEN>':'OPEN'})
    September = September.add_suffix('\nSep')
    print(September)

    October = pd.DataFrame(columns=['HIGH','LOW','OPEN','CLOSE'])
    October = file.loc[file['<DATE>'].str.contains('October'), ['<LOW>', '<HIGH>', '<OPEN>', '<CLOSE>']]
    October  = October.rename(columns={'<LOW>':'LOW','<HIGH>':'HIGH','<CLOSE>':'CLOSE','<OPEN>':'OPEN'})
    October = October.add_suffix('\nOct')
    print(October)

    
    November = pd.DataFrame(columns=['HIGH','LOW','OPEN','CLOSE'])
    November = file.loc[file['<DATE>'].str.contains('November'), ['<LOW>', '<HIGH>', '<OPEN>', '<CLOSE>']]
    November = November.rename(columns={'<LOW>':'LOW','<HIGH>':'HIGH','<CLOSE>':'CLOSE','<OPEN>':'OPEN'})
    November = November.add_suffix('\nNov')
    print(November)

    December = pd.DataFrame(columns=['HIGH','LOW','OPEN','CLOSE'])
    December = file.loc[file['<DATE>'].str.contains('December'), ['<LOW>', '<HIGH>', '<OPEN>', '<CLOSE>']]
    December  = December.rename(columns={'<LOW>':'LOW','<HIGH>':'HIGH','<CLOSE>':'CLOSE','<OPEN>':'OPEN'})
    December = December.add_suffix('\nDec')
    print(December)
    print(December.melt())

    all = pd.concat( (September.melt(), October.melt(), November.melt(), December.melt()) )
    
    print(all)
    g = sns.boxplot(data=all, x = "variable", y = "value", width=1, gap=0.1, palette="Blues", )
    
    


plt.grid(True, axis='y')
plt.legend(bbox_to_anchor=(1.0, 1), loc='upper left', borderaxespad=0)
plt.xlabel("Mounts") 
plt.show()