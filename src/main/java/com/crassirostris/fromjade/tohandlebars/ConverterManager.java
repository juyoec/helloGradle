package com.crassirostris.fromjade.tohandlebars;

import com.crassirostris.folderscan.RecusiveFolderScanner;
import com.google.common.base.Preconditions;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 8
 * Time: 오전 11:44
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor(staticName = "create")
public class ConverterManager {
    private static final String JADE_FILE_EXT = ".jade";
    public void run(String folderName) {
        Preconditions.checkArgument(!"".equals(folderName));
        RecusiveFolderScanner recusiveFolderScanner = RecusiveFolderScanner.create(folderName);
        JadeToHandlebars jadeToHandlebars = JadeToHandlebars.create();

        try {
            List<File> files = recusiveFolderScanner.scanFolder(JADE_FILE_EXT);

            for (File file: files) {
                jadeToHandlebars.process(file.getAbsolutePath());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
