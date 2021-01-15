package by.netcracker.enterprisedb.service.mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

/** @author Andrey Egorov */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseServiceTest<T, R> {

  protected T input;
  protected R inputDTO;
  protected List<T> list;
  protected List<R> listDTO;
  protected ObjectMapper mapper = new ObjectMapper();

  public File getAbsoluteFile(final String fileName) {
    return new File("src/test/resources/json/" + fileName + ".json").getAbsoluteFile();
  }
}
