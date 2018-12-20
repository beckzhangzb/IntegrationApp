package com.wallaw.webappgen.util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wallaw.webappgen.GenConfig;
import com.wallaw.webappgen.GeneratorProperties;

/**
 * @author badqiu
 * @email badqiu(a)gmail.com
 */
public class FileHelper {

    /**
     * 删除生成文件存放目录
     *
     * @throws IOException
     */
    public static void deleteOutRootDir() throws IOException {
        if (StringHelper.isBlank(GenConfig.OUT_ROOT_DIR_STR))
            throw new IllegalStateException("generator.xml 'outRoot' property must be not null.");
        GLogger.println("[delete dir]    " + GenConfig.OUT_ROOT_DIR_STR);
        FileHelper.deleteDirectory(new File(GenConfig.OUT_ROOT_DIR_STR));
    }

    /**
     * 得到相对路径
     */
    public static String getRelativePath(File baseDir, File file) {
        if (baseDir.equals(file))
            return "";
        if (baseDir.getParentFile() == null)
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
    }

    /**
     * 获取模板文件列表
     * @param templateRootDir
     * @return
     */
    public static List<File> getTemplateFiles(File templateRootDir) {
        List<File> arrayList = null;
        try {
            arrayList = fileList(templateRootDir, new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.getName().toLowerCase().endsWith(".include")) return false;//如果是.include后缀，不处理
                    if (pathname.isHidden()) return false; //如果是隐藏文件 不处理
                    if (isIgnoreFile(pathname)) return false; //如果是指定忽略目录 不处理
                    return true;
                }
            });
            Collections.sort(arrayList, new Comparator<File>() {
                public int compare(File o1, File o2) {
                    return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            GLogger.error("获取模板文件列表失败！！");
        }
        return arrayList;
    }

    public static InputStream getInputStream(String file) throws FileNotFoundException {
        InputStream inputStream = null;
        if (file.startsWith("classpath:")) {
            inputStream = FileHelper.class.getClassLoader().getResourceAsStream(file.substring("classpath:".length()));
        } else {
            inputStream = new FileInputStream(file);
        }
        return inputStream;
    }

    /**
     * add by xiaoming@wallaw.com.cn
     * 利用递归算法 搜索文件目录
     *
     * @param file      根目录
     * @param collector 集合容器
     * @param filter    过滤条件
     * @throws IOException
     */
    private static void sanAllNotIgnoreFile(File file, List<File> collector, FileFilter filter) throws IOException {
        if (file.isFile()) collector.add(file);
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles(filter);
            for (int i = 0; i < subFiles.length; i++) {
                sanAllNotIgnoreFile(subFiles[i], collector, filter);
            }
        }
    }

    /**
     * add by xiaoming@wallaw.com.cn
     * 如果不过滤，则表示返回所有
     * 根据过滤规则 返回指定目录下的所有文件列表
     *
     * @param rootDir
     * @return
     * @throws IOException
     */
    public static List<File> fileList(File rootDir, FileFilter filter) throws IOException {
        List<File> result = new ArrayList<File>();
        sanAllNotIgnoreFile(rootDir, result, filter);
        return result;
    }

    /**
     * 根据文件名创建文件目录
     *
     * @param file
     * @return
     */
    public static File parentMkdir(String file) {
        if (file == null) throw new IllegalArgumentException("file must be not null");
        File result = new File(file);
        if (result.getParentFile() != null) {
            result.getParentFile().mkdirs();
        }
        return result;
    }

    //创建文件目录
    public static void parentMkdir(File file) {
        if (file == null) throw new IllegalArgumentException("file must be not null");
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        return;
    }

    public static File getFileByClassLoader(String resourceName) throws IOException {
        Enumeration<URL> urls = GeneratorProperties.class.getClassLoader().getResources(resourceName);
        while (urls.hasMoreElements()) {
            return new File(urls.nextElement().getFile());
        }
        throw new FileNotFoundException(resourceName);
    }

    /**
     * 判断是否是排除的目录
     *
     * @param file
     * @return
     */
    private static boolean isIgnoreFile(File file) {
        for (int i = 0; i < GenConfig.IGNORE_LIST.size(); i++) {
            if (file.getName().equals(GenConfig.IGNORE_LIST.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static Set binaryExtentionsList = new HashSet();

    static {
        loadBinaryExtentionsList("binary_filelist.txt", true);
    }

    public static void loadBinaryExtentionsList(String resourceName, boolean ignoreException) {
        try {
            InputStream input = GeneratorProperties.class.getClassLoader().getResourceAsStream(resourceName);
            binaryExtentionsList.addAll(IOHelper.readLines(new InputStreamReader(input)));
            input.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查文件是否是二进制文件
     */
    public static boolean isBinaryFile(File file) {
        if (file.isDirectory()) return false;
        return isBinaryFile(file.getName());
    }

    public static boolean isBinaryFile(String filename) {
        if (StringHelper.isBlank(getExtension(filename))) return false;
        return binaryExtentionsList.contains(getExtension(filename).toLowerCase());
    }

    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = filename.indexOf(".");
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    //-----------------------------------------------------------------------

    /**
     * Deletes a directory recursively.
     *
     * @param directory directory to delete
     * @throws IOException in case deletion is unsuccessful
     */
    public static void deleteDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        cleanDirectory(directory);
        if (!directory.delete()) {
            String message =
                    "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }

    /**
     * Deletes a file, never throwing an exception. If file is a directory, delete it and all sub-directories.
     * <p/>
     * The difference between File.delete() and this method are:
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>No exceptions are thrown when a file or directory cannot be deleted.</li>
     * </ul>
     *
     * @param file file or directory to delete, can be <code>null</code>
     * @return <code>true</code> if the file or directory was deleted, otherwise
     *         <code>false</code>
     * @since Commons IO 1.4
     */
    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception e) {
        }

        try {
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws IOException in case cleaning is unsuccessful
     */
    public static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent) {
                    throw new FileNotFoundException("File does not exist: " + file);
                }
                String message =
                        "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }
}
