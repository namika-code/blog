# blog
A Java blog application using an offline database for storing posts and user data.

# Simple Blog Web Application - Spring Boot
## Overview

This is a simple web blog application built with Java and Spring Boot.  
It uses H2 database in file mode for offline persistence and supports creating, viewing, editing, and deleting posts.

## Features

- Create new blog posts  
- Display a list of posts  
- Edit existing posts  
- Delete posts  
- View and manage the database via H2 web console

## Technology Stack

- Java 17  
- Spring Boot  
- Spring Web / Spring Data JPA  
- Thymeleaf  
- H2 Database (file mode)

## How to Run

1. Clone this repository:  
   git clone https://github.com/your-username/blog.git
   cd blog

2. Verify that Java 17 or above is installed:
   java -version

3. Start the Spring Boot application:
   ・In Eclipse: Right-click MyblogApplication.java → Run As → Spring Boot App
   ・Or via terminal:
   ./mvnw spring-boot:run

4. Open your web browser and visit:
   http://localhost:8080/

5. (Optional) Access the H2 database console:
   http://localhost:8080/h2-console
   ・JDBC URL: jdbc:h2:file:./data/blogdb
   ・Username: sa
   ・Password: (leave blank)

## Run with `.jar` file (via GitHub Releases)

1. Download the `.jar` file from the [Releases](https://github.com/your-username/blog/releases) page
2. Open Command Prompt or Terminal in the folder where the `.jar` file is located
3. Run the following command:java -jar blog-0.0.1-SNAPSHOT.jar
4. You can check it in 4.5. just like when you clone the project.
   

Project Structure
```
   blog/
   ├── src/
   │   └── main/
   │       ├── java/
   │       │   └── com/
   │       │       └── example/
   │       │           └── blog/
   │       │               ├── MyblogApplication.java
   │       │               ├── controller/
   │       │               │   └── BlogController.java
   │       │               ├── entity/
   │       │               │   └── Post.java
   │       │               └── repository/
   │       │                   └── PostRepository.java
   │       └── resources/
   │           ├── application.properties
   │           └── templates/
   │               ├── index.html
   │               └── edit.html
   └── pom.xml
```

Notes
・The application uses the H2 database in file mode, so data is persisted even after the app stops.
・Avoid accessing the database concurrently from multiple processes, as it may cause errors.
・When using the H2 console, make sure to disconnect before restarting the app to prevent database locking issues.


# おためしブログ - Spring Boot Web ブログアプリ
## 概要

このアプリは、Java（Spring Boot）を使用して作成したシンプルなWebブログアプリケーションです。  
H2のオフラインファイルDBを使用し、投稿の作成・一覧表示・編集・削除が可能です。

## 主な機能

- 投稿の新規作成
- 投稿の一覧表示
- 投稿の編集機能
- 投稿の削除機能
- H2コンソールによるDB内容の確認

## 技術スタック

- Java 17
- Spring Boot
- Spring Web / Spring Data JPA
- Thymeleaf
- H2 Database（ファイルモード）

## 実行方法

1. このプロジェクトをクローン
   git clone https://github.com/あなたのユーザー名/blog.git
   cd blog

2. Java 17以上がインストールされていることを確認
   java -version

3. Spring Boot アプリケーションを起動
   Eclipse なら MyblogApplication.java を右クリック → Run As → Spring Boot App
   またはターミナルで以下を実行
   ./mvnw spring-boot:run

4. Webアプリをブラウザで確認
   http://localhost:8080/

5. H2コンソールでデータベースを確認（オプション）
   http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:file:./data/blogdb
   ユーザー名: sa
   パスワード: 空欄のまま

---
## `.jar` ファイルからの実行方法（Releases使用）

1. [Releases](https://github.com/あなたのユーザー名/blog/releases) ページから `.jar` ファイルをダウンロード
2. `.jar` ファイルを配置したフォルダでコマンドプロンプトを開く
3. 以下のコマンドを実行：java -jar blog-0.0.1-SNAPSHOT.jar
4. プロジェクトをクローンした場合と同様で4.5.で確認できる


注意事項
・オフラインDB（H2ファイルモード）を使用しているため、アプリを終了してもデータは保持されます。
・複数プロセスで同時にDBへアクセスするとエラーになることがあります。H2コンソール使用後は「Disconnect」ボタンを押してください。


