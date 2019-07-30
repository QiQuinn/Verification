package com.qiquinn.verification.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
@ConfigurationProperties(prefix = "qiquinn.security")
public class SecurityCoreProperties
{
    private BrowzeProperties browze = new BrowzeProperties();
    private ValidateCodeProperties validate = new ValidateCodeProperties();

    public ValidateCodeProperties getValidate() {
        return validate;
    }

    public void setValidate(ValidateCodeProperties validate) {
        this.validate = validate;
    }

    public BrowzeProperties getBrowze() {
        return browze;
    }

    public void setBrowze(BrowzeProperties browze) {
        this.browze = browze;
    }
}
