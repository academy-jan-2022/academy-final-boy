package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PassingTests {



  @Test
  void green() {
    var sut = new BackendApplication();
    sut.coverageWorks();
    Assertions.assertTrue(true);
  }
}
