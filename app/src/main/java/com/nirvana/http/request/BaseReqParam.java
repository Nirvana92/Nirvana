package com.nirvana.http.request;

import java.util.Map;

/**
 * Created by Nirvana on 2016/6/24.
 */
public class BaseReqParam {
    private String device;
    private String deviceId;
    private String operSystem;
    private String version;
    private String loginIp;
    private String pixels;
    private Map<String, String> reqParams;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOperSystem() {
        return operSystem;
    }

    public void setOperSystem(String operSystem) {
        this.operSystem = operSystem;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getPixels() {
        return pixels;
    }

    public void setPixels(String pixels) {
        this.pixels = pixels;
    }

    public Map<String, String> getReqParams() {
        return reqParams;
    }

    public void setReqParams(Map<String, String> reqParams) {
        this.reqParams = reqParams;
    }
}
