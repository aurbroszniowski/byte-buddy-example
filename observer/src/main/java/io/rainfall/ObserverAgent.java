package io.rainfall;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * @author Aurelien Broszniowski
 */

public class ObserverAgent {

  static Instrumentation inst;
  static ResettableClassFileTransformer rcft;
  static AgentBuilder ab;

  public static void premain(final String agentArgs,
                             final Instrumentation instrumentation) throws Exception {
    System.out.printf("Starting ObserverAgent\n");

    final AgentBuilder agentBuilder = new AgentBuilder.Default()
        .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
        .type((ElementMatchers.nameContains("io.rainfall.InstrumentedClass")))
        .transform((builder, typeDescription, classLoader, module) -> builder
            .visit(Advice.to(MyClassAdvice.class)
                .on(ElementMatchers.named("doSomething").and(ElementMatchers.takesArguments(String.class))))

        );
    final ResettableClassFileTransformer resettableClassFileTransformer = agentBuilder
        .installOn(instrumentation);

    inst = instrumentation;
    rcft = resettableClassFileTransformer;
    ab = agentBuilder;

  }

  static public void uninstrument() {
    rcft.reset(inst, AgentBuilder.RedefinitionStrategy.RETRANSFORMATION);
    inst.removeTransformer(rcft);
    System.out.println(" UNINSTRUMENTED ");
  }
}
