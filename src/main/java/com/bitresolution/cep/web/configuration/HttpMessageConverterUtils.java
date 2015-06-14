package com.bitresolution.cep.web.configuration;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HttpMessageConverterUtils {
    private static final ClassLoader CLASSLOADER = HttpMessageConverterUtils.class.getClassLoader();

    private HttpMessageConverterUtils() throws InstantiationException {
        throw new InstantiationException("Can not instantiate this utility class");
    }

    /**
     * Determine whether a JAXB binder is present on the classpath and can be loaded. Will return
     * <tt>false</tt> if either the {@link javax.xml.bind.Binder} or one of its dependencies is not
     * present or cannot be loaded.
     */
    public static boolean isJaxb2Present() {
        return ClassUtils.isPresent("javax.xml.bind.Binder", CLASSLOADER);
    }

    /**
     * Determine whether Jackson 2.x is present on the classpath and can be loaded. Will return
     * <tt>false</tt> if either the {@link com.fasterxml.jackson.databind.ObjectMapper},
     * {@link com.fasterxml.jackson.core.JsonGenerator} or one of its dependencies is not present
     * or cannot be loaded.
     */
    public static boolean isJackson2Present() {
        return ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", CLASSLOADER) &&
                ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", CLASSLOADER);
    }

    /**
     * Determine whether Jackson 1.x is present on the classpath and can be loaded. Will return
     * <tt>false</tt> if either the {@link org.codehaus.jackson.map.ObjectMapper},
     * {@link org.codehaus.jackson.JsonGenerator} or one of its dependencies is not present or
     * cannot be loaded.
     */
    @Deprecated
    public static boolean isJacksonPresent() {
        return ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", CLASSLOADER) &&
                ClassUtils.isPresent("org.codehaus.jackson.JsonGenerator", CLASSLOADER);
    }

    /**
     * Returns default {@link HttpMessageConverter} instances, i.e.:
     * <p/>
     * <ul>
     * <li>{@linkplain ByteArrayHttpMessageConverter}</li>
     * <li>{@linkplain StringHttpMessageConverter}</li>
     * <li>{@linkplain ResourceHttpMessageConverter}</li>
     * <li>{@linkplain Jaxb2RootElementHttpMessageConverter} (when JAXB is present)</li>
     * <li>{@linkplain MappingJackson2HttpMessageConverter} (when Jackson 2.x is present)</li>
     * <li>{@linkplain org.springframework.http.converter.json.MappingJacksonHttpMessageConverter}
     * (when Jackson 1.x is present and 2.x not)</li>
     * </ul>
     * <p/>
     * <p>Note: It does not return all of the default converters defined in Spring, but just thus
     * usable for exception responses.</p>
     */
    @SuppressWarnings("deprecation")
    public static List<HttpMessageConverter<?>> getDefaultHttpMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        stringConverter.setWriteAcceptCharset(false); // See SPR-7316
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(stringConverter);
        converters.add(new ResourceHttpMessageConverter());
        if(isJaxb2Present()) {
            converters.add(new Jaxb2RootElementHttpMessageConverter());
        }
        if(isJackson2Present()) {
            converters.add(new MappingJackson2HttpMessageConverter());
        }
        else if(isJacksonPresent()) {
            try {
                Class<?> clazz = Class.forName("org.springframework.http.converter.json.MappingJacksonHttpMessageConverter");
                converters.add((HttpMessageConverter<?>) clazz.newInstance());
            }
            catch (ClassNotFoundException ex) {
                // Ignore it, this class is not available since Spring 4.1.0.
            }
            catch (InstantiationException ex) {
                throw new IllegalStateException(ex);
            }
            catch (IllegalAccessException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return converters;
    }
}
