package com.agkit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * @author 13
 */
public class MyBlogUtils {

    public static URI getHost(URI uri) {
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    public static String cleanString(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        value = value.toLowerCase();
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("onload", "0nl0ad");
        value = value.replaceAll("xml", "xm1");
        value = value.replaceAll("window", "wind0w");
        value = value.replaceAll("click", "cl1ck");
        value = value.replaceAll("var", "v0r");
        value = value.replaceAll("let", "1et");
        value = value.replaceAll("function", "functi0n");
        value = value.replaceAll("return", "retu1n");
        value = value.replaceAll("$", "");
        value = value.replaceAll("document", "d0cument");
        value = value.replaceAll("const", "c0nst");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "scr1pt");
        value = value.replaceAll("insert", "1nsert");
        value = value.replaceAll("drop", "dr0p");
        value = value.replaceAll("create", "cre0ate");
        value = value.replaceAll("update", "upd0ate");
        value = value.replaceAll("alter", "a1ter");
        value = value.replaceAll("from", "fr0m");
        value = value.replaceAll("where", "wh1re");
        value = value.replaceAll("database", "data1base");
        value = value.replaceAll("table", "tab1e");
        value = value.replaceAll("tb", "tb0");
        return value;
    }

    /**
     * Created by Administrator on 2014/12/6.
     */
    public static class DocConverter {
        // ycl 20180226 modified start
        //private static final int environment = 2;// 环境 1：windows 2:linux
        private static int environment = 1;// 环境 1：windows 2:linux
        static{
            String os = System.getProperty("os.name");
            if(os.toLowerCase().startsWith("win")){
                environment = 1;
              }else
              {
                 environment = 2;
              }
        }
        // ycl 20180226 modified end

        private static Logger logger = LoggerFactory.getLogger(DocConverter.class);


        /**
         * doc转为PDF
         */
       /* private void doc2pdf() throws Exception {
            if (docFile.exists()) {
                if (!pdfFile.exists()) {
                    OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
                    try {
                        connection.connect();
                        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                        converter.convert(docFile, pdfFile);
                        // close the connection
                        connection.disconnect();
                        System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****");
                    } catch (java.net.ConnectException e) {
                        e.printStackTrace();
                        System.out.println("****swf转换器异常，openoffice服务未启动！****");
                        throw e;
                    } catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
                        e.printStackTrace();
                        System.out.println("****swf转换器异常，读取转换文件失败****");
                        throw e;
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                } else {
                    System.out.println("****已经转换为pdf，不需要再进行转化****");
                }
            } else {
                System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
            }
        }*/

        /**
         * pdf转换成 swf
         */
        @SuppressWarnings("unused")
        public static String pdf2swf(String pdfName,String swfName) throws Exception {
            File pdfFile=new File(pdfName);
            File swfFile=new File(swfName);
            Runtime r = Runtime.getRuntime();
            if (!swfFile.exists()) {
                if (pdfFile.exists()) {
                    if (environment == 1) {// windows环境处理测试
                        try {
                            Process p = r.exec("C:\\Program Files (x86)\\SWFTools\\pdf2swf.exe "+pdfName + " -o "+ swfName + " -T 9");
                            logger.info("swf转换成功，文件输出："+swfName);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return "convererror";
                        }
                    } else if (environment == 2) {// linux环境处理
                        try {
                            Process p = r.exec("/opt/swftools/bin/pdf2swf " + pdfName    + " -o " + swfName + " -T 9");
                            logger.info("swf转换成功，文件输出命令："+"/opt/swftools/bin/pdf2swf " + pdfName    + " -o " + swfName + " -T 9");
                            logger.info("swf转换成功，文件输出："+swfName);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return "convererror";
                        }
                    }
                } else {
                   return  "pdfnotexists";//pdf文件不存在
                }
            } else {
                return  "swfexists";//swf已经存在不需要转换
            }
            return "success";//转换成功
        }


     public static void main(String[] args) {
         try {
             //String pfd="E:/a.pdf";
             //String swf="E:/a.swf";
             //pdf2swf(pfd,swf);

             String os = System.getProperty("os.name");
             if(os.toLowerCase().startsWith("win")){
               System.out.println(os + " can't gunzip");
             }
         }catch (Exception e){
            e.printStackTrace();
         }

      }
    }
}
