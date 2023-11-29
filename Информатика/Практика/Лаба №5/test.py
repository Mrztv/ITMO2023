import seaborn as sns
import matplotlib as plt

test = sns.load_dataset("mpg").dropna()

print(test)