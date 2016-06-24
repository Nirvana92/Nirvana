package com.nirvana.http.response;

/**
 * Created by Nirvana on 2016/6/24.
 */
public class BaseEntity {
    protected String login;
    protected String userName;
    protected String deptName;
    protected String deptCode;
    protected String permission;
    protected String version;
    protected String needUpdate;
    protected String busiCode;
    protected String UnreadBulletin;

    protected String resultId;
    protected String resultParam;
    protected String resultMessage;

    protected boolean resultCode;
    protected String resultMsg;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(String needUpdate) {
        this.needUpdate = needUpdate;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getUnreadBulletin() {
        return UnreadBulletin;
    }

    public void setUnreadBulletin(String unreadBulletin) {
        UnreadBulletin = unreadBulletin;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getResultParam() {
        return resultParam;
    }

    public void setResultParam(String resultParam) {
        this.resultParam = resultParam;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public boolean isResultCode() {
        return resultCode;
    }

    public void setResultCode(boolean resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
