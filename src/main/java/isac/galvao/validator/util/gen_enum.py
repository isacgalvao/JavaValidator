alpha: dict[str, str] = {
    'en_US': r"^[A-Z]+$",
    'az_AZ': r"^[A-VXYZÇƏĞİıÖŞÜ]+$",
    'bg_BG': r"^[А-Я]+$",
    'cs_CZ': r"^[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ]+$",
    'da_DK': r"^[A-ZÆØÅ]+$",
    'de_DE': r"^[A-ZÄÖÜß]+$",
    'el_GR': r"^[Α-ώ]+$",
    'es_ES': r"^[A-ZÁÉÍÑÓÚÜ]+$",
    'fa_IR': r"^[ابپتثجچحخدذرزژسشصضطظعغفقکگلمنوهی]+$",
    'fi_FI': r"^[A-ZÅÄÖ]+$",
    'fr_FR': r"^[A-ZÀÂÆÇÉÈÊËÏÎÔŒÙÛÜŸ]+$",
    'it_IT': r"^[A-ZÀÉÈÌÎÓÒÙ]+$",
    'ja_JP': r"^[ぁ-んァ-ヶｦ-ﾟ一-龠ー・。、]+$",
    'nb_NO': r"^[A-ZÆØÅ]+$",
    'nl_NL': r"^[A-ZÁÉËÏÓÖÜÚ]+$",
    'nn_NO': r"^[A-ZÆØÅ]+$",
    'hu_HU': r"^[A-ZÁÉÍÓÖŐÚÜŰ]+$",
    'pl_PL': r"^[A-ZĄĆĘŚŁŃÓŻŹ]+$",
    'pt_PT': r"^[A-ZÃÁÀÂÄÇÉÊËÍÏÕÓÔÖÚÜ]+$",
    'ru_RU': r"^[А-ЯЁ]+$",
    'sl_SI': r"^[A-ZČĆĐŠŽ]+$",
    'sk_SK': r"^[A-ZÁČĎÉÍŇÓŠŤÚÝŽĹŔĽÄÔ]+$",
    'sr_RS_latin': r"^[A-ZČĆŽŠĐ]+$",
    'sr_RS': r"^[А-ЯЂЈЉЊЋЏ]+$",
    'sv_SE': r"^[A-ZÅÄÖ]+$",
    'th_TH': r"^[ก-๐\s]+$",
    'tr_TR': r"^[A-ZÇĞİıÖŞÜ]+$",
    'uk_UA': r"^[А-ЩЬЮЯЄIЇҐі]+$",
    'vi_VN': r"^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴĐÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸ]+$",
    'ko_KR': r"^[ㄱ-ㅎㅏ-ㅣ가-힣]*$noncase",
    'ku_IQ': r"^[ئابپتجچحخدرڕزژسشعغفڤقکگلڵمنوۆھەیێيطؤثآإأكضصةظذ]+$",
    'ar': r"^[ءآأؤإئابةتثجحخدذرزسشصضطظعغفقكلمنهوىيًٌٍَُِّْٰ]+$noncase",
    'he': r"^[א-ת]+$noncase",
    'fa': r"^['آاءأؤئبپتثجچحخدذرزژسشصضطظعغفقکگلمنوهةی']+$",
    'bn': r"^['ঀঁংঃঅআইঈউঊঋঌএঐওঔকখগঘঙচছজঝঞটঠডঢণতথদধনপফবভমযরলশষসহ়ঽািীুূৃৄেৈোৌ্ৎৗড়ঢ়য়ৠৡৢৣৰৱ৲৳৴৵৶৷৸৹৺৻']+$noncase",
    'hi_IN': r"^[\u0900-\u0961]+[\u0972-\u097F]*$",
    'si_LK': r"^[\u0D80-\u0DFF]+$noncase",
}

alphanumeric: dict[str, str] = {
    'en_US': r"^[0-9A-Z]+$",
    'az_AZ': r"^[0-9A-VXYZÇƏĞİıÖŞÜ]+$",
    'bg_BG': r"^[0-9А-Я]+$",
    'cs_CZ': r"^[0-9A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ]+$",
    'da_DK': r"^[0-9A-ZÆØÅ]+$/",
    'de_DE': r"^[0-9A-ZÄÖÜß]+$",
    'el_GR': r"^[0-9Α-ω]+$",
    'es_ES': r"^[0-9A-ZÁÉÍÑÓÚÜ]+$",
    'fi_FI': r"^[0-9A-ZÅÄÖ]+$",
    'fr_FR': r"^[0-9A-ZÀÂÆÇÉÈÊËÏÎÔŒÙÛÜŸ]+$",
    'it_IT': r"^[0-9A-ZÀÉÈÌÎÓÒÙ]+$",
    'ja_JP': r"^[0-9０-９ぁ-んァ-ヶｦ-ﾟ一-龠ー・。、]+$",
    'hu_HU': r"^[0-9A-ZÁÉÍÓÖŐÚÜŰ]+$",
    'nb_NO': r"^[0-9A-ZÆØÅ]+$",
    'nl_NL': r"^[0-9A-ZÁÉËÏÓÖÜÚ]+$",
    'nn_NO': r"^[0-9A-ZÆØÅ]+$",
    'pl_PL': r"^[0-9A-ZĄĆĘŚŁŃÓŻŹ]+$",
    'pt_PT': r"^[0-9A-ZÃÁÀÂÄÇÉÊËÍÏÕÓÔÖÚÜ]+$",
    'ru_RU': r"^[0-9А-ЯЁ]+$",
    'sl_SI': r"^[0-9A-ZČĆĐŠŽ]+$",
    'sk_SK': r"^[0-9A-ZÁČĎÉÍŇÓŠŤÚÝŽĹŔĽÄÔ]+$",
    'sr_RS_latin': r"^[0-9A-ZČĆŽŠĐ]+$",
    'sr_RS': r"^[0-9А-ЯЂЈЉЊЋЏ]+$",
    'sv_SE': r"^[0-9A-ZÅÄÖ]+$",
    'th_TH': r"^[ก-๙\s]+$",
    'tr_TR': r"^[0-9A-ZÇĞİıÖŞÜ]+$",
    'uk_UA': r"^[0-9А-ЩЬЮЯЄIЇҐі]+$",
    'ko_KR': r"^[0-9ㄱ-ㅎㅏ-ㅣ가-힣]*$noncase",
    'ku_IQ': r"^[٠١٢٣٤٥٦٧٨٩0-9ئابپتجچحخدرڕزژسشعغفڤقکگلڵمنوۆھەیێيطؤثآإأكضصةظذ]+$",
    'vi_VN': r"^[0-9A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴĐÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸ]+$",
    'ar': r"^[٠١٢٣٤٥٦٧٨٩0-9ءآأؤإئابةتثجحخدذرزسشصضطظعغفقكلمنهوىيًٌٍَُِّْٰ]+$noncase",
    'he': r"^[0-9א-ת]+$noncase",
    'fa': r"^['0-9آاءأؤئبپتثجچحخدذرزژسشصضطظعغفقکگلمنوهةی۱۲۳۴۵۶۷۸۹۰']+$",
    'bn': r"^['ঀঁংঃঅআইঈউঊঋঌএঐওঔকখগঘঙচছজঝঞটঠডঢণতথদধনপফবভমযরলশষসহ়ঽািীুূৃৄেৈোৌ্ৎৗড়ঢ়য়ৠৡৢৣ০১২৩৪৫৬৭৮৯ৰৱ৲৳৴৵৶৷৸৹৺৻']+$noncase",
    'hi_IN': r"^[\u0900-\u0963]+[\u0966-\u097F]*$",
    'si_LK': r"^[0-9\u0D80-\u0DFF]+$noncase",
}

decimal = {
    'en_US': '.',
    'ar': '٫',
}

englishLocales = ['AU', 'GB', 'HK', 'IN', 'NZ', 'ZA', 'ZM']

for value in englishLocales:
    locale = f'en_{value}'
    alpha[locale] = alpha['en_US']
    alphanumeric[locale] = alphanumeric['en_US']
    decimal[locale] = decimal['en_US']

arabicLocales = ['AE', 'BH', 'DZ', 'EG', 'IQ', 'JO', 'KW', 'LB', 'LY',
                 'MA', 'QM', 'QA', 'SA', 'SD', 'SY', 'TN', 'YE']

for value in arabicLocales:
    locale = f'ar_{value}'
    alpha[locale] = alpha['ar']
    alphanumeric[locale] = alphanumeric['ar']
    decimal[locale] = decimal['ar']

farsiLocales = ['IR', 'AF']

for value in farsiLocales:
    locale = f'fa_{value}'
    alphanumeric[locale] = alphanumeric['fa']
    decimal[locale] = decimal['ar']

bengaliLocales = ['BD', 'IN']

for value in bengaliLocales:
    locale = f'bn_{value}'
    alpha[locale] = alpha['bn']
    alphanumeric[locale] = alphanumeric['bn']
    decimal[locale] = decimal['en_US']


dotDecimal = ['ar_EG', 'ar_LB', 'ar_LY']
commaDecimal = [
    'bg_BG', 'cs_CZ', 'da_DK', 'de_DE', 'el_GR', 'en_ZM', 'es_ES', 'fr_CA', 'fr_FR',
    'id_ID', 'it_IT', 'ku_IQ', 'hi_IN', 'hu_HU', 'nb_NO', 'nn_NO', 'nl_NL', 'pl_PL', 'pt_PT',
    'ru_RU', 'si_LK', 'sl_SI', 'sr_RS@latin', 'sr_RS', 'sv_SE', 'tr_TR', 'uk_UA', 'vi_VN',
]

for i in range(len(dotDecimal)):
    decimal[dotDecimal[i]] = decimal['en_US']

for i in range(len(commaDecimal)):
    decimal[commaDecimal[i]] = ','

alpha['fr_CA'] = alpha['fr_FR']
alphanumeric['fr_CA'] = alphanumeric['fr_FR']

alpha['pt_BR'] = alpha['pt_PT']
alphanumeric['pt_BR'] = alphanumeric['pt_PT']
decimal['pt_BR'] = decimal['pt_PT']

alpha['pl_Pl'] = alpha['pl_PL']
alphanumeric['pl_Pl'] = alphanumeric['pl_PL']
decimal['pl_Pl'] = decimal['pl_PL']

alpha['fa_AF'] = alpha['fa']

############### processing ####################

alpha = {k: alpha[k] for k in sorted(alpha)}
alphanumeric = {k: alphanumeric[k] for k in sorted(alphanumeric)}
decimal = {k: decimal[k] for k in sorted(decimal)}

enum_template = '{locale}("{regex}", Pattern.compile("{regex}"{flags})),\n'
decimal_template = '{locale}("{value}"),\n'

alpha_values, alphanumeric_values, decimal_values = [], [], []

for k, v in alpha.items():
    alpha_values.append(enum_template.format(locale=k, regex=str(
        v).replace("noncase", "").replace("\\", "\\\\"), flags=", Pattern.CASE_INSENSITIVE" if not v.endswith('noncase') else ""))

for k, v in alphanumeric.items():
    alphanumeric_values.append(enum_template.format(locale=k, regex=str(
        v).replace("noncase", "").replace("\\", "\\\\"), flags=", Pattern.CASE_INSENSITIVE" if not v.endswith('noncase') else ""))

for k, v in decimal.items():
    decimal_values.append(decimal_template.format(locale=k, value=v))

with open('alpha.txt', 'w', encoding='UTF-8') as f:
    f.write("".join(alpha_values))

with open('alphanumeric.txt', 'w', encoding='UTF-8') as f:
    f.write("".join(alphanumeric_values))

with open('decimal.txt', 'w', encoding='UTF-8') as f:
    f.write("".join(decimal_values))
