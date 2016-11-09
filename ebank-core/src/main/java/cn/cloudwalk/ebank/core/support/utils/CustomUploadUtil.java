package cn.cloudwalk.ebank.core.support.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liwenhe on 16/11/7.
 */
public class CustomUploadUtil {

    private final static Logger logger = LoggerFactory.getLogger(CustomUploadUtil.class);

    public static void delete(File file) throws IOException {
        FileUtils.forceDelete(file);
    }

    public static void move(File src, File dst) throws IOException {
        FileUtils.moveFile(src, dst);
    }

    public static List<Map<String, Object>> upload(HttpServletRequest request, String tempDir, String prefix) {
        return upload(request, 10240, -1, -1, tempDir, prefix);
    }

    public static List<Map<String, Object>> upload(HttpServletRequest request, int sizeThreshold,
                              long sizeMax, long fileSizeMax, String tempDir, String prefix) {
        // 返回的结果集
        List<Map<String, Object>> results = new ArrayList<>();

        // 上传组件参数配置
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(sizeThreshold, null);
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        fileUpload.setSizeMax(sizeMax);
        fileUpload.setFileSizeMax(fileSizeMax);
        fileUpload.setHeaderEncoding("utf-8");

        // 处理上传
        try {
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            Iterator<FileItem> iterator = fileItems.iterator();
            while (iterator.hasNext()) {
                FileItem item = iterator.next();
                if (!item.isFormField()) {
                    // 生成文件名及判断其目录是否存在，不存在则创建
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    String filename = prefix +
                            sdf.format(new Date()) +
                            item.getName().substring(item.getName().lastIndexOf("."));

                    File temp = new File(tempDir.concat(File.separator).concat(filename));
                    if (!temp.getParentFile().exists()) {
                        temp.getParentFile().mkdirs();
                    }

                    // 生成文件
                    item.write(temp);

                    // 返回相应参数
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("filename", temp.getName());
                    resultMap.put("status", true);
                    resultMap.put("message", "上传文件成功!");
                    results.add(resultMap);
                }
            }
        } catch (FileUploadException e) {
            logger.error(e.getMessage(), e);
            if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", false);
                resultMap.put("message", "单个文件的大小超过限制!");
                results.add(resultMap);
            }
            if (e instanceof FileUploadBase.SizeLimitExceededException) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("status", false);
                resultMap.put("message", "总文件的大小超过限制!");
                results.add(resultMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("status", false);
            resultMap.put("message", "文件上传失败!");
            results.add(resultMap);
        }

        return results;
    }
}
