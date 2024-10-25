# Lens Judge


## Membres du groupe

- Fierquin Mattéo --> Chef de projet 
- Tittelein Samuel --> Scrum Master
- Mortreux Théo
- Lechêne Romain

## Tableau récapitulatif des fonctionnalités

| Fonctionnalité                                  | Patron de conception |
| :--------------                                 | --------------------:|
| Représentation d'un cas de test                 | /                    |
| Représentation d'un problème                    | /                    |
| Configuration d'un problème                     | Fabrique             |
| Représentation d'un processus                   | /                    |
| Limitation du temps d'exécution d'un processus  | Décorateur           |
| Limitaion de la mémoire d'un processus          | Décorateur           |
| Compilation d'un programme C                    | Stratégie & Proxy    |
| Compilation d'un programme C++                  | Stratégie & Proxy    |
| Compilation d'un programme Java                 | Stratégie & Proxy    |
| Compilation d'un programme Python               | Stratégie & Proxy    |
| Exécution d'un programme Python                 | Stratégie            |
| Exécution d'un programme C compilé              | Stratégie            |
| Exécution d'un programme C++ compilé            | Stratégie            |
| Exécution d'un programme Java compilé           | Stratégie            |
| Vérification stricte de la solution             | Stratégie            |
| Vérification avec tolérance sur les réels       | Stratégie            |
| Vérification avec tolérance sur la casse        | Stratégie            |
| Vérification avec tolérance sur les espaces     | Stratégie            |
| Vérification avec tolérance sur l'ordre         | Stratégie            |
| Vérification d'une solution parmi plusieurs     | /                    |
| Vérification déléguée à un programme externe    | Adaptateur           |
| Configuration de l'exécution sur un cas de test | /                    |
| Programme principal du juge automatique         | /                    |

## Diagramme de classes

```plantuml
hide empty members




class LensJudge {


   + {static} main(args: String[]): void


}




interface IJudge {


+ compile(String) : String
+ execute(Problem) : String
+ verifie(Problem) : boolean


}


class Judge {
- compiler : ICompiler
- executer : IExecuter
- verifier : IVerifier
--
+ compile(String) : String
+ execute(Problem) : String
+ verifie(Problem) : boolean


}


LensJudge - IJudge : <<utilise>>
Judge --|> IJudge


interface ICompiler {
+compile(String) : String
}


class CompilerProxy {
+compile(String) : String
}


class CCompiler {
+compile(String) : String
}


class CPPCompiler {
+compile(String) : String
}


class JavaCompiler {
+compile(String) : String
}


class PythonCompiler {
+compile(String) : String
}


ICompiler --o Judge


CompilerProxy ..|> ICompiler


PythonCompiler ..|> ICompiler


CCompiler ..|> ICompiler


CPPCompiler ..|> ICompiler


JavaCompiler ..|> ICompiler


ICompiler --* CompilerProxy






interface IVerifier {
+ {static} check(Problem) : boolean
}


class RealToleranceVerifier {
+ {static} check(Problem) : boolean
}


class StrictVerifier {
+ {static} check(Problem) : boolean
}


class OrderVerifier {
+ {static} check(Problem) : boolean
}


class CasseInsensitiveVerifier {
+ {static} check(Problem) : boolean
}


IVerifier --o Judge


RealToleranceVerifier ..|> IVerifier


StrictVerifier ..|> IVerifier


OrderVerifier ..|> IVerifier


CasseInsensitiveVerifier ..|> IVerifier


class ProblemBuilder {
-timeLimit : long
-memoryLimit : long
-testcases : Array<TestCase>
-verifieMod : IVerifier
-output : String
--
- ProblemBuilder()
+ {static} newInstance() : ProblemBuilder
+ build() : Problem
+ withLimitTime(long): ProblemBuilder
# getLimitTime(): long
+ withLimitMemory(long): ProblemBuilder
# getLimitMemory(): long
+ withTestCases(Array<TestCase>): ProblemBuilder
# getTestCases() : Array<TestCase>
+ withVerifier(IVerifier): ProblemBuilder
# getVerifier() : IVerifier
+ withOutput(String): ProblemBuilder
# getOutput() : String
}


class Problem {
-timeLimit : long
-memoryLimit : long
-testcases : Array<TestCase>
-verifieMod : IVerifier
-output : String
--
+Problem(ProblemBuilder)
}


ProblemBuilder --> Problem : <<construit>>


class TestCase{
-input : String
-output : String
--
TestCase()
setInput(String) : void
setOutput(String) : void
}


Problem *-- TestCase


Problem --> IVerifier : <<utilise>>


Judge --> ProblemBuilder : <<utilise>>


class ExternalVerifierAdaptater implements IVerifier {
}


interface IExecuter {
+execute(String) : String
}


class ExecuterProxy {
+execute(String) : String
}


class CExecuter {
+execute(String) : String
}


class CPPExecuter {
+execute(String) : String
}


class JavaExecuter {
+execute(String) : String
}


class PythonExecuter {
+execute(String) : String
}


IExecuter --o Judge
ExecuterProxy ..|> IExecuter


PythonExecuter ..|> IExecuter


CExecuter ..|> IExecuter


CPPExecuter ..|> IExecuter


JavaExecuter ..|> IExecuter


IExecuter --* ExecuterProxy


```
