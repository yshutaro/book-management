# git clone

```
git clone https://github.com/yshutaro/book-management.git
cd book-management
```

# Database のセットアップ

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

# 実装できていないこと

+ 書籍登録時のバリデーションエラーで元の画面にエラー文言出力ができていない。（JSONがそのまま出力されるためブラウザの戻るボタンで戻る必要がある）
+ 検索で入力した値の保持ができていない。
+ 登録・変更・削除後にトップ画面に戻るが初期のアクセスと同じ状態になってしまう。
+ テストコードがほんの一部のみしか記述できなかった。
+ 存在しないidへのアクセス時にエラーとなるが特別な画面を用意できなかった。ブラウザの戻るボタンで戻る必要がある。