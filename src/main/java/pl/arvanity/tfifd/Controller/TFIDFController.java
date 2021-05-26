package pl.arvanity.tfifd.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.arvanity.tfifd.Model.Search;
import pl.arvanity.tfifd.Service.TFIDFService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/")
public class TFIDFController {

    private TFIDFService tfidfService;

    public TFIDFController(TFIDFService tfidfService) {
        this.tfidfService = tfidfService;
    }

    @GetMapping
    public String homepage(HttpServletRequest req) {
        req.getSession().setAttribute("freq", null);
        return "index";
    }

    @GetMapping("about")
    public String about(HttpServletRequest req) {
        req.getSession().setAttribute("freq", null);
        return "about";
    }

    @GetMapping("calculator")
    public String calculator(Model model, HttpServletRequest req) {
        req.getSession().setAttribute("freq", null);
        model.addAttribute("search", new Search());
        return "form";
    }

    @PostMapping("calculator")
    public String calculatorForm(@ModelAttribute Search search, HttpServletRequest req) {
        tfidfService.calculate(search, req);
        return "redirect:/frequency";
    }

    @GetMapping("frequency")
    public String frequency() {
        return "frequency";
    }

    @GetMapping("file")
    public String fileForm() {
        return "fileForm";
    }

    @PostMapping("file")
    public String fileFormSuccess(@RequestParam MultipartFile file, @RequestParam String wordToSearch, HttpServletRequest req) throws IOException {
        tfidfService.transform(file, wordToSearch, req);
        return "redirect:/frequency";
    }

    @GetMapping("save")
    public String saveInFile(HttpServletRequest req) {
        tfidfService.saveInFile(req);
        return "redirect:/frequency";
    }
}
