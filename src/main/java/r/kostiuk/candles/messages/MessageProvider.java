package r.kostiuk.candles.messages;

import r.kostiuk.candles.dto.UIMessage;

public interface MessageProvider {
    String getDefaultMessage(String messageCode);

    /**
     * Same as {@link MessageProvider#getDefaultMessage(String)} but messageCode and message
     * are wrapped into UIMessage
     */
    UIMessage getDefaultUIMessage(String messageCode);
}
