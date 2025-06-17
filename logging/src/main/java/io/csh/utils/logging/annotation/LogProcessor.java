package io.csh.utils.logging.annotation;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * @cshLog 어노테이션을 처리하는 프로세서
 */
@SupportedAnnotationTypes("io.csh.utils.logging.annotation.CshLog")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class LogProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                if (element.getKind().isClass()) {
                    processClass((TypeElement) element);
                }
            }
        }
        return true;
    }

    private void processClass(TypeElement element) {
        String className = element.getQualifiedName().toString();
        String packageName = className.substring(0, className.lastIndexOf('.'));
        String simpleClassName = element.getSimpleName().toString();
        String generatedClassName = simpleClassName + "Log";

        try {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(
                packageName + "." + generatedClassName);

            try (PrintWriter out = new PrintWriter(sourceFile.openWriter())) {
                // 패키지 선언
                out.println("package " + packageName + ";");
                out.println();

                // 임포트 선언
                out.println("import io.csh.utils.logging.Logger;");
                out.println();

                // 클래스 선언
                out.println("public class " + generatedClassName + " {");
                out.println("    private static final Logger log = Logger.of(\"" + simpleClassName + "\");");
                out.println();
                out.println("    public static void trace(String message) {");
                out.println("        log.trace(message);");
                out.println("    }");
                out.println();
                out.println("    public static void trace(String message, Throwable throwable) {");
                out.println("        log.trace(message, throwable);");
                out.println("    }");
                out.println();
                out.println("    public static void debug(String message) {");
                out.println("        log.debug(message);");
                out.println("    }");
                out.println();
                out.println("    public static void debug(String message, Throwable throwable) {");
                out.println("        log.debug(message, throwable);");
                out.println("    }");
                out.println();
                out.println("    public static void info(String message) {");
                out.println("        log.info(message);");
                out.println("    }");
                out.println();
                out.println("    public static void info(String message, Throwable throwable) {");
                out.println("        log.info(message, throwable);");
                out.println("    }");
                out.println();
                out.println("    public static void warn(String message) {");
                out.println("        log.warn(message);");
                out.println("    }");
                out.println();
                out.println("    public static void warn(String message, Throwable throwable) {");
                out.println("        log.warn(message, throwable);");
                out.println("    }");
                out.println();
                out.println("    public static void error(String message) {");
                out.println("        log.error(message);");
                out.println("    }");
                out.println();
                out.println("    public static void error(String message, Throwable throwable) {");
                out.println("        log.error(message, throwable);");
                out.println("    }");
                out.println();
                out.println("    public static void fatal(String message) {");
                out.println("        log.fatal(message);");
                out.println("    }");
                out.println();
                out.println("    public static void fatal(String message, Throwable throwable) {");
                out.println("        log.fatal(message, throwable);");
                out.println("    }");
                out.println("}");
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(
                javax.tools.Diagnostic.Kind.ERROR,
                "Failed to generate log class: " + e.getMessage(),
                element);
        }
    }
} 