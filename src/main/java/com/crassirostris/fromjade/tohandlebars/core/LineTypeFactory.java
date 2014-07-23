package com.crassirostris.fromjade.tohandlebars.core;

import com.google.common.base.CharMatcher;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오전 11:44
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor(staticName = "create")
public class LineTypeFactory {
    public AbstractHandlebarsLine createLineOf(String jadeString) {
        int indent = MatcherHelper.firstSpellingIndex(jadeString);
        String trimedJadeString = CharMatcher.WHITESPACE.trimFrom(jadeString);
        AbstractHandlebarsLine lineInfo = tagSelecting(trimedJadeString);
        lineInfo.setIndent(indent);

        return lineInfo;
    }

    private AbstractHandlebarsLine tagSelecting(String jadeString) {
        if (MatcherHelper.sharpStart(jadeString) || MatcherHelper.commaStart(jadeString)) {
            HtmlTagLine line = HtmlTagLine.create("div");
            line.generateAttribute(jadeString);
            return line;
        } else if (MatcherHelper.scriptStart(jadeString)) {
            ScriptLine line = ScriptLine.create();
            return line;
        } else if (MatcherHelper.includeStart(jadeString)) {
            IncludeLine line = IncludeLine.create(jadeString.replace("include", "").trim());
            return line;
        } else if (MatcherHelper.blockStart(jadeString)) {
            BlockLine line = BlockLine.create(jadeString.replace("block", "").trim());
            return line;
        } else if (MatcherHelper.extendsStart(jadeString)) {
            ExtendsLine line = ExtendsLine.create(jadeString.replace("extends", "").trim());
            return line;
        } else if (MatcherHelper.pipeStart(jadeString)) {
            PipeLine line = PipeLine.create(jadeString.replace("|", "").trim());
            return line;
        } else if (MatcherHelper.plusStart(jadeString)) {
            PipeLine line = PipeLine.create(jadeString.trim());
            return line;
        } else if (MatcherHelper.doctypeStart(jadeString)) {
            DoctypeLine line = DoctypeLine.create();
            return line;
        } else if (MatcherHelper.contextStart(jadeString)) {
            String context = MatcherHelper.firstWord(jadeString);

            ContextLine line = ContextLine.create(context);
            line.selectVariable(jadeString);
            return line;
        }
        // 기타 html 태그들 처리
        String tag = MatcherHelper.firstWord(jadeString);
        HtmlTagLine line = HtmlTagLine.create(tag);
        line.generateAttribute(jadeString);
        return line;
    }

}
