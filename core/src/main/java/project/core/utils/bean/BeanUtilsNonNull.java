package project.core.utils.bean;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanUtilsNonNull {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, nonNull(source));
    }

    private static String[] nonNull(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        return Arrays.stream(wrappedSource.getPropertyDescriptors())
                .map(pd -> pd.getName())
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
