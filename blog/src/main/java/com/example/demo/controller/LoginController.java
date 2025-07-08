// 20250707 add
/**
 * ログイン画面を表示するコントローラークラス
 * 
 * "/login"へのGETリクエストを処理し、ログイン画面のビューを返す。
 * 認証処理自体はSpring Securityが担当。
 */

package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // /login へのGETアクセス時に login.html を返す
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
