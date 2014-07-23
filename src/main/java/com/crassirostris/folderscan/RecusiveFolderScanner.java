package com.crassirostris.folderscan;

import static com.google.common.base.Preconditions.*;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Coupang
 * Date: 14. 7. 8
 * Time: 오전 11:01
 * To change this template use File | Settings | File Templates.
 */
@RequiredArgsConstructor(staticName = "create")
public class RecusiveFolderScanner {
    @NonNull private String foldername;
    public List<File> scanFolder(String ext) throws FileNotFoundException {
        checkArgument(!"".equals(foldername));
        File folder = new File(foldername);
        if (!folder.exists()) {
            throw new FileNotFoundException(foldername);
        }

        List<File> fileList = Lists.newArrayList();
        recusiveScan(fileList, folder, ext);

        return fileList;
    }

    private void recusiveScan(List<File> fileList, File folder,String ext) {
        fileList.addAll(selectFile(folder, ext));
        File[] directories = selectDirectories(folder);
        for (File directory: directories) {
            recusiveScan(fileList, directory, ext);
        }
    }

    private File[] selectDirectories(File folder) {
        File[] files = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        return files;
    }

    private List<File> selectFile(File folder,final String ext) {
        File[] files = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory() && pathname.getName().indexOf(ext) > 0;
            }
        });
        ArrayList<File> files1 = Lists.newArrayList(files);
        return files1;
    }
}
