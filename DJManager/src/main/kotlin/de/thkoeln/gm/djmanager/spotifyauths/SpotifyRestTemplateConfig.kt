package de.thkoeln.gm.djmanager.spotifyauths

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.util.*

@Configuration
class RestTemplateConfig() {
    // spotify auth gibt eine message zurück die spring nicht so gut lesen kann, deswegen hier "converter"
    // (beans önnen von spring verarbeitet werden)
    @Bean
    fun restTemplate(): RestTemplate {
        val converter = MappingJackson2HttpMessageConverter() // JSON converter
        val restTemplate = RestTemplate()
        val messageConverters: MutableList<HttpMessageConverter<*>> = ArrayList()
        messageConverters.add(converter)
        restTemplate.messageConverters = messageConverters
        return restTemplate
    }
}