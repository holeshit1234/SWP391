/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DHTV.forgotpassword;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class VerifyError implements Serializable{
     private String emailLengthError;
    private String emailNotExisted;
    private String codeLengthError;
    private String codeNotExisted;
    private String signUpWithGoogleAccount;

    public VerifyError() {
    }

    public VerifyError(String emailLengthError, String emailNotExisted, String codeLengthError, String codeNotExisted, String signUpWithGoogleAccount) {
        this.emailLengthError = emailLengthError;
        this.emailNotExisted = emailNotExisted;
        this.codeLengthError = codeLengthError;
        this.codeNotExisted = codeNotExisted;
        this.signUpWithGoogleAccount = signUpWithGoogleAccount;
    }

    public String getEmailLengthError() {
        return emailLengthError;
    }

    public void setEmailLengthError(String emailLengthError) {
        this.emailLengthError = emailLengthError;
    }

    public String getEmailNotExisted() {
        return emailNotExisted;
    }

    public void setEmailNotExisted(String emailNotExisted) {
        this.emailNotExisted = emailNotExisted;
    }

    public String getCodeLengthError() {
        return codeLengthError;
    }

    public void setCodeLengthError(String codeLengthError) {
        this.codeLengthError = codeLengthError;
    }

    public String getCodeNotExisted() {
        return codeNotExisted;
    }

    public void setCodeNotExisted(String codeNotExisted) {
        this.codeNotExisted = codeNotExisted;
    }

    public String getSignUpWithGoogleAccount() {
        return signUpWithGoogleAccount;
    }

    public void setSignUpWithGoogleAccount(String signUpWithGoogleAccount) {
        this.signUpWithGoogleAccount = signUpWithGoogleAccount;
    }
    
    
}
