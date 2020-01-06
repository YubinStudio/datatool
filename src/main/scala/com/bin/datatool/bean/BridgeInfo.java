package com.bin.bean;

/**
 * @program: javautils
 * @description: 桥梁信息
 * @author: jyb
 * @create: 2019-10-31 19:46
 **/
public class BridgeInfo {
    private String id;                  //传感器编号
    private String status;              //传感器状态
    private String logTime;             //日志产生时间
    private String weather;             //天气
    private String windDirection;       //风向
    private int windSpeed;              //风速，1-10级
    private int temperature;            //温度，-20—40
    private int waterLevel;             //水位,-20—40
    private int gravity;                //重力
    private int frequency;              //自振频率
    private int subsidenceDegree;       //桥墩沉降度,(0-3)cm
    private int displacementDegree;     //桥墩位移度,(0-3)cm
    private int tiltDegree;             //桥墩倾斜度
    private int affectResult;           //影响结果

    public BridgeInfo() {
    }

    public BridgeInfo(String id, String status, String logTime, String weather, String windDirection, int windSpeed, int temperature, int waterLevel, int gravity, int frequency, int subsidenceDegree, int displacementDegree, int tiltDegree, int affectResult) {
        this.id = id;
        this.status = status;
        this.logTime = logTime;
        this.weather = weather;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.waterLevel = waterLevel;
        this.gravity = gravity;
        this.frequency = frequency;
        this.subsidenceDegree = subsidenceDegree;
        this.displacementDegree = displacementDegree;
        this.tiltDegree = tiltDegree;
        this.affectResult = affectResult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getSubsidenceDegree() {
        return subsidenceDegree;
    }

    public void setSubsidenceDegree(int subsidenceDegree) {
        this.subsidenceDegree = subsidenceDegree;
    }

    public int getDisplacementDegree() {
        return displacementDegree;
    }

    public void setDisplacementDegree(int displacementDegree) {
        this.displacementDegree = displacementDegree;
    }

    public int getTiltDegree() {
        return tiltDegree;
    }

    public void setTiltDegree(int tiltDegree) {
        this.tiltDegree = tiltDegree;
    }

    public int getAffectResult() {
        return affectResult;
    }

    public void setAffectResult(int affectResult) {
        this.affectResult = affectResult;
    }

    @Override
    public String toString() {
        return "BridgeInfo{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", logTime='" + logTime + '\'' +
                ", weather='" + weather + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", windSpeed=" + windSpeed +
                ", temperature=" + temperature +
                ", waterLevel=" + waterLevel +
                ", gravity=" + gravity +
                ", frequency=" + frequency +
                ", subsidenceDegree=" + subsidenceDegree +
                ", displacementDegree=" + displacementDegree +
                ", tiltDegree=" + tiltDegree +
                ", affectResult=" + affectResult +
                '}';
    }
}
