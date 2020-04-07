package com.mxjlife.netdisk.filetest;

import com.mxjlife.netdisk.common.util.NdUtils;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tika.Tika;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * description:
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/3 10:51
 */
public class Test001 {

    @Test
    public void test001(){
        String extension = FilenameUtils.getExtension("test.tests.mp3");
        System.out.println(extension);
        String baseName = FilenameUtils.getBaseName("test.tests.mp3");
        System.out.println(baseName);


    }
    @Test
    public void test002(){
        File file = new File("E:\\tmp\\1");
        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f.getName());
        }
        Arrays.sort(files, Comparator.comparing(File::getName).reversed());
        for (File f : files) {
            System.out.println(f.getName());
        }
    }

    @Test
    public void test003() throws IOException {
        File file = new File("E:\\Downloads");
        File[] files = file.listFiles();
        Tika tika = new Tika();
        for (File f : files) {
            String res = tika.detect(f);
            System.out.println(f.getName() + "\t\t\t" + NdUtils.getFileType(res));
        }
    }

    @Test
    public void test004() throws IOException {
        // 87ca5a8c781f78264f2bcafb318a36bc
        File file = new File("E:\\Data\\tmp\\ldar.mp4");
        InputStream fis = FileUtils.openInputStream(file);
        System.out.println(FileUtils.sizeOf(file));
//        String md5 = DigestUtils.md5Hex(fis);
//        System.out.println(md5);
        String uuid = NdUtils.uuid();
        File f1 = new File("E:\\Data\\tmp\\" + uuid);

        FileUtils.copyToFile(fis, f1);

        FileUtils.copyToFile(fis, new File("E:\\Data\\tmp\\123456"));
//        FileUtils.copyToFile();
        FileInputStream fis2 = FileUtils.openInputStream(new File("E:\\Data\\tmp\\" + uuid));
        String md52 = DigestUtils.md5Hex(fis2);
        System.out.println(md52);

    }
    @Test
    public void test005() throws IOException, InterruptedException {
        File file = new File("E:\\Data\\tmp\\tmp.7z");
        FileInputStream fis = FileUtils.openInputStream(file);
        File file1 = new File("E:\\Data\\tmp\\tmp.7z.1");
        FileOutputStream stream = new FileOutputStream(file1);
        FileUtils.copyFile(file, stream);
        Thread.sleep(10000);
//        DigestUtils.md5Hex(fis);
    }

}
