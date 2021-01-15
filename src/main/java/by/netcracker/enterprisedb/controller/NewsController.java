package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.NewsDTO;
import by.netcracker.enterprisedb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

  private final NewsService newsService;

  @Autowired
  public NewsController(final NewsService newsService) {
    this.newsService = newsService;
  }

  @PostMapping("/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final NewsDTO newsDTO) {
    return ResponseEntity.ok(newsService.save(newsDTO));
  }

  @PutMapping("/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final NewsDTO newsDTO) {
    return ResponseEntity.ok(newsService.update(newsDTO));
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(newsService.findById(id));
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(newsService.getAll());
  }

  @GetMapping("/all/{adminId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("adminId") final Long adminId) {
    return ResponseEntity.ok(newsService.getAllByAdminId(adminId));
  }

  @DeleteMapping("/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    newsService.deleteById(id);
    return ResponseEntity.ok("News deleted");
  }
}
