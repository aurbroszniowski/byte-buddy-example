package io.rainfall;

/**
 * @author Aurelien Broszniowski
 */

public class Example {

  public static void main(String[] args) {
    new Example().run();
  }

  private void run() {
    final InstrumentedClass instrumentedClass = new InstrumentedClass();

    for (int i = 0; i < 10; i++) {
      instrumentedClass.doSomething("" + i);
    }
  }

}
