package xyz.cdbxinhe.cat.backend.util.common;

import org.springframework.lang.Nullable;
import xyz.cdbxinhe.cat.backend.ConstantVariates;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * IO工具类
 * package xyz.cdbxinhe.cat.backend.util.common
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
public class IoUtils {
    /**
     * 递归获取目录下文件列表
     *
     * @param realpath 目标目录
     * @return 文件列表
     */
    public static List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subFiles = realFile.listFiles();
            try {
                assert subFiles != null;
                for (File file : subFiles) {
                    if (file.isDirectory()) {
                        getFiles(file.getAbsolutePath(), files);
                    } else {
                        files.add(file);
                    }
                }
            } catch (Exception ignored) {
                return new ArrayList<>();
            }
        }
        return files;
    }

    /**
     * 按照文件修改时间排序获取目录下所有文件
     *
     * @param path 目标目录
     * @return 排序后文件列表
     */
    public static List<File> getFileSort(String path) {
        List<File> list = getFiles(path, new ArrayList<>());
        if (list.size() > 0) {
            list.sort((file, newFile) -> Long.compare(newFile.lastModified(), file.lastModified()));
        }
        return list;
    }

    @Nullable
    public static InputStream transImageToJpeg(InputStream is) {
        try {
            BufferedImage srcImg = ImageIO.read(is);
            BufferedImage finalImg = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            finalImg.createGraphics().drawImage(srcImg, 0, 0, Color.WHITE, null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(finalImg, ConstantVariates.IMAGE_TYPE, out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException ignored) {
            return null;
        }
    }

    /**
     * 从输入流下载图片
     * @param inputStream 图片输入流
     * @param filenameL 文件名称
     * @param type 文件类型
     * @return 文件url
     * @throws IOException io错误
     */
    public static String downloadFile(InputStream inputStream, String filenameL, String type) throws IOException {
        File sf = new File("");
        //File sf = new File("tmpFiles/");
        if (!sf.exists()) {
            sf.mkdirs();
        }
        String newFileName = sf.getPath() + filenameL + type;
        if (new File(newFileName).exists()) {
            return newFileName;
        }
        OutputStream os = new FileOutputStream(newFileName);
        byte[] bs = new byte[1024];
        int len;
        while ((len = inputStream.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        inputStream.close();
        return newFileName;
    }
    public static boolean deleteFile(String fileUrl) throws IOException {
        File file = new File(fileUrl);
        if (!file.delete()){
            throw new IOException("糟糕!,文件删除失败");
        }
        return true;
    }

}
