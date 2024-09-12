# 메시지 국제화

### 직접등록

```java

@Bean
public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("messages", "errors");
    messageSource.setDefaultEncoding("utf-8");
    return messageSource;
}
```

`basenames` : 설정 파일의 이름을 지정한다.

`messages` 로 지정하면 `messages.properties` 파일을 읽어서 사용한다.

추가로 국제화 기능을 적용하려면 `messages_en.properties` , `messages_ko.properties` 와 같
이 파일명 마지막에 언어 정보를 주면된다.

만약 찾을 수 있는 국제화 파일이 없으면 `messages.properties` (언어정보가 없는 파일명)를 기본으로 사용한다.

파일의 위치는 `/resources/messages.properties` 에 두면 된다.

여러 파일을 한번에 지정할 수 있다. 여기서는 `messages` , `errors` 둘을 지정했다. `defaultEncoding` : 인코딩 정보를 지정한다. `utf-8` 을 사용하면 된다.

#### 스프링 부트

스프링 부트를 사용하면 스프링 부트가 `MessageSource` 를 자동으로 스프링 빈으로 등록한다.

#### 스프링 부트 메시지 소스 기본 값

`spring.messages.basename=messages`

`MessageSource` 를 스프링 빈으로 등록하지 않고, 스프링 부트와 관련된 별도의 설정을 하지 않으면 `messages` 라 는 이름으로 기본 등록된다.

따라서 `messages_en.properties` , `messages_ko.properties` , `messages.properties` 파일만 등록하면 자동으로 인식된다.

## 스프링의 국제화 메시지 선택

앞서 `MessageSource` 테스트에서 보았듯이 메시지 기능은 `Locale` 정보를 알아야 언어를 선택할 수 있다.

결국 스프링도 `Locale` 정보를 알아야 언어를 선택할 수 있는데, 스프링은 언어 선택시 기본으로 `Accept-Language` 헤더의 값을 사용한다.

### LocaleResolver

스프링은 `Locale` 선택 방식을 변경할 수 있도록 `LocaleResolver` 라는 인터페이스를 제공하는데, 스프링 부트는 기본으로 `Accept-Language` 를 활용하는
`AcceptHeaderLocaleResolver` 를 사용한다.

```java
public interface LocaleResolver {

    Locale resolveLocale(HttpServletRequest request);

    void setLocale(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Locale locale);
}
```

**LocaleResolver 변경**

만약 `Locale` 선택 방식을 변경하려면 `LocaleResolver` 의 구현체를 변경해서 쿠키나 세션 기반의 `Locale` 선택 기능을 사용할 수 있다.

예를 들어서 고객이 직접 `Locale` 을 선택하도록 하는 것이다.

관련해서 `LocaleResolver` 를 검색하면 수 많은 예제가 나오니 필요한 분들은 참고하자.