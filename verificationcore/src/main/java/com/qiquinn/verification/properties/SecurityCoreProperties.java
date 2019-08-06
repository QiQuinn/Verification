package com.qiquinn.verification.properties;

import com.qiquinn.verification.properties.login.BrowzeProperties;
import com.qiquinn.verification.properties.login.SessionPropreties;
import com.qiquinn.verification.properties.login.social.SocialProperties;
import com.qiquinn.verification.properties.login.validatecimge.ValidateCodeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
@ConfigurationProperties(prefix = "qiquinn.security",ignoreInvalidFields = true)
public class SecurityCoreProperties
{
    private BrowzeProperties browze = new BrowzeProperties();
    private ValidateCodeProperties validate = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }
    public void setSocial(SocialProperties social) {
        this.social = social;
    }
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
