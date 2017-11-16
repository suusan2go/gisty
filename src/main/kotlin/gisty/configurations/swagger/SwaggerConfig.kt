package gisty.configurations.swagger

import com.google.common.base.Predicate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import com.google.common.base.Predicates.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun document(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(paths())
                .build()
                .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Example API")
                .version("1.0")
                .build()
    }

    private fun paths(): Predicate<String> {
        return or(containsPattern("/api*"));
    }
}