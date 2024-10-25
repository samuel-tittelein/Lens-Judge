package compiler;

import java.util.List;

/**
 * This enumerates the C compiler types
 * Give the list of arguments to compile file with the C compiler specified
 *
 * @author samuel.tittelein
 */
public enum CCompilerEnum {

    C(List.of("gcc", "-x", "c", "-Wall", "-O2", "-pipe", "-lm", "-o")),
    CPP(List.of("g++", "-x", "c++", "-Wall", "-O2", "-pipe", "-o"));

    final List<String> args;
    CCompilerEnum(List<String> args) {
        this.args = args;
    }

    public List<String> getArgs() {
        return args;
    }
}
