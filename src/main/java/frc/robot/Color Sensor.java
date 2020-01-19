+/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7034.robot;
import edu.wpi.first.wpilibj.I2C;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ColorSensor {
protected final static int CMD = 0x80;
protected final static int MULTI_BYTE_BIT = 0x20;

protected final static int ENABLE_REGISTER = 0x00;
protected final static int ATIME_REGISTER  = 0x01;
protected final static int PPULSE_REGISTER = 0X0e;

protected final static int ID_REGISTER     = 0x12;
protected final static int CDATA_REGISTER  = 0x14;
protected final static int RDATA_REGISTER  = 0x16;
protected final static int GDATA_REGISTER  = 0x18;
protected final static int BDATA_REGISTER  = 0x1A;
protected final static int PDATA_REGISTER  = 0x1C;

protected final static int PON   = 0b00000001;
protected final static int AEN   = 0b00000010;
protected final static int PEN   = 0b00000100;
protected final static int WEN   = 0b00001000;
protected final static int AIEN  = 0b00010000;
protected final static int PIEN  = 0b00100000;

private final double integrationTime = 10;


private I2C sensor;

private ByteBuffer buffy = ByteBuffer.allocate(8);

public short red = 0, green = 0, blue = 0, pro = 0;

public ColorSensor(I2C.Port port) {
    buffy.order(ByteOrder.LITTLE_ENDIAN)
    sensor = new I2C(port, 0x39); //0x39 is the adress of the VexColorSensor V2

    sensor.write(CMD 0x00, POW AEN PEN);

    sensor.write(CMD 0x00, (int) (256-integrationTime/2.38)); //configures the integration time (for updating color data)
    sensor.write(CMD 0x0E, 0b1111);
}

public void read() {
    buffy.clear();
    sensor.read(CMD MUTLI_BYE_BIT  RDATA_REGISTER, 8, buffy);

    red = buffy.getShort(0);
    if(red < 0) {red += 0b0000000000000000000;}


}
}

/*package org.usfirst.frc.team7034.robot;

import java.nio.ByteBuffer;
import edu.wpi.first.wpilibj.I2C;

public class ColorSensor{
    ByteBuffer buffer = ByteBuffer.allocate(1);
    I2C sensor;
    public ColorSensor(){
        sensor = new I2C(I2C.Port.kOnboard, 0x39); //0x39 is the sensor's i2c address
        sensor.write(0x00, 192); //0b11000000 ... Power on, color sensor on. (page 20 of sensor database0

    }
    public int red(){
        sensor.read(0x16, 1, buffer);
        return buffer.get(0);
    }
    public int green(){
        sensor.read(0x18, 1, buffer);
        return buffer.get(0);
    }
    public int yellow(){
        sensor.read(0x18, 1, buffer);
        return buffer.get(0);
    }
    //Blue 100 0 0 0
    //Green 100 0 100 0
    //Red 0 100 100 0
    //Yellow 0 0 100 0
}
*/