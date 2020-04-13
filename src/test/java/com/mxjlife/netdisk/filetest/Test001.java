package com.mxjlife.netdisk.filetest;

import com.mxjlife.netdisk.common.cache.GuavaCache;
import com.mxjlife.netdisk.common.constant.Cons;
import com.mxjlife.netdisk.common.util.BuilderUtils;
import com.mxjlife.netdisk.common.util.NdUtils;
import com.mxjlife.netdisk.pojo.nd.FileInfoPO;
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
        File file = new File("D:\\Java\\tool\\Xmanager Enterprise 5\\Fonts\\75dpi\\utrg__24.pcf.Z");
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

    @Test
    public void test006() throws IOException, InterruptedException {
        File file = new File("E:\\Downloads\\BaiduNetdisk_6.8.1.3.exe");
        String s = DigestUtils.md5Hex(FileUtils.openInputStream(file));
        System.out.println(s.equals("6469e1e50910ad159e20ec94a2e3d0e1"));
    }

    @Test
    public void test007() throws IOException, InterruptedException {
        String md5 = "cc97bfb7495be56900c66429a589e914";
        String rootPath = "E:\\tmpfile";

        String filePath = rootPath + "\\f\\123456754676";
        String tmpDirPath = rootPath + File.separator + Cons.DIR_FILE_TMP_DIR + File.separator + md5;

            File resFile = new File(filePath);
            if (!resFile.getParentFile().exists()) {
                resFile.mkdirs();
            }
                resFile.deleteOnExit();
            File tmpDir = new File(tmpDirPath);
        File[] tmpFiles = tmpDir.listFiles();
        Arrays.sort(tmpFiles, Comparator.comparingInt(file -> Integer.parseInt(file.getName())));
        FileOutputStream resFos = new FileOutputStream(resFile, true);
        System.out.println(2);
        for (File tmpFile : tmpFiles) {
            System.out.println(tmpFile.getName());
            // 每次合并重新读取，防止流过大
            // 合并到最终文件
            FileUtils.copyFile(tmpFile, resFos);
            resFos.flush();

        }
        System.out.println(111);
        resFos.close();
        // 校验写入结果的MD5

    }

}
