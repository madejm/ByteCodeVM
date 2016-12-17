package com.madejm.ByteCodeVM.BusinessLogic;

import java.util.Map;

/**
 * Created by mejdej on 17/12/16.
 */
public class ByteCodeInterpreter {

    //interpretuje polecenia

    /*

    Omawiany wzorzec projektowy można wykorzystać w sytuacjach, gdy zadania, zapisane w pewnym interpretowalnym języku, mogą być reprezentowane jako drzewa składniowe oraz istnieje prosta gramatyka opisująca ten język. Do przykładowych zastosowań tego wzorca należy interpretacja rzymskiego systemu liczbowego, interpretacja wyrażeń zapisanych w odwrotnej notacji polskiej oraz sprawdzanie poprawności pewnych reguł[1]. Stosowany jest także w kompilatorach (np. kompilatorze języka Smalltalk).


     */
}

interface Expression {
    public int interpret(final Map<String, Expression> variables);
}

class Number implements Expression {
    private int number;
    public Number(final int number)       { this.number = number; }
    public int interpret(final Map<String, Expression> variables)  { return number; }
}

class Plus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Plus(final Expression left, final Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public int interpret(final Map<String, Expression> variables) {
        return leftOperand.interpret(variables) + rightOperand.interpret(variables);
    }
}

class Minus implements Expression {
    Expression leftOperand;
    Expression rightOperand;
    public Minus(final Expression left, final Expression right) {
        leftOperand = left;
        rightOperand = right;
    }

    public int interpret(final Map<String, Expression> variables) {
        return leftOperand.interpret(variables) - rightOperand.interpret(variables);
    }
}

class Variable implements Expression {
    private String name;
    public Variable(final String name)       { this.name = name; }
    public int interpret(final Map<String, Expression> variables) {
        if (null == variables.get(name)) return 0; // Either return new Number(0).
        return variables.get(name).interpret(variables);
    }
}