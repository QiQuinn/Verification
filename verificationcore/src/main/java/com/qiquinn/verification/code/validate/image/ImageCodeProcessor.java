package com.qiquinn.verification.code.validate.image;

import com.qiquinn.verification.code.impl.AbstractValidateProcessor;
import com.qiquinn.verification.code.validate.entity.ImageCode;
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
