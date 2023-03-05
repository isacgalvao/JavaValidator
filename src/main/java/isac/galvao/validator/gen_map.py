from os import listdir, rename

annotations_dir = 'annotations/string'
validators_dir = 'annotationvalidators/string'

annotation_files = [i for i in listdir(
    annotations_dir) if not i.startswith("__")]
validators_files = [i for i in listdir(
    validators_dir) if not i.startswith("__")]

template = "validators.put({i}.class, new {i}Validator());"

for i in annotation_files:
    cleaned = i.replace(".java", "")
    print(template.format(i=cleaned))
