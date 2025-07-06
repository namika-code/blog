// 20250704 add
// 投稿ページの表示と、投稿の保存処理を行うコントローラー

package com.example.blog.controller;

import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {

    private final PostRepository postRepository;

    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // トップページ表示
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }

    // 投稿処理
    @PostMapping("/post")
    public String createPost(@RequestParam String title, @RequestParam String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
        return "redirect:/";
    }
    
 // 編集画面を表示するための処理（GETリクエスト）
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        // 指定されたIDの投稿を取得（存在しなければ例外を投げる）
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        // モデルに投稿データを渡す（画面に表示するため）
        model.addAttribute("post", post);
        return "edit"; // edit.html を表示
    }

    // 編集フォームから送信された内容で投稿を更新する（POSTリクエスト）
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        post.setId(id); // 編集対象のIDを明示的に設定
        postRepository.save(post); // 投稿を保存（同じIDがあれば上書き）
        return "redirect:/"; // 一覧ページにリダイレクト
    }

    // 指定されたIDの投稿を削除する
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id); // 投稿をDBから削除
        return "redirect:/"; // 一覧ページにリダイレクト
    }
}
