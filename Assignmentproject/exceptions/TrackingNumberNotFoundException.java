package exceptions; // Package declaration

// Custom exception class extending Exception
public class TrackingNumberNotFoundException extends Exception {
    public TrackingNumberNotFoundException(String message) {
        super(message); // Call superclass constructor with message
    }
}

