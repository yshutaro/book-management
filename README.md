書籍管理システム
====
# 内容

書籍の登録・修正・削除ができます。

+ 書籍名（必須。50文字以内）
+ 著者名（必須。50文字以内）
+ 出版社名（必須。50文字以内）

# 作成したバージョン

## Java

```shell script
java --version
openjdk 11.0.3 2019-04-16 LTS
OpenJDK Runtime Environment Corretto-11.0.3.7.1 (build 11.0.3+7-LTS)
OpenJDK 64-Bit Server VM Corretto-11.0.3.7.1 (build 11.0.3+7-LTS, mixed mode)
```

## Kotlin

```shell script
sdk list | grep kotlin
Kotlin (1.3.50) 
```

## gradle

```shell script
sdk list | grep gradle
Gradle (5.6.1)
```

# ローカル環境での起動方法

## git clone

```
git clone https://github.com/yshutaro/book-management.git
cd book-management
```

## Database のセットアップ

sqlite3を使います。
プロジェクトのトップディレクトに `book.db` 名で作成します。
テーブル名は `books` です。
```
sqlite3 book.db
sqlite> CREATE TABLE books(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	author TEXT NOT NULL,
	publisher TEXT NOT NULL
);
```

# 起動

```
./gradlew run     
```

# アクセス

起動したら下記URLにアクセスします。

http://localhost:8080/books

※以下トップ画面と記します。

## 書籍登録

1. 画面上部の「書籍登録」リンクをクリック
2. 新規登録画面にて、書籍名、著者名、出版社名を入力し、[登録]ボタンをクリック。
3. トップ画面に戻ります。トップ画面は検索するまで全件表示します。

## 書籍検索

1. トップ画面の初期表示は登録した書籍の全件表示です。
2. トップ画面で、書籍名、著者名、出版社名を入力し[検索]ボタンをクリックします。
検索条件はAND条件です。それぞれの条件は部分一致です。

## 書籍変更

1. トップ画面で全件表示または検索をして書籍を表示します。
2. 書籍名がリンクになっているので変更する書籍の書籍名リンクをクリックします。
3. 登録と同じ項目の画面が表示されるので、書籍名、著者名、出版社名を入力し、[更新]ボタンをクリック。
4. トップ画面に戻ります。トップ画面は検索するまで全件表示します。

## 書籍削除

1. トップ画面で全件表示または検索をして書籍を表示します。
2. 一番右が削除リンクになっているので削除する書籍の削除リンクをクリックします。
3. トップ画面に戻ります。トップ画面は検索するまで全件表示します。

# 実装できていないこと

+ 書籍登録時のバリデーションエラーで元の画面にエラー文言出力ができていない。（JSONがそのまま出力されるためブラウザの戻るボタンで戻る必要がある）
+ 検索で入力した値の保持ができていない。
+ 登録・変更・削除後にトップ画面に戻るが初期のアクセスと同じ状態になってしまう。
+ テストコードがほんの一部のみしか記述できなかった。
+ 存在しないidへのアクセス時にエラーとなるが特別な画面を用意できなかった。ブラウザの戻るボタンで戻る必要がある。