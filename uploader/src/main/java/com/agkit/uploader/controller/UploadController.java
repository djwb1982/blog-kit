package com.agkit.uploader.controller;

import com.agkit.uploader.config.AgkitConfig;
import com.agkit.uploader.utils.FileUtil;
import com.agkit.util.MyBlogUtils;
import com.agkit.util.Result;
import com.agkit.util.ResultGenerator;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@Controller
@RequestMapping("/admin")
public class UploadController {

     private static Logger logger = Logger.getLogger(UploadController.class);
    @PostMapping({"/upload/file"})
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String callbackfile = httpServletRequest.getParameter("callbackfile");//客户端请求参数
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(AgkitConfig.getFileRoot());
        //创建文件
        File destFile = new File(AgkitConfig.getFileRoot() + "/" + AgkitConfig.getPathFix() + "/" + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = ResultGenerator.genSuccessResult();
        result.setData(MyBlogUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/" + AgkitConfig.getPathFix() + "/" + newFileName);
        return result;
    }

    @PostMapping("/blogs/md/uploadfile")
    public void crossUploadFileByEditormd(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(name = "callback") String callback,
                                     @RequestParam(name = "editormd-image-file", required = true) MultipartFile file) throws IOException, URISyntaxException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        File destFile = new File(AgkitConfig.getFileRoot() + "/" + AgkitConfig.getPathFix() + "/" + newFileName);
        String fileUrl = MyBlogUtils.getHost(new URI(request.getRequestURL() + "")) + "/" + AgkitConfig.getPathFix() + "/" + newFileName;
        File fileDirectory = new File(AgkitConfig.getFileRoot() + "/" + AgkitConfig.getPathFix() + "/");
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            //String repStr="{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}";
            String repStr=callback+"?success=1&message=success&fileUrl="+fileUrl;
            response.getWriter().write(repStr);
            response.sendRedirect(repStr);
        } catch (UnsupportedEncodingException e) {
            response.getWriter().write("{\"success\":0}");
        } catch (IOException e) {
            response.getWriter().write("{\"success\":0}");
        }
    }

       /**
     * swf使用上传文件
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/upload/goswf")
    @ResponseBody
    public String goswf(HttpServletRequest request, HttpServletResponse response) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile imgFile = multipartRequest.getFile("fileupload");
            String[] paths = FileUtil.getSavePathByRequest(request);
            JsonObject json = FileUtil.saveImage(imgFile, paths);
            logger.info("++++upload img return:" + json.get("url").getAsString());
            return json.get("url").getAsString();
        } catch (Exception e) {
            logger.error("goswf error", e);
        }
        return null;
    }

}
