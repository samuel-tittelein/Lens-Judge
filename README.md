# Lens Judge

## Table of contents

1. [Group members](#group-members)
2. [Functionalities recap](#functionalities-recap)
3. [UML Diagram](#uml-diagram)
4. [Use case](#use-case)

## Group members

- Fierquin Mattéo --> Chef de projet 
- Tittelein Samuel --> Scrum Master
- Mortreux Théo
- Lechêne Romain

## Functionalities recap

| Functionality                                   | Design pattern       |            Author|
| :--------------                                 | --------------------:| ----------------:|
| Représentation d'un cas de test                 | /                    | Théo Mortreux    |
| Représentation d'un problème                    | Builder              | Romain Lechêne   |
| Configuration d'un problème                     | Fabrique             | Romain Lechêne   |
| Représentation d'un processus                   | Adaptateur           | Mattéo Fierquin  |
| Limitation du temps d'exécution d'un processus  | Décorateur           | Mattéo Fierquin  |
| Limitaion de la mémoire d'un processus          | Décorateur           | /                |
| Compilation d'un programme C                    | Stratégie & Proxy    | Samuel Tittelein |
| Compilation d'un programme C++                  | Stratégie & Proxy    | Samuel Tittelein |
| Compilation d'un programme Java                 | Stratégie & Proxy    | Samuel Tittelein |
| Compilation d'un programme Python               | Stratégie & Proxy    | Samuel Tittelein |
| Exécution d'un programme Python                 | Stratégie            | Mattéo Fierquin  |
| Exécution d'un programme C compilé              | Stratégie            | Mattéo Fierquin  |
| Exécution d'un programme C++ compilé            | Stratégie            | Mattéo Fierquin  |
| Exécution d'un programme Java compilé           | Stratégie            | Mattéo Fierquin  |
| Vérification stricte de la solution             | Stratégie            | Théo Mortreux    |
| Vérification avec tolérance sur les réels       | Stratégie            | Théo Mortreux    |
| Vérification avec tolérance sur la casse        | Stratégie            | Mattéo Fierquin  |
| Vérification avec tolérance sur les espaces     | Stratégie            | Mattéo Fierquin  |
| Vérification avec tolérance sur l'ordre         | Stratégie            | Mattéo & Théo    |
| Vérification d'une solution parmi plusieurs     | /                    | /                |
| Vérification déléguée à un programme externe    | Adaptateur           | /                |
| Configuration de l'exécution sur un cas de test | /                    | Mattéo & Romain  |
| Programme principal du juge automatique         | /                    | Samuel & Romain  |

## UML Diagram

```plantuml

class AbstractCompiler {
  + AbstractCompiler(): 
  + isCompatible(File): boolean
  + binName(File): String
  + getExtension(File): String
  + compile(File): File
}
class CCompiler {
  + CCompiler(CCompilerEnum): 
  + compile(File): File
}
enum CCompilerEnum << enumeration >> {
  - CCompilerEnum(List<String>): 
  ~ args: List<String>
  + valueOf(String): CCompilerEnum
  + values(): CCompilerEnum[]
   args: List<String>
}
class CExecuter {
  + CExecuter(): 
  + execute(File, File, long): void
}
class CaseInsensitiveVerifier {
  + CaseInsensitiveVerifier(IVerifier): 
  + verify(File, File): boolean
  - createLowerCaseCopy(File): File
}
class ExecuterProxy {
  + ExecuterProxy(): 
  + execute(File, File, long): void
}
interface ICompiler << interface >> {
  + isCompatible(File): boolean
  + binName(File): String
  + compile(File): File
}
interface IExecuter << interface >> {
  + execute(File, File, long): void
}
interface IProcess << interface >> {
  + stopProcess(): void
  + waitForCompletion(): void
  + startProcess(List<String>): void
  + writeStandardOutputToFile(String): void
  + provideInput(InputStream): void
   errorOutput: String
   processFinished: boolean
   standardOutput: String
}
interface IVerifier << interface >> {
  + verify(File, File): boolean
}
class InputReader {
  - InputReader(): 
  + readVerifier(): IVerifier
  + readPrecision(): double
  + readLine(): String
  + readTimeLimit(): long
  + readInt(int): int
}
class JavaCompiler {
  + JavaCompiler(): 
  + compile(File): File
}
class JavaExecuter {
  + JavaExecuter(): 
  + execute(File, File, long): void
}
class Main {
  + Main(): 
  - getTestCases(File, String[]): Problem?
  - getCompiledFile(File): File
  + main(String[]): void
  - verifyDirectoryTestCase(File, String[]): void
  + printFile(File): void
  + verifyOneTestCase(File, String[]): void
}
class OrderTolerantVerifier {
  + OrderTolerantVerifier(IVerifier): 
  + verify(File, File): boolean
  - createSortedCopy(File): File
}
class PrecisionVerifier {
  + PrecisionVerifier(double): 
  + verify(File, File): boolean
}
class Problem {
  + Problem(ProblemBuilder): 
  - testCasesList: List<TestCase>
  - verifier: IVerifier
  + forEach(Consumer<TestCase>): void
  + spliterator(): Spliterator<TestCase>
  + iterator(): Iterator<TestCase>
   verifier: IVerifier
   testCasesList: List<TestCase>
}
class ProblemBuilder {
  + ProblemBuilder(): 
  - testCasesList: List<TestCase>
  - verifier: IVerifier
  + withTestCasesList(List<TestCase>): ProblemBuilder
  + build(): Problem
  + newInstance(): ProblemBuilder
  + withVerifier(IVerifier): ProblemBuilder
   verifier: IVerifier
   testCasesList: List<TestCase>
}
class ProcessController {
  + ProcessController(): 
  - standardOutput: StringBuilder
  - errorOutput: StringBuilder
  + writeStandardOutputToFile(String): void
  - captureOutput(): void
  + waitForCompletion(): void
  + provideInput(InputStream): void
  + stopProcess(): void
  + startProcess(List<String>): void
   errorOutput: String
   processFinished: boolean
   standardOutput: String
}
class PythonCompiler {
  + PythonCompiler(): 
  + compile(File): File
}
class PythonExecuter {
  + PythonExecuter(): 
  + execute(File, File, long): void
}
class Runner {
  + Runner(RunnerBuilder): 
  ~ outputFile: File
  ~ compiledFile: File
  ~ testCase: TestCase
  + runFile(): void
  + verifyProgram(): boolean
  + compileFile(): void
   compiledFile: File
   testCase: TestCase
   outputFile: File
}
class RunnerBuilder {
  + RunnerBuilder(): 
  ~ testCase: TestCase
  + newInstance(): RunnerBuilder
  + build(): Runner
  + withSourceFile(File): RunnerBuilder
  + withExpectedOutputFile(File): RunnerBuilder
  + withVerifier(IVerifier): RunnerBuilder
  + withInputFile(File): RunnerBuilder
  + withTimeInMs(long): RunnerBuilder
   testCase: TestCase
}
class SpaceInsensitiveVerifier {
  + SpaceInsensitiveVerifier(IVerifier): 
  + verify(File, File): boolean
  - createSpaceRemovedCopy(File): File
}
class TestCase {
  + TestCase(File, File, File, long, IVerifier): 
  - outputFile: File
  - inputProgramFile: File
  - inputFile: File
  - timeInMs: long
  - verifier: IVerifier
   verifier: IVerifier
   inputProgramFile: File
   timeInMs: long
   inputFile: File
   outputFile: File
}
class TimedProcessController {
  + TimedProcessController(IProcess, long): 
  + stopProcess(): void
  + provideInput(InputStream): void
  + writeStandardOutputToFile(String): void
  + startProcess(List<String>): void
  + waitForCompletion(): void
   errorOutput: String
   processFinished: boolean
   standardOutput: String
}
class Verifier {
  + Verifier(): 
  + verify(File, File): boolean
}

AbstractCompiler          -[#008200,dashed]-^  ICompiler                
CCompiler                 -[#000082,plain]-^  AbstractCompiler         
CCompiler                 -[#008200,dashed]-^  ICompiler                
CExecuter                 -[#008200,dashed]-^  IExecuter                
CaseInsensitiveVerifier   -[#008200,dashed]-^  IVerifier                
ExecuterProxy             -[#008200,dashed]-^  IExecuter                
JavaCompiler              -[#000082,plain]-^  AbstractCompiler         
JavaCompiler              -[#008200,dashed]-^  ICompiler                
JavaExecuter              -[#008200,dashed]-^  IExecuter                
OrderTolerantVerifier     -[#008200,dashed]-^  IVerifier                
PrecisionVerifier         -[#008200,dashed]-^  IVerifier                
ProcessController         -[#008200,dashed]-^  IProcess                 
PythonCompiler            -[#000082,plain]-^  AbstractCompiler         
PythonCompiler            -[#008200,dashed]-^  ICompiler                
PythonExecuter            -[#008200,dashed]-^  IExecuter                
SpaceInsensitiveVerifier  -[#008200,dashed]-^  IVerifier                
TimedProcessController    -[#008200,dashed]-^  IProcess                 
Verifier                  -[#008200,dashed]-^  IVerifier
Main --* Runner
ProblemBuilder --> Problem
RunnerBuilder --> Runner
CCompilerEnum --^ CCompiler
TestCase --> Problem
InputReader --> Main : <<utilise>>
```

## Use case

In order to create the .jar file, cd to the project root, and type ```gradle clean build``` into the console. The .jar file created will be located in the *app/builds/libs* directory.

The expected output should be in a *.ans* file, while the inputs should be in a *.in* file.

### Unique test case

If you want to test one specific case, you can launch the .jar file by typing ```java -jar app.jar relative/path/to/file_to_test path/to/parameters/file path/to/expected/output/file``` in the console.

### Directory of test cases

If you want to test multiple cases using a directory, you can launch the .jar file by typing ```java -jar app.jar relative/path/to/file_to_test path/to/ins&outs/file``` in the console.
