package com.agkit.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;


public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 文件上传工具类
     * @author guoshiqi
     */
    public static class FileUploadUtil {
        private File uploadDir = null;//上传文件的文件夹
        private MultipartFile[] mtpfiles = null;//获取的上传文件数组

        public FileUploadUtil(File uploadDir, MultipartFile[] mtpfiles) {
            super();
            this.uploadDir = uploadDir;
            this.mtpfiles = mtpfiles;
        }

         //上传文件
         public void upload(boolean overwrite) throws IOException
         {

             checkDir(uploadDir); //判断文件夹是否存在
             File fileUpload = null;
             for(MultipartFile mtpfile :mtpfiles)
                 {
                   int i =0;
                   //获取原始文件名称
                   String  originalFilename = mtpfile.getOriginalFilename();
                   fileUpload =  new File(uploadDir,originalFilename);//当前目标文件夹

                  // System.out.println("fileUpload:"+fileUpload);
                   if(fileUpload.exists()&&!overwrite)//如果目标文件已经存在且不允许覆盖
                   {
                       while(fileUpload.exists())//文件名+数字 直到不重复
                       {
                           fileUpload = new File(uploadDir,originalFilename+(i++));
                       }

                   }
                   mtpfile.transferTo(fileUpload); //将获取的文件传到指定文件

                 }
         }




         //判断文件夹是否存在  如果不存在 创建
        private void checkDir(File uploadDir)
        {
            if (!uploadDir.exists()) {
                  //创建目录
                  uploadDir.mkdirs();
              }
        }
    }
}
