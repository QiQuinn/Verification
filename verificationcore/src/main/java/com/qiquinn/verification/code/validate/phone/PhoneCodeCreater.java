package com.qiquinn.verification.code.validate.phone;

import com.qiquinn.verification.code.validate.entity.BaseCode;
import com.qiquinn.verification.code.api.ValiadateCodeCreater;
import com.qiquinn.verification.code.validate.entity.PhoneCode;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import com.qiquinn.verification.properties.login.validatecimge.PhoneCodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
@Component("phoneCodeCreater")
public class PhoneCodeCreater implements ValiadateCodeCreater {

    @Autowired
    private SecurityCoreProperties securityCoreProperties;

    @Override
    public BaseCode createCode(HttpServletRequest request)
    {
        PhoneCodeProperties phone = securityCoreProperties.getValidate().getPhonecode();
        int count = securityCoreProperties.getValidate().getPhonecode().getCount();
        int loseTime = securityCoreProperties.getValidate().getPhonecode().getLoseTime();
        String code = getRamdomNumber(count);
        BaseCode phonCode = new PhoneCode(code,loseTime);
        return phonCode;
    }
    /**
      * @Author:QiQuinn
      * @Desicription: 生成短信验证码
      * @Date:Created in 2019/7/31 10:56
      * @param count
      *@return int[] 短信验证码数组
      * @Modified By:
      */
    private String getRamdomNumber(Integer count)
    {
        StringBuffer stringBuffer = new StringBuffer("");
        Random random = new Random();
        for(int i=0;i<count;i++)
        {
            stringBuffer.append(random.nextInt(9));
        }
        return stringBuffer.toString();
    }


    public SecurityCoreProperties getSecurityCoreProperties() {
        return securityCoreProperties;
    }

    public void setSecurityCoreProperties(SecurityCoreProperties securityCoreProperties) {
        this.securityCoreProperties = securityCoreProperties;
    }
}
