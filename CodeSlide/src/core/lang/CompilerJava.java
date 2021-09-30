package core.lang;

import javax.tools.*;

public class CompilerJava implements LangCompiler {
	
	public void compile() {
		Tool javac = ToolProvider.getSystemJavaCompiler();
		System.out.println("Compiling!");
		System.err.println(javac.run(System.in, System.out, System.err, "src/slides/code/test/HelloWorld.java"));
	}

}
