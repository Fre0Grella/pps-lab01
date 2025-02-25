package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private static  final int PIN_NOT_SET = -1;
    private static final int MAX_ATTEMPTS = 5;
    private boolean locked = false;
    private boolean blocked = false;
    private int pin = PIN_NOT_SET;
    private int failedAttempts = 0;

    @Override
    public void setPin(int pin) throws IllegalArgumentException{
        int maxPinValue = 9999;
        int lowestPinValue = 0;
        if (pin > maxPinValue || pin < lowestPinValue) {
            throw new IllegalArgumentException();
        }
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (pin == this.pin) {
            this.locked = false;
        } else {
            if (++this.failedAttempts >= MAX_ATTEMPTS) {
                this.blocked = true;
            }
        }
    }

    @Override
    public void lock() {
        if (this.pin == PIN_NOT_SET) {
            throw new IllegalStateException();
        }
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
