import yaml
import json
import time

for i in range(100):
    with open("source.yml", 'r') as yaml_in, open("out_lib.json", "w") as json_out:
        yaml_object = yaml.safe_load(yaml_in)
        json_object = json.dumps(yaml_object, indent=2)
        json_out.writelines(json_object)
print(time.process_time())