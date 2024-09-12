package hello.itemservice.message;


import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
class MessageSourceTest {

    @Autowired
    private MessageSource messageSource;


    @Test
    void message() {
        String hello = messageSource.getMessage("hello", null, Locale.KOREA);

        assertThat(hello).isEqualTo("안녕");
    }


    @Test
    void NoSuchMessageException() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, Locale.KOREA)).isInstanceOf(
                NoSuchMessageException.class);
    }


    @Test
    void argumentMessage() {
        String result = messageSource.getMessage("hello.name", new Object[] {"Spring"}, Locale.KOREA);
        assertThat(result).isEqualTo("안녕 Spring");
    }

}
