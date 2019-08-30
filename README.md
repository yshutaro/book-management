# git clone

```
git clone https://github.com/yshutaro/book-management.git
cd book-management
```

## Database

```aidl
sqlite3 book.db
sqlite> CREATE TABLE books(id INTEGER PRIMARY KEY, name TEXT NOT NULL, author TEXT NOT NULL, publisher TEXT NOT NULL);
```
