package by.netcracker.enterprisedb.controller;

import by.netcracker.enterprisedb.dto.model.CareerDTO;
import by.netcracker.enterprisedb.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/career")
public class CareerController {

  private final CareerService careerService;

  @Autowired
  public CareerController(final CareerService careerService) {
    this.careerService = careerService;
  }

  @PostMapping("/add")
  public @ResponseBody ResponseEntity<?> add(@Valid @RequestBody final CareerDTO careerDTO) {
    return ResponseEntity.ok(careerService.save(careerDTO));
  }

  @PutMapping("/update")
  public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody final CareerDTO careerDTO) {
    return ResponseEntity.ok(careerService.update(careerDTO));
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<?> findById(@PathVariable("id") final Long id) {
    return ResponseEntity.ok(careerService.findById(id));
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<?> getAll() {
    return ResponseEntity.ok(careerService.getAll());
  }

  @GetMapping("/all/{employeeId}")
  public @ResponseBody ResponseEntity<?> getAllByEmployeeID(@PathVariable("employeeId") final Long employeeId) {
    return ResponseEntity.ok(careerService.getAllByEmployeeId(employeeId));
  }

  @DeleteMapping("/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteById(@PathVariable("id") final Long id) {
    careerService.deleteById(id);
    return ResponseEntity.ok("Career deleted");
  }
}
