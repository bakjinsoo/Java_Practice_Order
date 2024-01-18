package hello.core;

import hello.core.order.AppConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
//Configuration에는 Component어노테이션이 존재하기 때문에,ComponentScan에서 검색됨 AppConfig.class를 안전하게 유지하기 위해 Configuration이 붙어 있는 클래스를 필터링해서 제외 시킨것.
public class AutoAppConfig {

}
