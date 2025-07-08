// 20250704 add
// 投稿ページの表示と、投稿の保存処理を行うコントローラー

package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // ---------------------------------------
    // トップページ（投稿一覧＋ページネーション）
    // ---------------------------------------
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postPage = postRepository.findAll(pageable);

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("post", new Post()); // 投稿フォーム用オブジェクト

        return "index";
    }

    // ---------------------------------------
    // 投稿作成：フォーム表示
    // ---------------------------------------
    @GetMapping("/posts/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post"; // templates/post.html
    }

    // ---------------------------------------
    // 投稿作成：保存処理
    // ---------------------------------------
    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return "redirect:/";
    }

    // ※以下と機能重複していたため、統一：
    // @PostMapping("/posts/create") → /posts に統一済

    // ---------------------------------------
    // 投稿編集：フォーム表示
    // ---------------------------------------
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "edit";
    }

    // 投稿編集：更新処理
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, Principal principal) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));

        // 投稿者チェック（編集できるのは投稿者のみ）
        if (!existingPost.getUser().getUsername().equals(principal.getName())) {
            throw new RuntimeException("Unauthorized");
        }

        // 更新したい項目だけセット
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        postRepository.save(existingPost);

        // 詳細ページにリダイレクト（編集後は編集画面ではなく詳細画面へ戻す場合）
        return "redirect:/posts/" + existingPost.getId();
    }
    // ---------------------------------------
    // 投稿削除
    // ---------------------------------------
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }

    // ---------------------------------------
    // 投稿詳細（投稿者かどうかでページを分岐）
    // ---------------------------------------
    /*
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model, Principal principal) {
        Post post = postRepository.findById(id).orElseThrow();
        String currentUsername = principal.getName();

        if (post.getUser().getUsername().equals(currentUsername)) {
            return "redirect:/edit/" + id;
        }

        model.addAttribute("post", post);
        return "post-detail";
    }
    */
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model, Principal principal) {
        Post post = postRepository.findByIdWithUser(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        String currentUsername = principal.getName();

        if (post.getUser().getUsername().equals(currentUsername)) {
            return "redirect:/edit/" + id;
        }

        model.addAttribute("post", post);
        return "post-detail";
    }

    // ---------------------------------------
    // 投稿一覧（別ページでの表示用）
    // ---------------------------------------
    @GetMapping("/posts")
    public String listPosts(Model model, Principal principal) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("currentUsername", principal.getName());
        return "post-list"; // templates/post-list.html
    }

    // ---------------------------------------
    // （未使用）新規投稿用フォーム（post_form.html用）
    // ---------------------------------------
    @GetMapping("/post")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post_form"; // templates/post_form.html
    }
}


/*
package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.security.Principal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Controller
public class BlogController {

    private final PostRepository postRepository;

    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // トップページ表示（ページネーション対応）
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;

        // PageRequestの引数は0始まりなので (page-1)
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postPage = postRepository.findAll(pageable);

        model.addAttribute("posts", postPage.getContent());         // 表示用の投稿リスト
        model.addAttribute("totalPages", postPage.getTotalPages()); // ページ数
        model.addAttribute("page", page);                           // 現在のページ番号
        model.addAttribute("post", new Post());                     // 投稿フォーム用の空オブジェクト

        return "index";
    }

    // 投稿処理
    @PostMapping("/post")
    public String createPost(@ModelAttribute Post post) {
        post.setCreatedAt(LocalDateTime.now()); // 投稿日時を設定
        postRepository.save(post);
        return "redirect:/";
    }

    // 編集画面を表示するための処理（GETリクエスト）
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "edit";
    }

    // 編集フォームから送信された内容で投稿を更新する（POSTリクエスト）
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        post.setId(id);
        postRepository.save(post);
        return "redirect:/";
    }

    // 指定されたIDの投稿を削除する
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }
    
    // 新規投稿処理(GETリクエスト)
    @GetMapping("/post")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post_form"; // templates/post_form.html を表示
    }
    

    // タイトルクリックで詳細ページへ
//    @GetMapping("/posts/{id}")
//    public String showPost(@PathVariable Long id, Model model, Principal principal) {
//        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
//        model.addAttribute("post", post);

        // ログインユーザー名と投稿者が一致するかチェック
//        boolean isOwner = post.getUser().getUsername().equals(principal.getName());
//        model.addAttribute("isOwner", isOwner);

//        return "post-detail";
//    }
    
    // 投稿主とそれ以外でページ分岐
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model, Principal principal) {
        Post post = postRepository.findById(id).orElseThrow();
        String currentUsername = principal.getName();

        if (post.getUser().getUsername().equals(currentUsername)) {
            return "redirect:/posts/edit/" + id;  // 投稿者なら編集画面へ
        }

        model.addAttribute("post", post);
        return "post-detail";  // 投稿者以外は詳細画面
    }
    
    // 一覧画面にくる時にログインユーザー名を渡す
    @GetMapping("/posts")
    public String listPosts(Model model, Principal principal) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("currentUsername", principal.getName());
        return "post-list";  // post-list.html
    }
    
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return "redirect:/posts";
    }
}
*/
