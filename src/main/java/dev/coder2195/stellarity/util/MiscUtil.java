package dev.coder2195.stellarity.util;

public interface MiscUtil {
	static <T extends Throwable> T initThrowableCause(T original, Throwable cause) {
		original.initCause(cause);
		return original;
	}
}