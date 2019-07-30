package com.qiquinn.verification.controller;

import com.qiquinn.verification.mould.UpLoadFile;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String FILE_PATH = "d:/";
    @PostMapping
    public Object upLoadFile(MultipartFile file) throws Exception
    {
        System.out.println("filename="+file.getName());
        System.out.println("fileOrigname="+file.getOriginalFilename());
        System.out.println("fileSize="+file.getSize());

        File localFile = new File(FILE_PATH,new Date().getTime()+".txt");
        file.transferTo(localFile);
        return new UpLoadFile(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void DownLoad(@PathVariable String id, HttpServletResponse response, HttpServletRequest request)
    {
        System.out.println("id: "+id);
        try(InputStream inputStream = new FileInputStream(new File(FILE_PATH,id+".txt"));
            OutputStream outputStream = response.getOutputStream())
        {
            System.out.println("download running");
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
}
