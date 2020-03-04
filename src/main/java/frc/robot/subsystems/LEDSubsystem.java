package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDSubsystem extends SubsystemBase 
{
    // LED Strip Variables

    private AddressableLED       m_led;
    private AddressableLEDBuffer m_ledBuffer;

//    private int                  m_nUpdateInterval   = 1;
    private int                  m_nPulsingIncrement = 1;
    private int                  m_nPulsingValue     = 0xFF;

    private int                  m_nBallIndicators = 0;

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////
    
    public LEDSubsystem() 
    {
        // Must be a PWM header, not MXP or DIO
        m_led = new AddressableLED( Constants.PWM_LED_STRIP );

        // Length is expensive to set, so only set it once, then just update data

        m_ledBuffer = new AddressableLEDBuffer( Constants.LED_STRIP_NUM_OF_LEDS );
        m_led.setLength( m_ledBuffer.getLength() );

        // Set the data
        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    private void UpdatePulseValue() 
    {
        m_nPulsingValue += m_nPulsingIncrement;
      
        if (m_nPulsingValue > 0xFF)
        {
            m_nPulsingIncrement = -(m_nPulsingIncrement);
            m_nPulsingValue    += m_nPulsingIncrement;
        }
        else if (m_nPulsingValue < 0)
        {
            m_nPulsingIncrement = -(m_nPulsingIncrement);
            m_nPulsingValue    += m_nPulsingIncrement;
        }
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void SetAll( Color color )
    {
        SetPixels( 0, Constants.LED_STRIP_NUM_OF_LEDS, color );
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void Pulse( int nStart, int nCount, int r, int g, int b)
    {
        SetPixels( nStart, nCount, r, g, b );
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void Pulse( int nStart, int nCount, Color color)
    {
        SetPixels( nStart, nCount, color );
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void SetRGB( int pixel, int r, int g, int b )
    {
        if ((pixel < 0) || (pixel >= Constants.LED_STRIP_NUM_OF_LEDS))
            return;

        m_ledBuffer.setRGB( pixel,  r, g, b);
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void SetLED( int pixel, Color color )
    {
        if ((pixel < 0) || (pixel >= Constants.LED_STRIP_NUM_OF_LEDS))
            return;

        m_ledBuffer.setLED( pixel, color );
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void SetHSV( int pixel, int h, int s, int v )
    {
        if ((pixel < 0) || (pixel >= Constants.LED_STRIP_NUM_OF_LEDS))
            return;

        m_ledBuffer.setHSV( pixel,  h, s, v);
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void SetPixels( int nStart, int nCount, int r, int g, int b )
    {
        if (nStart >= Constants.LED_STRIP_NUM_OF_LEDS)
            return;

        int nEnd = Math.min( Constants.LED_STRIP_NUM_OF_LEDS, nStart + nCount );
        
        for( int i=nStart; i < nEnd; i++ ) 
        {
            m_ledBuffer.setRGB(i,  r, g, b);
        }  
    }

    // //////////////////////////////////////////////////////////////////////
    //
    // //////////////////////////////////////////////////////////////////////

    public void SetPixels( int nStart, int nCount, Color color )
    {
        if (nStart >= Constants.LED_STRIP_NUM_OF_LEDS)
            return;

        int nEnd = Math.min( Constants.LED_STRIP_NUM_OF_LEDS, nStart + nCount );
        
        for( int i=nStart; i < nEnd; i++ ) 
        {
            m_ledBuffer.setLED( i, color );
        }  
    }

    public void ShowBallStatus( byte ballIndicators ) 
    {
        int nLedsPerBall =  Constants.LED_STRIP_NUM_OF_LEDS / Constants.NUM_BALLS;
        int nBallMask = 1;

        if ( ballIndicators != 0 )
        {
            // If not changed from last call, do nothing...
            if (ballIndicators == m_nBallIndicators)
                return;

            m_nBallIndicators = ballIndicators;

            // Not all ball loaded, show which ones are
            for (int nIdx = 0; nIdx < Constants.NUM_BALLS; nIdx++ )
            {
                int nStart = nIdx * nLedsPerBall;

                if (( ballIndicators & nBallMask ) != 0)
                {
                    // No Ball
                    SetPixels( nStart, nLedsPerBall, Color.kLightPink );
                }
                else
                {
                    // Ball

                    SetPixels( nStart, nLedsPerBall, Color.kLightGreen );                
                }
            }
        }
        else
        {
            // Full... do animation... pulsing green for now
            UpdatePulseValue();
            Pulse( 0, Constants.LED_STRIP_NUM_OF_LEDS, 0x00, 0x00, m_nPulsingValue );
        }

        m_led.setData(m_ledBuffer);
	}

 }