package com.crassirostris.fromjade.tohandlebars;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.crassirostris.fromjade.tohandlebars.core.AbstractHandlebarsLine;
import com.crassirostris.fromjade.tohandlebars.core.HandlebarsTagStack;
import com.crassirostris.fromjade.tohandlebars.core.LineTypeFactory;
import com.crassirostris.fromjade.tohandlebars.core.MatcherHelper;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 7
 * Time: 오전 10:04
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor(staticName = "create")
public class JadeToHandlebars {
    private List<String> readFile(File file) {
        System.out.println("read file : " + file.getAbsoluteFile());
        List<String> lines = Lists.newArrayList();
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        return lines;
    }

    void process(final String filename) throws FileNotFoundException {
        File jadeFile = checkJadeFile(filename);
        List<String> jadeList = readFile(jadeFile);
        List<String> hbsStringList = Lists.newArrayList();
        HandlebarsTagStack stack = HandlebarsTagStack.create();
        LineTypeFactory lineTypeFactory = LineTypeFactory.create();
        try {

            for (String line : jadeList) {
                System.out.println(line);
                if ("".equals(line.trim()) || line.contains("mixin")) {
                    continue;
                }
                int index = MatcherHelper.firstSpellingIndex(line);
                if (stack.isBeforeScript(index)) {  // javascript의 경우우
                    hbsStringList.add(line);
                    continue;
                }
                while (stack.isParent(index)) {
                    hbsStringList.add(stack.pop().getHbsEndString());
                }
                AbstractHandlebarsLine lineOfInfo = lineTypeFactory.createLineOf(line);
                hbsStringList.add(lineOfInfo.getHbsStartString());
                if (lineOfInfo.isNeedStack()) {
                    stack.push(lineOfInfo);
                }

            }
            while (stack.hasItem()) {
                hbsStringList.add(stack.pop().getHbsEndString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        final File touchFile = getResultFile(jadeFile);

        try {
            Files.touch(touchFile);
            Files.write(Joiner.on("\n").join(hbsStringList).getBytes(), touchFile);

        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private File checkJadeFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException(filename);
        }
        return file;
    }


    private File getResultFile(File originalFile) {
        String filepath = originalFile.getAbsolutePath();
        String filename = originalFile.getName();
        filepath = filepath.replace(filename, "");
        String filePureName = filename.substring(0, filename.indexOf("."));
        String fileExt = filename.substring(filename.indexOf(".") + 1, filename.length());
        File file = new File(filepath + filePureName + ".hbs");
        for (int i = 0; file.exists(); i++) {
            file = new File(filepath + filePureName + i + ".hbs");
        }
        return file;
    }
}
