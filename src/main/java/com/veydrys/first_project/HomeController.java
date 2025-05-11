package com.veydrys.first_project;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/getName")
    public String getUsername(HttpSession session, Model model){
        UserInfo currentUser = (UserInfo) session.getAttribute("currentUser");
        model.addAttribute("user", currentUser);
        return currentUser.getUsername();
    }
    @GetMapping("/articles")
    public List<Post> getAllPosts(HttpSession session, Model model) {
        UserInfo currentUser = (UserInfo) session.getAttribute("currentUser");
        model.addAttribute("user", currentUser);
        return postRepository.findAllByIdUser(currentUser.getUsername());
    }
    @GetMapping("/articles/{id}")
    public ResponseEntity<Post> getArticleById(@PathVariable String id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logged out");
    }
}
