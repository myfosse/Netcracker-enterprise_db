package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.NewsDTO;
import by.netcracker.enterprisedb.payload.response.MessageResponse;
import by.netcracker.enterprisedb.service.NewsService;
import io.swagger.annotations.ApiOperation;
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

  @ApiOperation(value = "Add news", notes = "This method allows admin add new news")
  @PostMapping("/admin/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final NewsDTO newsDTO) {
    return ResponseEntity.ok(newsService.save(newsDTO));
  }

  @ApiOperation(value = "Update news", notes = "This method allows admin update news")
  @PutMapping("/admin/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final NewsDTO newsDTO) {
    if (newsDTO.getId() == null) {
      return ResponseEntity.badRequest().body("Unknown id");
    }
    return ResponseEntity.ok(newsService.update(newsDTO));
  }

  @ApiOperation(value = "Get news by ID", notes = "This method allows everyone get news by ID")
  @GetMapping("/all/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(newsService.findById(id));
  }

  @ApiOperation(value = "Get all news", notes = "This method allows everyone get news")
  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(newsService.getAll());
  }

  @ApiOperation(
      value = "Get all news by admin ID",
      notes = "This method allows everyone get news by admin ID")
  @GetMapping("/all/{adminId}")
  public @ResponseBody ResponseEntity<?> getAllByAdminId(
      @PathVariable("adminId") final Long adminId) {
    return ResponseEntity.ok(newsService.getAllByAdminId(adminId));
  }

  @ApiOperation(value = "Delete news by ID", notes = "This method allows admin delete news by ID")
  @DeleteMapping("/admin/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    newsService.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("News deleted"));
  }
}
