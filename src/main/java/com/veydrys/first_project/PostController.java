package com.veydrys.first_project;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @PostMapping("/api/posts")
    public String savePost(@RequestBody Post post, HttpSession session, Model model) {
        UserInfo currentUser = (UserInfo) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "You must login first!";
        }
        model.addAttribute("user", currentUser);
        post.setIdUser(currentUser.getUsername());
        postRepository.save(post);
        return "Saved";
    }
}

