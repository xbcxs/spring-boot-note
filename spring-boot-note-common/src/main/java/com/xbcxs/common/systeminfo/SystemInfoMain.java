package com.xbcxs.common.systeminfo;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

@Slf4j
public class SystemInfoMain {

    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
       /* log.info("getProcessor:{}", hal.getProcessor());
        log.info("getMemory:{}", hal.getMemory().getPhysicalMemory().get(0));
        log.info("getDiskStores:{}", hal.getDiskStores().get(0));
        log.info("getGraphicsCards:{}", hal.getGraphicsCards());*/


        log.info("getProcessor:{}", hal.getProcessor().getProcessorIdentifier().getProcessorID());
        log.info("getDiskStores:{}", hal.getDiskStores().get(0).getSerial());
        log.info("getMemory:{}", hal.getMemory().getPhysicalMemory().get(0));
        log.info("getGraphicsCards:{}", hal.getGraphicsCards().get(0).getDeviceId());
//        log.info("getComputerSystem:{}", hal.getComputerSystem());
//        log.info("Sensors:{}", hal.getSensors());
//        log.info("getDisplays:{}", hal.getDisplays());
    }
}
