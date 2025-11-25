package com.example.model;

import java.time.LocalDateTime;

public class VehicleStay {
    private Integer id;
    private String licensePlate;
    private String vehicleModel;
    private String vehicleColor;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double totalCost;
    private Integer userId;

    public VehicleStay() {}

    public VehicleStay(String licensePlate, String vehicleModel, String vehicleColor, LocalDateTime entryTime, Integer userId) {
        this.licensePlate = licensePlate;
        this.vehicleModel = vehicleModel;
        this.vehicleColor = vehicleColor;
        this.entryTime = entryTime;
        this.userId = userId;
    }

    // Getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    public String getVehicleColor() { return vehicleColor; }
    public void setVehicleColor(String vehicleColor) { this.vehicleColor = vehicleColor; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "VehicleStay{licensePlate='" + licensePlate + "', vehicleModel='" + vehicleModel + "', vehicleColor='" + vehicleColor + "', entryTime=" + entryTime + ", exitTime=" + exitTime + ", totalCost=" + totalCost + "}";
    }
}