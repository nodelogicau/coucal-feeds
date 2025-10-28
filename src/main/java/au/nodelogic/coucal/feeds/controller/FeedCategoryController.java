package au.nodelogic.coucal.feeds.controller;

import au.nodelogic.coucal.feeds.data.FeedCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class FeedCategoryController {

    private final FeedCategoryRepository feedCategoryRepository;

    public FeedCategoryController(@Autowired FeedCategoryRepository feedCategoryRepository) {
        this.feedCategoryRepository = feedCategoryRepository;
    }

    @GetMapping("/")
    public String listCategories(Model model) {
        model.addAttribute("categories", feedCategoryRepository.findAll());
        return "categories/list";
    }
}
