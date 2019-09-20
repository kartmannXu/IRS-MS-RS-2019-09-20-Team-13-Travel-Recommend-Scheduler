import pandas, jieba
import argparse, string
import nltk
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from nltk.corpus import wordnet

CATEGORIES = {
    "Family": ["亲子", "小孩", "孩子", "小孩子", "教育", "教育意义"],
    "Cultural": ["文化", "教育", "风格", "特色", "异域", "文化遗产", "风情", "博物馆"],
    "Gourmet": ["美食", "好吃", "美味", "正宗", "地道"],
    "Downtown": ["市中心"],
    "Shopping": ["购物", "商场", "逛街"],
    "Bustle": ["挤", "人多", "排队", "热闹", "聚集"],
    "Resort": ["度假", "悠闲", "空气清新", "放松"],
}
CATEGORIES_ENG = {
    "Family": ["child", "kid", "family", "educational"],
    "Cultural": ["atmosphere", "culture", "special", "style", "architecture", "eastern", "western", "historical", "museum", "relic", "history", "traditional"],
    "Downtown": ["downtown", "center", "centre"],
    "Shopping": ["shopping", "shop", "mall"],
    "Bustle": ["crowd", "queue", "busy", "touristy", "tourist"],
    "Resort": ["relax", "resort", "chill", "stroll", "peaceful", "quiet", "rest", "fresh", "nature", "wild", "canopy"],
    "Gourmet": ["delicious", "taste", "tasty", "cuisine", "authentic"]
}

def categories(ch):
    return CATEGORIES if ch else CATEGORIES_ENG

STOP = set(stopwords.words('english'))
wnl = WordNetLemmatizer()
remove_punct_dict = dict((ord(punct), None) for punct in string.punctuation)
count_dict = {k: [0] * len(v) for k, v in CATEGORIES.items()}


def get_wordnet_pos(tag):
    if tag.startswith('J'):
        return wordnet.ADJ
    elif tag.startswith('V'):
        return wordnet.VERB
    elif tag.startswith('N'):
        return wordnet.NOUN
    elif tag.startswith('R'):
        return wordnet.ADV
    else:
        return None

def arg_parser():
    parser = argparse.ArgumentParser()
    parser.add_argument('--input_file', '-i', default='data\\ch\\ctrp_sg_Universal_Studio_of_Singapore.csv',
                        type=str, help="path/to/input_csv")
    return parser.parse_args()

def merge_dict(a, b):
    for k in list(set(a.keys()) & set(b.keys())):
        a[k] = [av + bv for av, bv in zip(a[k], b[k])]
    for k in list(set(b.keys()) - set(a.keys())):
         a[k] = b[k]
    return a

def count_each(word_tokens, gt):
    cnt = 0
    for word in word_tokens:
        if word == gt:
            cnt += 1
    return cnt

def word_process(raw, verbose=True, ch=True):
    if ch:
        word_tokens = list(jieba.cut(raw, cut_all=False))
    elif type(raw) == type(""):
        raw_words = nltk.word_tokenize(raw.lower().translate(remove_punct_dict))
        tagged_sent = nltk.pos_tag(raw_words)
        word_tokens = []
        for token in tagged_sent:
            if token[0] not in STOP:
                wordnet_pos = get_wordnet_pos(token[1]) or wordnet.NOUN
                word_tokens.append(wnl.lemmatize(token[0], pos=wordnet_pos))
    else:
        word_tokens = []
    if verbose:
        print(word_tokens)
    return word_tokens

def count_word(raw, verbose=True, ch=True):
    word_tokens = word_process(raw, verbose, ch)
    return {k: [count_each(word_tokens, gt) for gt in v] 
                for k, v in categories(ch).items()}

def clean_data(df, contents):
    contents_dup = contents
    for i in range(df.shape[0]):
        if df.loc[i, 'content'] in contents_dup:
            contents_dup.remove(df.loc[i, 'content'])
        else:
            df.drop([i], inplace=True)
    return df

def word_freq_analysis(raw, word_freq_dict, verbose=True, ch=True):
    word_tokens = word_process(raw, verbose, ch)
    if verbose:
        print(word_tokens[0:5])
    for word in word_tokens:
        if word in word_freq_dict.keys():
            word_freq_dict[word] += 1
        else:
            word_freq_dict[word] = 1
    return word_freq_dict

def main(input_file, count_dict):
    word_freq_dict = {}

    df = pandas.read_csv(input_file, sep=',')
    ch = True
    if "ctrp" in input_file:
        country, spot = tuple(input_file.split('\\')[-1].split('_')[1:])
    else:
        country = "sg"
        spot = input_file.split("\\")[-1]
        ch = False
    spot = spot.split('.')[0]
    print(df.columns.values.tolist())

    contents = list(df['content'])
    contents_unique = list(set(contents))
    df = clean_data(df, contents_unique)

    for content in list(df['content']):
        new = count_word(content, verbose=False, ch=ch)
        count_dict = merge_dict(count_dict, new)
        # word_freq_dict = word_freq_analysis(content, word_freq_dict, verbose=False, ch=ch)

    count_reduce_dict = {k: [sum(v)] for k, v in count_dict.items()}
    count_reduce_dict['country'] = country
    count_reduce_dict['spot'] = spot
    df_out = pandas.DataFrame(count_reduce_dict)

    print(df_out.head())
    df.to_csv(input_file.split('.')[0] + '_ana.csv',index=False, encoding="utf_8_sig")

    '''
    sorted_dict = sorted(word_freq_dict.items(), key=lambda d: d[1], reverse=True)
    for k, v in sorted_dict[:300]:
        print(k, '\t', v)
    '''

    return
if __name__ == '__main__':
    args = arg_parser()
    input_file = args.input_file
    main(input_file, count_dict)
