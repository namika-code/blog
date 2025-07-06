// 20250704 add
// Postエンティティ用のリポジトリ（DB操作を簡単に行う）

package com.example.blog.repository;

import com.example.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // ここにカスタムメソッドも追加できる
}
