package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private static final int MAX_ATTEMPTS = 5;
    private static final int PIN = 1234;
    private static final int WRONG_PIN = 3;
    private SmartDoorLock smartDoorLock;

    @BeforeEach
    public void setUp() {
         this.smartDoorLock = new SmartDoorLockImpl();
    }

    @Test
    public void isInitiallyUnlockedAndOpen() {
        assertAll(
                () -> assertFalse(smartDoorLock.isLocked()),
                () -> assertFalse(smartDoorLock.isBlocked())
        );
    }

    @Test
    public  void testIllegalPinSetting() {
        int pinLong = 123456;
        int pinNegative = -5;
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.setPin(pinLong)),
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.setPin(pinNegative))
        );
    }

    @Test
    public void canBeLocked() {
        smartDoorLock.setPin(PIN);
        smartDoorLock.lock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    public  void cannotBeLockedWithoutPinSet() {
        assertThrows(IllegalStateException.class, () -> smartDoorLock.lock());
    }

    @Test
    public void canBeUnlocked() {
        smartDoorLock.setPin(PIN);
        smartDoorLock.lock();
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    public void canBeBlocked() {
        smartDoorLock.setPin(PIN);
        smartDoorLock.lock();
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            smartDoorLock.unlock(WRONG_PIN);
        }

        assertTrue(smartDoorLock.isBlocked());
    }

}
