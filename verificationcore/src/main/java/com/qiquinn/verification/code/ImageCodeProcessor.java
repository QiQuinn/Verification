package com.qiquinn.verification.code;

import com.qiquinn.verification.code.api.PhoneCodeSender;
import com.qiquinn.verification.code.impl.AbstractValidateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateProcessor<ImageCode>
{
    @Override
    protected void send(ServletWebRequest request,ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
