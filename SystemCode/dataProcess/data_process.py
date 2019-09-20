from text_analyze import *
import argparse
import os
import pandas
import re
from opencage.geocoder import OpenCageGeocode


def arg_parser():
    parser = argparse.ArgumentParser()
    parser.add_argument('--input_path', '-i', default="data",
                        type=str, help="directory/to/comment/data")
    parser.add_argument('--output_name', '-o', default="Spot", type=str,
                        help="output filename wo extension.")
    parser.add_argument('--key', '-k', type=str,
                        help="Key of opencage geocode. please get your own key on http://sglocate@singpost.com")
    return parser.parse_args()


def geocoding(postal, key, country="Singapore"):
    query = str(postal) + ", " + country
    result = OpenCageGeocode(key).geocode(query, no_annotations=1,
                                          language="en",
                                          proximity='1.29823, 103.85392')

    return result[0]["geometry"]["lat"], result[0]["geometry"]["lng"]


def make_empty_df(row):
    spot_table = pandas.DataFrame(row, columns=["Spot_id", "Spot_name", "City_id", "Country_id", "Open_times", "Close_times", "Ticket",
                                                "Est_duration", "Score", "Postal", "Gourmet", "Cultural", "Downtown", "Resort",
                                                "Bustle", "Family", "Shopping", "Parks", "Museums",
                                                "Observation_deck", "Gardens", "Zoo", "Themeparks", "Neighbourhoods", "Religious_Sites",
                                                "Landmarks", "Historical_Sites", "Island", "Shopping_Malls", "Bridges", "Beaches"],
                                  index=[0])
    spot_table[["Spot_id", "City_id", "Country_id", "Est_duration", "Postal"]] = spot_table[["Spot_id", "City_id", "Country_id", "Est_duration", "Postal"]].astype(int)
    spot_table[["Spot_name", "Open_times", "Close_times"]] = spot_table[["Spot_name", "Open_times", "Close_times"]].astype(str)
    spot_table[["Score", "Gourmet", "Cultural", "Downtown", "Resort", "Bustle", "Family", "Shopping"]] = spot_table[["Score", "Gourmet", "Cultural", "Downtown", "Resort", "Bustle", "Family", "Shopping"]].astype(float)
    spot_table[["Parks", "Museums", "Observation_deck", "Gardens", "Zoo", "Themeparks", "Neighbourhoods", "Religious_Sites", "Landmarks", "Historical_Sites", "Island", "Shopping_Malls", "Bridges", "Beaches"]] = spot_table[["Parks", "Museums", "Observation_deck", "Gardens", "Zoo", "Themeparks", "Neighbourhoods", "Religious_Sites", "Landmarks", "Historical_Sites", "Island", "Shopping_Malls", "Bridges", "Beaches"]].astype(bool)
    return spot_tables


def need_convert(string_like) -> bool:
    return isinstance(string_like, str) and ("\'" in string_like or '\"' in string_like)


def swtich_sing_doub_quot(string: str) -> str:
    out = ""
    if '\"' in string or "\'" in string:
        for char in string:
            if char in ["\'", '\"']:
                out += "\'\'"
            else:
                out += char
        print("replaced with \'\'")
    return out


def sql_formater(df, table_name):
    columns = df.columns[1:]
    columns_str = str(tuple(columns)).replace("'", "")
    lines = ""
    for index, row in df.iterrows():
        row_str = str(tuple([row[col] if not isinstance(row[col], bool) and not need_convert(row[col]) else
                            (str(row[col]).upper() if not need_convert(row[col]) else swtich_sing_doub_quot(row[col]))
                            for col in columns]))
        row_str = row_str.replace("\'FALSE\'", "0")
        row_str = row_str.replace("\'TRUE\'", "1")
        line = f"INSERT INTO {table_name}{columns_str} VALUES{row_str};\n"
        lines += line
    lines = lines.replace('"', "'")
    return lines


def main(args):
    data_raw = None
    spot_table = None
    input_path = args.input_path
    spots = {}
    spot_cnt = 0

    country_df = pandas.DataFrame({
        'Country_id': 0,
        'Country_name': 'Singapore',
        'Est_visa_day': 3   # currently only support china
    }, index=[0])

    city_df = pandas.DataFrame({
        'City_id': 0,
        'City_name': "Singapore",
        "Country_id": 0
    }, index=[0])

    if os.path.exists(input_path):
        if not os.path.exists(os.path.join(input_path, "Spot.csv")):
            list_dir = [name for name in os.listdir(input_path)
                        if os.path.isdir(os.path.join(input_path, name))]
            list_dir += [name for name in os.listdir(input_path)
                         if not os.path.isdir(os.path.join(input_path, name))]

            for subdir in list_dir:
                subpath = os.path.join(input_path, subdir)
                if os.path.isdir(subpath):
                    for subsubdir in os.listdir(subpath):
                        subsubpath = os.path.join(subpath, subsubdir)
                        try:
                            df = pandas.read_csv(subsubpath, sep=',')
                            ch = subdir == "ch"
                            count_dict = {k: [0] * len(v) for k, v in categories(ch).items()}
                            if ch:
                                spot = '_'.join(tuple(subsubpath.split('.')[0].split('_')[2:]))
                            else:
                                spot = subsubpath.split('.')[0].split("\\")[-1]

                            print("=" * 10)
                            print(f"processing {subsubpath}, Spot name: {spot}" +
                                  (", presented before." if spot in spots.keys() else "."))
                            contents = list(df['content'])
                            contents_unique = list(set(contents))
                            df = clean_data(df, contents_unique)

                            for content in list(df['content']):
                                new = count_word(content, verbose=False, ch=ch)
                                count_dict = merge_dict(count_dict, new)

                            count_reduce_dict = {k: [sum(v)] for k, v in count_dict.items()}

                            spot_cnt += 1
                            if spot not in spots.keys():
                                spots[spot] = count_reduce_dict
                            else:
                                spots[spot] = merge_dict(count_reduce_dict, spots[spot])

                            spots[spot]["Score"] = df.mean()["score"].round(3)
                            print("Spot info:", spots[spot])
                        except:
                            print("Pass the", subsubpath)
                else:
                    if subdir == "dataraw.xlsx":
                        data_raw = pandas.read_excel(subpath)

                        spot_table = None

                        for i in range(data_raw.shape[0]):
                            row = {
                                "Spot_id": data_raw.loc[i, "Spot_id"],
                                "Spot_name": data_raw.loc[i, "Spot_name"],
                                "Est_duration": data_raw.loc[i, "Est_duration"],
                                "Country_id": country_df.loc[0, "Country_id"],
                                "City_id": city_df.loc[0, "City_id"],
                                "Ticket": data_raw.loc[i, "Ticket"],
                                "Open_times": data_raw.loc[i, "Open_times"],
                                "Close_times": data_raw.loc[i, "Close_times"],
                                "Postal": int(data_raw.loc[i, "Postal"]),
                                "imageUrl": data_raw.loc[i, "imageUrl"],
                                "introduction": data_raw.loc[i, "introduction"]
                            }

                            Types = data_raw.loc[i, "Types"]
                            for typ in ["Parks", "Museums", "Observation_deck", "Gardens", "Zoo",
                                        "Themeparks", "Neighbourhoods", "Religious_Sites", "Landmarks",
                                        "Historical_Sites", "Island", "Shopping_Malls", "Bridges", "Beaches"]:
                                row[typ] = (typ in Types)

                            if row["Spot_name"] in spots.keys():
                                weights = spots[row["Spot_name"]]
                                sum_weights = sum([v[0] for k, v in weights.items() if k != "Score"])
                                weights_percent = {k: round(float(v[0] / sum_weights), 3) for k, v in weights.items()
                                                   if k != "Score"}

                                row.update(weights_percent.items())
                                row["Score"] = weights["Score"]

                                print("=" * 10)
                                print(f"Inserting row:", row)
                                if spot_table is None:
                                    spot_table = make_empty_df(row)
                                else:
                                    print(spot_table.shape, len(row.keys()))
                                    spot_table.loc[spot_table.shape[0] + 1] = row

            print(spot_table.head())
            save_csv_path = os.path.join(args.input_path, args.output_name + ".csv")
            spot_table.to_csv(save_csv_path)
            print("Table saved to", save_csv_path)
        else:
            spot_table = pandas.read_csv(os.path.join(input_path, "Spot.csv"))

    # ADD IMAGEURL ADDRESS
    imageurl_sdf = pandas.read_csv(os.path.join(input_path, "attractions_index.csv"))
    spot_table["imageUrl"] = imageurl_sdf["image"].to_list()
    spot_table["Addr"] = imageurl_sdf["position"].to_list()

    # ADD introduction and geocode
    description_df = pandas.read_csv(os.path.join(input_path, "description.csv"), encoding="cp1252")
    spot_names_desc = description_df["Spot_name"].to_list()

    introductions = []
    for spot_name in spot_table["Spot_name"].to_list():
        if spot_name in spot_names_desc:
            introductions += [description_df["introduction"].to_list()[spot_names_desc.index(spot_name)].encode("utf-8", "ignore").decode()]
    ltts, lgts = [], []
    for postal in spot_table["Postal"].to_list():
        ltt, lgt = geocoding(postal, args.key)
        ltts.append(ltt)
        lgts.append(lgt)
    spot_table["lgt"] = lgts
    spot_table["ltt"] = ltts
    spot_table["introduction"] = introductions
    spot_table = spot_table.drop(["Activities"], axis=1)

    sql_lines = sql_formater(spot_table, "Spot")
    save_sql_path = os.path.join(args.input_path, args.output_name + ".sql")
    with open(save_sql_path, "w", encoding="utf-8") as f:
        for line in sql_lines:
            f.write(line)
    print("SQL file saved to", save_sql_path)
    spot_table.to_csv(os.path.join(args.input_path, args.output_name + ".csv"))
    print("CSV file saved to", os.path.join(args.input_path, args.output_name + ".csv"))


if __name__ == '__main__':
    main(arg_parser())
