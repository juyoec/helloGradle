package com.crassirostris.fromjade.tohandlebars.core;

import java.util.Stack;

import com.crassirostris.fromjade.tohandlebars.core.AbstractHandlebarsLine;
import com.crassirostris.fromjade.tohandlebars.core.ExtendsLine;
import com.crassirostris.fromjade.tohandlebars.core.ScriptLine;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오전 11:09
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor(staticName = "create")
public class HandlebarsTagStack {
    private Stack<AbstractHandlebarsLine> stack = new Stack<AbstractHandlebarsLine>();

    public boolean isBeforeScript(int index) {
        if (stack.size() == 0) {
            return false;
        }
        AbstractHandlebarsLine peek = stack.peek();
        return peek instanceof ScriptLine && peek.getIndent() < index;
    }

    public AbstractHandlebarsLine pop() {
        return stack.pop();
    }

    public void push(AbstractHandlebarsLine lineOfInfo) {
        stack.push(lineOfInfo);
    }

    public boolean isParent(int index) {
        if (stack.size() == 0) {
            return false;
        }
        AbstractHandlebarsLine peek = stack.peek();
        if (peek instanceof ExtendsLine) {
            return false;
        }

        if (peek.isSkipEqualIndent()) {
            return peek.getIndent() > index;
        }
        return peek.getIndent() >= index;
    }

    public boolean hasItem() {
        return stack.size() != 0;
    }
}
