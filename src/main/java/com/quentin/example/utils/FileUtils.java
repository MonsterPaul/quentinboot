package com.quentin.example.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 文件操作工具类 拷贝|删除|写入|读取
 *
 * @Auth Created by guoqun.yang
 * @Date Created in 11:22 2017/11/13
 * @Version 1.0
 */
@SuppressWarnings("deprecation")
public class FileUtils {

    /**
     * 拷贝文件
     *
     * @param src        源文件
     * @param dest       目标文件
     * @param bufferSize 每次读取的字节数
     * @throws IOException
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:59
     * @version 1.0
     */
    public static void copyFile(String src, String dest, int bufferSize) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] buffer = new byte[bufferSize];
        int length;

        while ((length = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, length);
        }
        fis.close();
        fos.close();
    }

    /**
     * 删除文件
     *
     * @param src 源文件
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:59
     * @version 1.0
     */
    public static void deleteFile(String src) {
        new File(src).delete();
    }

    /**
     * 删除多个文件
     *
     * @param src 源文件数组
     * @Author: guoqun.yang
     * @Date: 2018/1/18 13:59
     * @version 1.0
     */
    public static void deleteFiles(String... src) {
        for (String s : src) {
            FileUtils.deleteFile(s);
        }
    }

    /**
     * 根据路径删除文件，不能删除子目录下的文件
     *
     * @param dir 目录
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:00
     * @version 1.0
     */
    @Deprecated
    public static void deleteByDir(String dir) throws Exception {
        File d = new File(dir);
        if (!d.isDirectory()) return;
        String[] fileNameArray = d.list();
        FileUtils.deleteFiles(fileNameArray);
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:00
     * @version 1.0
     */
    public static boolean deleteByDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteByDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 递归删除目录下的所有文件及子目录下指定类型的所有文件
     *
     * @param dir            文件目录
     * @param deleteEmptyDir 是否删除空目录
     * @param fileSuffix     文件名后缀，如：jpg,doc，不含“.”,区分JPG和jpg的大小写
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:01
     * @version 1.0
     */
    public static boolean deleteByFileTypes(File dir, boolean deleteEmptyDir, String... fileSuffix) {
        if (dir.isFile()) return false;
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                //如果是目录
                if (children[i].isDirectory()) {
                    if (!deleteByFileTypes(children[i], deleteEmptyDir, fileSuffix)) {
                        return false;
                    }
                }

                //匹配文件后缀
                if (matchFileType(children[i], fileSuffix)) {
                    if (!children[i].delete()) {
                        return false;
                    }
                }
            }
        }
        //删除空文件夹
        if (deleteEmptyDir) {
            if (dir.list().length == 0) {
                if (!dir.delete()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 匹配文件的后缀
     *
     * @param file
     * @param fileSuffix
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:03
     * @version 1.0
     */
    private static boolean matchFileType(File file, String... fileSuffix) {
        String fileName = file.getName();
        for (String suffix : fileSuffix) {
            if (fileName.endsWith("." + suffix)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 将内容写入文件 写文件不要用FileWriter，因为这个无法设置编码，使用系统的编码格式
     *
     * @param content 写入的内容
     * @param dest    写入的文件
     * @param append  是否追加
     * @param newLine 是否换行
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:04
     * @version 1.0
     */
    public static void writeToFile(String content, String dest, boolean append,
                                   boolean newLine) throws IOException {
        writeToFile(content, dest, append, newLine, Charset.forName("UTF-8"));
    }

    /**
     * 将内容写入文件
     *
     * @param content 写入的内容
     * @param dest    写入的文件
     * @param append  是否追加
     * @param newLine 是否换行
     * @param charset 字符编码
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:05
     * @version 1.0
     */
    public static void writeToFile(String content, String dest, boolean append, boolean newLine, Charset charset) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(dest, true), charset);
        writer.write(content + (newLine == true ? System.getProperty("line.separator") : ""));
        writer.close();
    }

    /**
     * 获取文件内容
     *
     * @param src     源文件
     * @param charset
     * @return String[] 文件内容数组，每行占一个数组空间
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:05
     * @version 1.0
     */
    public static String[] readContent(String src, Charset charset) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader br = new BufferedReader(reader);
        String[] array = new String[FileUtils.readLineNumber(src)];
        String line;
        int lineNumber = 0;
        while ((line = br.readLine()) != null) {
            array[lineNumber] = line;
            lineNumber++;
        }
        reader.close();
        br.close();
        return array;
    }

    /**
     * 获取文件内容
     *
     * @param src 源文件
     * @return String 文件内容数组，每行占一个数组空间
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:06
     * @version 1.0
     */
    public static String readStringContent(String src) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader br = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        br.close();
        return sb.toString();
    }

    /**
     * 获取文件行数
     *
     * @param src 源文件
     * @return int 内容行数
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:07
     * @version 1.0
     */
    public static int readLineNumber(String src) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader br = new BufferedReader(reader);
        int lineNumber = 0;
        while (br.readLine() != null) {
            lineNumber++;
        }
        reader.close();
        br.close();
        return lineNumber;
    }

    /**
     * 获取目录下的文件和文件夹列表
     *
     * @param dir 源目录
     * @return LinkedHashMap<String,Boolean> true表示目录
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:07
     * @version 1.0
     */
    public static LinkedHashMap<String, Boolean> readDir(String dir) throws Exception {
        File d = new File(dir);

        if (!d.isDirectory())
            throw new Exception("\"" + dir + "\"" + "不是一个目录");

        String[] array = d.list();
        if (array == null)
            return null;

        LinkedHashMap<String, Boolean> map = new LinkedHashMap<String, Boolean>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], new File(dir + File.separatorChar + array[i])
                    .isDirectory() == true ? true : false);
        }
        return map;
    }

    /**
     * 移动文件,不可以移动文件家
     *
     * @param src  源文件
     * @param dest 目标文件
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:08
     * @version 1.0
     */
    public static void moveFile(String src, String dest) {
        new File(src).renameTo(new File(dest));
    }

    /**
     * 重命名文件||实际上调用本类的moveFile方法
     *
     * @param src  源文件
     * @param dest 目标文件
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:08
     * @version 1.0
     */
    public static void renameFile(String src, String dest) {
        moveFile(src, dest);
    }

    /**
     * 从URL抓取一个文件写到本地，有可能会出现403的情况
     *
     * @param source
     * @param destination
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:09
     * @version 1.0
     */
    public static void copyFileFromURL(URL source, File destination) throws IOException {

        InputStream input = null;
        FileOutputStream output = null;
        byte[] buffer = new byte[1024];

        input = source.openStream();

        if (destination.exists()) {
            if (destination.isDirectory()) {
                throw new IOException("File '" + destination
                        + "' exists but is a directory");
            }
            if (destination.canWrite() == false) {
                throw new IOException("File '" + destination
                        + "' cannot be written to");
            }
        } else {
            File parent = destination.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent
                            + "' could not be created");
                }
            }
        }

        output = new FileOutputStream(destination, true);

        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        output.close();
        input.close();
    }

    /**
     * 复制一个目录及其子目录、文件到另外一个目录
     *
     * @param _src
     * @param _target
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:10
     * @version 1.0
     */
    public static void copyDir(String _src, String _target) throws IOException {

        File src = new File(_src);
        File dest = new File(_target);

        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdirs();
            }
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                // 递归复制
                copyDir(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }

    /**
     * 目录重命名
     *
     * @param sourceDirName
     * @param targetDirName
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:10
     * @version 1.0
     */
    public static void renameDirectory(String sourceDirName, String targetDirName) {
        new File(sourceDirName).renameTo(new File(targetDirName));
    }

    /**
     * 获取目录下所有文件
     *
     * @param realpath
     * @param files
     * @param listSubDir 是否包含子目录
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:11
     * @version 1.0
     */
    public static List<File> getFiles(String realpath, List<File> files, boolean listSubDir) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] _files = realFile.listFiles();
            if (!listSubDir) return ArrayUtils.asList(_files);
            for (File file : _files) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files, true);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * 获取目录下所有文件(包括子文件夹)
     * 递归调用
     *
     * @param realpath
     * @param files
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:11
     * @version 1.0
     */
    public static List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * 获取目录下所有文件(按修改时间排序)
     *
     * @param path
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:11
     * @version 1.0
     */
    public static List<File> getFileSort(String path) {
        List<File> list = getFiles(path, new ArrayList<File>());
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }

        return list;
    }

    /**
     * 从网络下载文件，不会出现403的情况
     *
     * @param url
     * @param filePathName
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:13
     * @version 1.0
     */
    public static void download(String url, String filePathName) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);

            // 伪装成google的爬虫,一般服务器会进行请求的校验，如果不是http请求会进行拦截
            httpget.setHeader("User-Agent", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
            HttpResponse response = httpclient.execute(httpget);

            File storeFile = new File(filePathName);
            FileOutputStream output = new FileOutputStream(storeFile);

            // 得到网络资源的字节数组,并写入文件
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    byte b[] = new byte[1024];
                    int j = 0;
                    while ((j = instream.read(b)) != -1) {
                        output.write(b, 0, j);
                    }
                    output.flush();
                    output.close();
                } catch (IOException ex) {
                    throw ex;
                } catch (RuntimeException ex) {
                    httpget.abort();
                    throw ex;
                } finally {
                    try {
                        instream.close();
                        httpget.releaseConnection();
                    } catch (Exception ignore) {
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    /**
     * 获取文件后缀名
     *
     * @param file
     * @Author: guoqun.yang
     * @Date: 2018/1/18 14:13
     * @version 1.0
     */
    public static String getFileSuffix(File file) {
        if (!file.isFile() || !file.exists())
            return null;

        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos == -1) {
            return null;
        }
        return name.substring(pos + 1, name.length());
    }


}
