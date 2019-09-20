# -*- coding:utf-8 -*-
import re
import requests
import json
import time
import pandas as pd


SPOT = 'MasjidSultan'
COUNTRY = 'sg'
date = []
content = []
id = []
uid = []
score = []
title = []
fail_list = []
csv_file = f'ctrp_{COUNTRY}_{SPOT}.csv'
url = "https://sec-m.ctrip.com/restapi/soa2/12530/json/viewCommentList?_fxpcqlniredt=09031066110211306837"
total_page = 26
interval = 0.2
viewid = 3660

for i in range(1, total_page + 1):
    try:
        print('-' * 20)
        print(f'crawling Page #{i}')

        data = {"pageid":10650000804,"viewid":viewid,"tagid":0,"pagenum":i,"pagesize":10,"contentType":"json","head":{"appid":"100013776","cid":"09031066110211306837","ctok":"","cver":"1.0","lang":"01","sid":"8888","syscode":"09","auth":"","extension":[]},"ver":"7.10.3.0319180000"}
        html=json.loads(
            requests.post(url,data=json.dumps(data)).text
        )

        for each in html["data"]["comments"]:
            print(each)
            id.append(each['id'])
            uid.append(each['uid'])
            title.append(each['title'])
            content.append(each['content'])
            score.append(each['score'])
            date.append(each['date'])
        time.sleep(interval)
    except:
        fail_list.append(i)

result=pd.DataFrame({
    'id': id,
    'uid': uid,
    'title': title,
    'score': score,
    'date':date,
    'content':content,
})

print(f'Saving {len(id)} results to {csv_file}')
result.to_csv(csv_file,index=False, encoding="utf_8_sig")
print(f'failed ones: {fail_list}')
