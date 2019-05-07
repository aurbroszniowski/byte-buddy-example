package io.rainfall;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Executable;

/**
 * @author Aurelien Broszniowski
 */

public class MyClassAdvice {

  public static int cnt = 0;

  @Advice.OnMethodEnter
  static void getAllMethods(@Advice.Origin Executable method, @Advice.AllArguments Object[] args) throws Exception {

    System.out.println("--> Currently Instrumenting " + cnt);
    if (cnt == 5) {
      try {
        System.out.println("---->>> NOW UNINSTRUMENTING");
        ObserverAgent.uninstrument();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    cnt++;

  }
}
