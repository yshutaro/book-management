# git clone

```
git clone https://github.com/yshutaro/book-management.git
cd book-management
```

## Database

sqlite3を使います。
`book.db`

```
sqlite3 book.db
sqlite> CREATE TABLE books(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	author TEXT NOT NULL,
	publisher TEXT NOT NULL
);
```

http://localhost:8080/books
