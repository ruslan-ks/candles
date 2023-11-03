package r.kostiuk.candles.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import r.kostiuk.candles.dto.UIMessage;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageSourceBasedMessageProvider implements MessageProvider {
    private final MessageSource messageSource;

    @Override
    public String getDefaultMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, Locale.getDefault());
    }

    @Override
    public UIMessage getDefaultUIMessage(String messageCode) {
        return new UIMessage(messageCode, getDefaultMessage(messageCode));
    }
}
