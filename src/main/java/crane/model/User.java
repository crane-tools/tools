package crane.model;

import java.io.Serializable;

/**
 * Created by crane on 16/9/23.
 * private字段需要和field值一致
 */
public class User implements Serializable, EsBaseModel {
    private String id;
    private String userName;
    private String nickName;
    private String realName;
    private String password;
    private String email;
    private Long emailStatus;
    private String mobile;
    private Long mobileStatus;
    private Long expValue;
    private Long gender;
    private String role;
    private String signature;
    private String qq;
    private String weChat;
    private String weiBo;
    private String faceBook;
    private String linkedin;
    private Long birthday;
    private String company;
    private String occupation;
    private String address;
    private String zipCode;
    private String site;
    private String graduateSchool;
    private String education;
    private String avatar;
    private String idcardType;
    private String idcardNumber;
    private String idcardPath;
    private String status;
    private Long createDate;
    private String createSource;
    private Long lastLoginTime;
    private Long activeTime;
    private Long isGoverner;
    private Long isNameValid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getCreateSource() {
        return createSource;
    }

    public void setCreateSource(String createSource) {
        this.createSource = createSource;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Long emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Long getExpValue() {
        return expValue;
    }

    public void setExpValue(Long expValue) {
        this.expValue = expValue;
    }

    public String getFaceBook() {
        return faceBook;
    }

    public void setFaceBook(String faceBook) {
        this.faceBook = faceBook;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public String getIdcardPath() {
        return idcardPath;
    }

    public void setIdcardPath(String idcardPath) {
        this.idcardPath = idcardPath;
    }

    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    public Long getIsGoverner() {
        return isGoverner;
    }

    public void setIsGoverner(Long isGoverner) {
        this.isGoverner = isGoverner;
    }

    public Long getIsNameValid() {
        return isNameValid;
    }

    public void setIsNameValid(Long isNameValid) {
        this.isNameValid = isNameValid;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMobileStatus() {
        return mobileStatus;
    }

    public void setMobileStatus(Long mobileStatus) {
        this.mobileStatus = mobileStatus;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getWeiBo() {
        return weiBo;
    }

    public void setWeiBo(String weiBo) {
        this.weiBo = weiBo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
