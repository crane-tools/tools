package crane.model;

import java.io.Serializable;

/**
 * Created by crane on 16/9/23.
 * private字段需要和field值一致
 */
public class User implements Serializable, EsBaseModel {
    /**
     *
     */
    private String id;
    /**
     * 登陆名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 实名
     */
    private String realName;
    /**
     * 密码 MD5
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱状态（是否认证等）1已认证
     */
    private Long emailStatus;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 手机号码状态（是否认证等）
     */
    private Long mobileStatus;
    /**
     * 经验值
     */
    private Long expValue;
    /**
     * 性别
     */
    private Long gender;
    /**
     * 角色 normal amdin
     */
    private String role;
    /**
     * 签名
     */
    private String signature;
    /**
     * QQ号码
     */
    private String qq;
    /**
     * 微信号
     */
    private String weChat;
    /**
     * 微博
     */
    private String weiBo;
    /**
     * facebook
     */
    private String faceBook;
    /**
     * linkedin
     */
    private String linkedin;
    /**
     * 生日
     */
    private Long birthday;
    /**
     * 公司
     */
    private String company;
    /**
     * 职位、职业
     */
    private String occupation;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 个人网址
     */
    private String site;
    /**
     * 毕业学校
     */
    private String graduateSchool;
    /**
     * 最高学历
     */
    private String education;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 证件类型：身份证 护照 军官证等
     */
    private String idcardType;
    /**
     * 证件号码
     */
    private String idcardNumber;
    /**
     * 证件号码图片路径
     */
    private String idcardPath;
    /**
     * 状态 normal frozen
     */
    private String status;
    /**
     * 创建日期
     */
    private Long createDate;
    /**
     * 用户来源（可能来之oauth第三方)
     */
    private String createSource;
    /**
     * 最后的登陆时间
     */
    private Long lastLoginTime;
    /**
     * 激活时间
     */
    private Long activeTime;
    /**
     * 是否管理机构用户
     */
    private Long isGoverner;
    /**
     * 是否实名认证
     */
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
