package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase 
{
    private DoubleSolenoid m_intakeSolenoid;

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public IntakeArm() 
    {
        m_intakeSolenoid = new DoubleSolenoid( Constants.CAN_PCM, 
                                               Constants.SOL_INTAKE_ARM_FORWARD, 
                                               Constants.SOL_INTAKE_ARM_REVERSE );
       m_intakeSolenoid.set( Value.kReverse );
                                            
    }

    public void IntakePistonDown()
    {
        m_intakeSolenoid.set( Value.kForward );
    }

    public void IntakePistonUp()
    {
        m_intakeSolenoid.set( Value.kReverse );
    }
}