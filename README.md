# Sample Blog - Spring Boot Web  youBlog Application

## Overview

This is a simple web blog application built with Java (Spring Boot).  
It uses an offline file-based H2 database and supports **creating, editing, deleting, and viewing blog posts**, along with **login functionality and user-specific access control**.

---

## ✅ Features

- Create new posts (authenticated users)
- View post list and detailed post pages
- Edit and delete posts (by the author only)
- Admin can view all posts
- Login, registration, and authentication via Spring Security
- Role-based access control (user/admin)
- View database content via H2 Console

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Web / Spring Data JPA
- Spring Security
- Thymeleaf
- H2 Database (file-based mode)

---

## 🚀 Getting Started

### 1. Clone the project

```bash
git clone https://github.com/your-username/blog.git
cd blog
```

### 2. Make sure Java 17 or later is installed

```bash
java -version
```

### 3. Run the Spring Boot application

#### Using Maven (from terminal)

```bash
./mvnw spring-boot:run
```

#### Or using an IDE (Eclipse / IntelliJ)

Right-click on `MyblogApplication.java` → Run As → Spring Boot App

### 4. Open the web application in your browser

```
http://localhost:8080/
```
---

## 🧪 H2 Console (for development/testing)

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/blogdb`
- Username: `sa`
- Password: *(leave blank)*

*Note: When using the H2 console, please click "Disconnect" after you're done to avoid database lock issues when accessing from multiple processes.*

---

## 🔐 Login Credentials

### Admin Account

- Username: `admin`  
- Password: `adminpass`  

### Regular Users

Can register via the sign-up page on the login screen.

---

## 🗂️ Page Routes

| Path             | Description                                     |
|------------------|-------------------------------------------------|
| `/`              | Post list                                       |
| `/post/new`      | New post creation page (login required)         |
| `/post/{id}`     | Post detail page (other users or admin only)         |
| `/edit/{id}`     | Edit post page (author only)                    |
| `/login`         | Login page                                      |
| `/signup`        | User registration page                          |

---

## 📁 File Structure

```
File Structure
   blog/
   ├── src/
   │   └── main/
   │       ├── java/
   │       │   └── com/
   │       │       └── example/
   │       │           └── demo/
   │       │               ├── BlogApplication.java
   │       │               ├── config/
   │       │               │   └── SecurityConfig.java
   │       │               │   └── UserDataInitializer.java
   │       │               ├── controller/
   │       │               │   └── BlogController.java
   │       │               │   └── LoginController.java
   │       │               │   └── UserController.java
   │       │               ├── entity/
   │       │               │   └── Post.java
   │       │               │   └── User.java
   │       │               ├── repository/
   │       │               │   └── PostRepository.java
   │       │               │   └── UserRepository.java
   │       │               └── service/
   │       │                   └── CustomUserDetailsService.java
   │       └── resources/
   │           ├── application.properties
   │           └── templates/
   │               ├── edit.html
   │               ├── index.html
   │               ├── login.html
   │               ├── post_form.html
   │               ├── post-detail.html
   │               ├── register.html
   │               └── user-list.html
   └── pom.xml

```

---

## 📦 License

This project is licensed under the [MIT License](LICENSE).

---

## ⚠️ Notes

- This app uses an offline H2 database in file-based mode, so data is persisted even after shutdown.
- Avoid accessing the database simultaneously from multiple processes, as it may cause locking issues.





# おためしブログ - Spring Boot Web ブログアプリ

## 概要

このアプリは、Java（Spring Boot）を使用して作成したシンプルなWebブログアプリケーションです。  
H2のオフラインファイルDBを使用し、**投稿の作成・編集・削除・閲覧**に加え、**ログイン機能とユーザーごとのアクセス制御**が含まれています。

---

## ✅ 主な機能

- 投稿の新規作成（ログインユーザー）
- 投稿の一覧表示・詳細ページ
- 投稿の編集・削除（投稿者のみ）
- 管理者（admin）による全投稿の閲覧
- Spring Security によるログイン・新規登録・認証
- ユーザー別アクセス制限
- H2コンソールによるDB内容の確認

---

## 🛠️ 技術スタック

- Java 17 以上
- Spring Boot 3.x
- Spring Web / Spring Data JPA
- Spring Security
- Thymeleaf
- H2 Database（ファイルモード）

---

## 🚀 実行方法（セットアップ手順）

### 1. このプロジェクトをクローン

```bash
git clone https://github.com/あなたのユーザー名/blog.git
cd blog
```

### 2. Java 17以上がインストールされていることを確認

```bash
java -version
```

### 3. Spring Boot アプリケーションを起動

#### Mavenの場合（ターミナルから）

```bash
./mvnw spring-boot:run
```

#### または IDE（Eclipse / IntelliJ）を使用する場合

`MyblogApplication.java` を右クリック → Run As → Spring Boot App

### 4. ブラウザでWebアプリを確認

```
http://localhost:8080/
```

---

## 🧪 H2コンソール（DB確認用）

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/blogdb`
- ユーザー名: `sa`
- パスワード: （空欄）

※ 開発用。H2コンソール使用後は「Disconnect」ボタンで切断してください。複数プロセスで同時アクセスするとエラーになる場合があります。

---

## 🔐 ログイン情報

### 管理者アカウント

- ユーザー名: `admin`  
- パスワード: `adminpass`  

### 一般ユーザー

ログイン画面にて新規会員登録


---

## 🗂️ 画面構成（ルーティング）

| パス             | 内容                         |
|------------------|------------------------------|
| `/`              | 投稿一覧                     |
| `/post/new`      | 新規投稿ページ（ログイン必須） |
| `/post/{id}`     | 投稿詳細ページ（他ユーザーまたは管理者） |
| `/edit/{id}`     | 投稿編集ページ（投稿者のみ） |
| `/login`         | ログインページ               |
| `/signup`        | 新規登録ページ               |

---

## 📦 ライセンス

このプロジェクトは [MIT License](LICENSE) のもとで公開されています。

---

## ⚠️ 注意事項

- オフラインDB（H2ファイルモード）を使用しているため、アプリを終了してもデータは保持されます。
- H2コンソールの同時使用やIDEからの多重実行でDBロックが発生することがあります。
