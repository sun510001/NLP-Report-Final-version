# ツイートを用いた施設の品質が評価するシステムの構築
## 1.	研究目的
近年、多くな人がTripAdvisor[1]の評価システムを使って、自分が利用しなかったの施設と店の優劣を判断する。しかし、利用人数とファンクションの制限のため、こういうアプリはうまく役に立たない。例える、TripAdvisorの施設紹介と評価は観光施設限定である、ホテル、飲食店と観光地しかあらわさない。
　2017年から、Twitterの月間アクティブユーザー人数は3億以上である[2]。その中には自身が施設を体験したことの感想を発信した内容が少なくない。これらを、施設を利用したユーザーからのツイートをTwitter APIを用いて収集して分析すれば、その施設の優劣はわかられる。
## 2.	研究方法
本研究では、Twitter4J[3]を用いて、ユーザーのツイートデータを分析して、施設を利用する時の感想があるツイートを数値化して評価する。まず、施設の名前をツイートのSearch APIで捜索して。次に、捜索したツイートはテキストファイルで保存される。最後、単語感情極性対応表[4]とマッチングして、感想をネガティブからポジティブまでの五つのレブルで分別する。
### 2.1.	施設の捜索プログラム
まず、ファンクションGetAccess()を呼び出してTwitter APIのアクセス許可をもらえる。次に、二つの捜索モードを選択して（0は一つだけのキーワードを捜索する、1は複数のキーワードを一つずつ捜索する）。最後、そのキーワードをファンクションsearchclass()に渡す。
searchclass()は新しいのファイルを作て。そして、ツイートを捜索し、リツイートを省略して。残した内容をファイルに保存する。
### 2.2.	分析プログラム
まずはｍian()ファンクションでリストファイルに全部のツイートを一つずつ読む、一つのツイートを読んだら、その内容をAnaToken()に渡す。AnaToken()は一つのツイートを持って、kuromoji[5]を用いて、ツイートを分割し、品詞をつける。次に、分割したツイートを単語として、対応の品詞と一緒にPNTable()に渡す。PNTable()の役割は品詞を参照して分割した単語を対応の単語感情極性対応表で分析して、単語の点数をつける。単語感情極性対応表で分析するの点数は絶対値が 0.9以上1以下です。絶対値が0.9を接近するの時、その単語の感想表現が弱い、絶対値が1を接近するの時、その単語の感想表現が強い。だから、点数が-1以上-0.9以下の場合は (50*点数+45)とする、点数が0.9以上1以下の場合は (50*点数-45)とする。そして、その結果をAnaToken()に返す、単語の点数の平均値を計算して、その結果、(0.5*点数+2.5)で0から5まで五つのレブルに換算する。そのツイートの点数は全部単語点数の平均値。最後、全部感想入りツイートの点数の平均値はその施設の得点とする。
## 3.	研究結果
「外国人に人気の日本の観光スポット ランキング」を参照して、30選の観光地を捜索プログラムで一週間のツイート捜索した。保存したツイートには、一つの観光地は1000ツイート以内で分析した。その結果には、ユーザーがその施設に対しての感情を数字化して表現できる。
 しかし、プログラムがファイルからツイートを読み込むのはすっこく時間がかかる。そして、保存したツイートには広告ツイートはたくさん残している。また、全ての施設の得点は低い。
## 4.	まとめ
今後課題はまず、データを読み込むの時間がかかるのはマルチスレッドで解決するつもりです。そして、広告のフィルタも必要です。最後、点数の計算も調整すべきと思います。
## 参考文献
[1] Tripadvisor, https://www.tripadvisor.jp/ (2018年6月アクセス)<br/>
[2] Twitterユーザー数, https://ja.wikipedia.org/wiki/Twitter#cite_note-fy-1/<br/>
[3] Twitter4J, http://twitter4j.org/ja/index.html<br/>
[4] 単語感情極性対応表, http://www.lr.pi.titech.ac.jp/~takamura/pndic_ja.html<br/>
[5] kuromoji, https://www.atilika.com/ja/kuromoji/<br/>
