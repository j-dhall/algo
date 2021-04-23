package conc;

import java.util.function.Consumer;

public abstract class DeferredCallback {
	abstract void registerCallback (Consumer<String> callback, String arg, long seconds);
}
